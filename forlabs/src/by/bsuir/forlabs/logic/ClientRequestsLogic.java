package by.bsuir.forlabs.logic;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.ClientRequestDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.ClientRequest;

import java.util.ArrayList;

public class ClientRequestsLogic {

    private static final int NEW_REQUEST_STATUS_CODE = 1;
    private static final int DAMAGED_STATUS_CODE = 6;

    public static ArrayList<ClientRequest> findShortNewRequests() throws LogicalException {

        ArrayList<ClientRequest> newRequests = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            newRequests = clientRequestDao.findShortByStatus(NEW_REQUEST_STATUS_CODE);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return newRequests;
    }


    public static ArrayList<ClientRequest>  findShortCarsForRepair () throws LogicalException {
        ArrayList<ClientRequest> carsForRepair = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            carsForRepair = clientRequestDao.findShortByStatus(DAMAGED_STATUS_CODE);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }

}
