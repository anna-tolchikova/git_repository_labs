package by.bsuir.forlabs.connectionpool;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private Logger log;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String logConfigFilename = context.getInitParameter("logConfigFile");
        String pref = "";
        pref = context.getRealPath("/");
        DOMConfigurator.configure(pref + logConfigFilename);
        log = Logger.getLogger(ContextListener.class);
        log.info("Context was initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("Listener works on context destroy. Connections are closed");
        if (ConnectionPool.instanceCreated == true) {
            ConnectionPool pool = ConnectionPool.getInstance();
            pool.closeConnections();
        }
    }
}
