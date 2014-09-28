package by.bsuir.forlabs.logic;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.ClientRequestDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.composers.Composed;

import java.util.ArrayList;

public class ClientRequestsLogic {

    private final static int NEW_REQUESTS_STATUS = 1;
    private final static int ACCEPTED_REQUESTS_STATUS = 2;
    private final static int PAYED_REQUESTS_STATUS = 4;
    private final static int DAMAGED_REQUESTS_STATUS = 6;

    public static ArrayList<Composed> findNewRequests() throws LogicalException {

        ArrayList<Composed> newRequests = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            newRequests = clientRequestDao.findByStatus(NEW_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return newRequests;
    }

    public static ArrayList<Composed> findExpiredRequests() throws LogicalException {
        ArrayList<Composed> carsForRepair = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            carsForRepair = clientRequestDao.findByStatus(ACCEPTED_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }

    public static ArrayList<Composed> findNotReturnedRequests() throws LogicalException {
        ArrayList<Composed> carsForRepair = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            carsForRepair = clientRequestDao.findByStatus(PAYED_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }

    public static ArrayList<Composed> findDamagedRequests() throws LogicalException {
        ArrayList<Composed> carsForRepair = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            carsForRepair = clientRequestDao.findByStatus(DAMAGED_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }


}
