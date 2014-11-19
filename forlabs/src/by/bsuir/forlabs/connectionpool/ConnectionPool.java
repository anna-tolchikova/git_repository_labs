package by.bsuir.forlabs.connectionpool;

import by.bsuir.forlabs.exceptions.ConnectionInitException;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.resourcesmanagers.DatabaseManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static Logger log = Logger.getLogger(ConnectionPool.class);

    private final static int MAX_POOL_SIZE =
            Integer.parseInt(ConfigurationManager.getProperty("pool.max.size"));
    private final static int MAX_CLIENT_WAIT_PERIOD_MSEC =
            Integer.parseInt(ConfigurationManager.getProperty("pool.max.clientwaitperiod.msec"));
    private final static int MAX_IDLE_TIMEOUT_MSEC =
            Integer.parseInt(ConfigurationManager.getProperty("pool.max.idletimeout.msec"));           // 3hours
    private final static int FIRST_DELAY_PERIOD_MSEC =
            Integer.parseInt(ConfigurationManager.getProperty("pool.firstdelayperiod.msec"));         // 3hours
    private final static int REPEAT_TASK_DELAY_PERIOD_MSEC =
            Integer.parseInt(ConfigurationManager.getProperty("pool.repeattaskperiod.msec"));  // 3hours

    private final Lock      lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    private ArrayList<WrapperConnector>     connections;
    private HashMap<WrapperConnector, Long> connectionsLastUsingMap;
    private AtomicInteger                   currentPoolSize;
    private Timer timer;

    static boolean instanceCreated = false;

    private ConnectionPool(){

        connections = new ArrayList<WrapperConnector>();
        connectionsLastUsingMap = new HashMap<WrapperConnector, Long>();
        currentPoolSize = new AtomicInteger();

        for (int i = 0 ; i < MAX_POOL_SIZE; ++i) {
            WrapperConnector wc = null;
            try {
                String driver = DatabaseManager.getProperty("driver");
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException e) {
                    log.error("DRIVER NOT FOUND");
                }

                wc = createWrapperConnector();
            } catch (ConnectionInitException e) {
                throw new RuntimeException("Error creating ConnectionPool instance.", e);
            }
            connections.add(wc);
            connectionsLastUsingMap.put(wc, new Date().getTime());  // при первичном создании время последнего использования инициализируется временем создания
            currentPoolSize.incrementAndGet();                      // get не нужно, но нужен инкремент
            log.info(i+1 + ") connection created \n\t poolSize = " + currentPoolSize);
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                synchronized (connections) {
                    log.info("Old connections cleaning started.");
                    for (int i = 0; i < connections.size(); i++) {
                        WrapperConnector wc = connections.get(i);
                        Long lastUsing = connectionsLastUsingMap.get(wc);
                        log.info(i + 1 + ")connection check \n\t last used " + ((new Date().getTime()) - lastUsing));
                        if (lastUsing != null && ((new Date().getTime()) - lastUsing) >= MAX_IDLE_TIMEOUT_MSEC) {
                            connectionsLastUsingMap.remove(wc);
                            connections.remove(i);
                            wc.closeConnection();
                            currentPoolSize.decrementAndGet();          // get не нужно, но нужен декремент
                            log.info("...is deleted \n" +
                                    "\t poolSize = " + currentPoolSize);
                        } else {
                            log.info("...is alive \n" +
                                    "\t poolSize = " + currentPoolSize);
                        }
                    }
                }
            }

        }, FIRST_DELAY_PERIOD_MSEC, REPEAT_TASK_DELAY_PERIOD_MSEC);

        instanceCreated = true;
    }

    private static class SingletonLazyLoader {
        private static ConnectionPool INSTANCE = new ConnectionPool();
    }

    /**
     *
     * @return ConnectionPool single for all clients object
     */
    public static ConnectionPool getInstance() {
        return SingletonLazyLoader.INSTANCE;
    }

    private synchronized static WrapperConnector createWrapperConnector () throws ConnectionInitException {
        try {
            return new WrapperConnector(configureConnection());
        } catch (ClassNotFoundException | SQLException |MissingResourceException e) {
            throw new ConnectionInitException(e);
        }
    }

    private synchronized static Connection configureConnection () throws ClassNotFoundException, SQLException {
        Connection connection = null;
        String url = DatabaseManager.getProperty("url");
        String user = DatabaseManager.getProperty("user");
        String password = DatabaseManager.getProperty("password");
        String useUnicode = DatabaseManager.getProperty("useUnicode");
        String encoding = DatabaseManager.getProperty("encoding");

        Properties prop = new Properties();
        prop.put("url", url);
        prop.put("user", user);
        prop.put("password", password);
        prop.put("useUnicode", useUnicode);
        prop.put("encoding", encoding);

        connection = DriverManager.getConnection(url, prop);
        return connection;
    }

    /**
     *
     * @return WrapperConnector object  - for AbstractDao object.
     * null - in case
     * 1. timeout waiting for ConnectionPool object or connections list locking
     * 2. errors by creating new connection
     * 3. errors by locking <code>ConnectionPool</code> instance
     *
     */
    public WrapperConnector getConnection () {  // return null by timeout
        WrapperConnector wc = null;

        try {
            if (lock.tryLock(MAX_CLIENT_WAIT_PERIOD_MSEC / 2, TimeUnit.MILLISECONDS) == false) {
                log.error("Timeout waiting for pool access.");
            }
            else {
                try {
                    if (connections.size() == 0) {  // all alife connections are used

                        if (currentPoolSize.get() < MAX_POOL_SIZE) {  // pool is not full
                            wc = createWrapperConnector();
                            currentPoolSize.incrementAndGet();
                            connectionsLastUsingMap.put(wc, new Date().getTime());
                            log.info("New connection was created by getConnection method.");
                        }
                        else {

                            if (notEmpty.await(MAX_CLIENT_WAIT_PERIOD_MSEC / 2, TimeUnit.MILLISECONDS) == true) {
                                wc = connections.remove(0);
                                log.info("All connections were used. connection was released and given before timeout waiting");
                            }
                            else {
                                log.error("All connections are used. timeout waiting for release"); // error - empty pool
                            }
                        }
                    }
                    else {
                        wc = connections.remove(0);
                        log.info("Connection was given.");
                    }
                }catch (ConnectionInitException e) {
                    log.error("Error creating connection while getting connection." + e);
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            log.error("Error locking ConnectionPool instance while getting connection.", e);
        }
        return wc;
    }

    /**
     *
     * @param wc WrapperConnector object for return connection in connection pool
     */
    public void releaseConnection (WrapperConnector wc) {
        lock.lock();
        try {
            connections.add(wc);
            connectionsLastUsingMap.put(wc, new Date().getTime());
            log.info("Connection was released");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    void closeConnections() {
        timer.cancel();
        for (WrapperConnector connection: connections) {
            connection.closeConnection();
        }
    }

}
