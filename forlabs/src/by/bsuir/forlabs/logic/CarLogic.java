package by.bsuir.forlabs.logic;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CarDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Car;

import java.util.ArrayList;
import java.util.Date;

public class CarLogic {

    private final static int NEW_REQUESTS_STATUS = 1;
//    private final static int ACCEPTED_REQUESTS_STATUS = 2;
//    private final static int PAYED_REQUESTS_STATUS = 4;
//    private final static int DAMAGED_REQUESTS_STATUS = 6;

    public static ArrayList<Car> findFreeCarsBySpecificationsOnDate(int idSpecification, Date rentalDate)
            throws LogicalException {

        ArrayList<Car> freeCars = new ArrayList<Car>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            CarDao carDao = new CarDao(wc);
            freeCars = carDao.findFreeCarsBySpecificationsOnDate(idSpecification, rentalDate);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return freeCars;
    }

    public static int countFreeCarsBySpecificationsOnDate(int idSpecification, Date rentalDate)
            throws LogicalException {

        int freeCarsCount;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            CarDao carDao = new CarDao(wc);
            freeCarsCount = carDao.countFreeCarsBySpecificationsOnDate(idSpecification, rentalDate);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return freeCarsCount;
    }

}
