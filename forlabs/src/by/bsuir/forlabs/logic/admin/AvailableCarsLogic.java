package by.bsuir.forlabs.logic.admin;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CarDao;
import by.bsuir.forlabs.dao.ClientRequestDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Car;
import by.bsuir.forlabs.subjects.ClientRequest;

import java.util.ArrayList;

public class AvailableCarsLogic {

    /**
     *
     * @param idRequest
     * @return
     * @throws LogicalException
     */
    public static ArrayList<Car> findAvailableCarsForRequest(int idRequest)
            throws LogicalException {

        ArrayList<Car> freeCars = new ArrayList<Car>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(idRequest);
            CarDao carDao = new CarDao(wc);
            freeCars = carDao.findFreeCarsBySpecificationsOnDate(
                    clientRequest.getIdSpecification(), 
                    clientRequest.getRentalDate(),
                    clientRequest.getRentalPeriod());

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return freeCars;
    }

    /**
     *
     * @param idRequest
     * @return
     * @throws LogicalException
     */
    public static int countAvailableCarsForRequest(int idRequest)
            throws LogicalException {

        int freeCarsCount;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(idRequest);
            CarDao carDao = new CarDao(wc);
            freeCarsCount = carDao.countFreeCarsBySpecificationsOnDate(
                    clientRequest.getIdSpecification(),
                    clientRequest.getRentalDate(),
                    clientRequest.getRentalPeriod());

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return freeCarsCount;
    }

}
