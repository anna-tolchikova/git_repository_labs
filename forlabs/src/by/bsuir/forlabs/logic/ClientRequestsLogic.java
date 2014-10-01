package by.bsuir.forlabs.logic;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.ClientRequestDao;
import by.bsuir.forlabs.dao.SpecificationDao;
import by.bsuir.forlabs.dao.StatusDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.ClientRequest;
import by.bsuir.forlabs.subjects.Specification;
import by.bsuir.forlabs.subjects.Status;
import by.bsuir.forlabs.subjects.composers.Composed;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;

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
            newRequests = clientRequestDao.findJoinedWithSpecificationByStatus(NEW_REQUESTS_STATUS);

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
            carsForRepair = clientRequestDao.findJoinedWithSpecificationByStatus(ACCEPTED_REQUESTS_STATUS);

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
            carsForRepair = clientRequestDao.findJoinedWithSpecificationByStatus(PAYED_REQUESTS_STATUS);

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
            carsForRepair = clientRequestDao.findJoinedWithSpecificationByStatus(DAMAGED_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }

    public static ComposedRequestSpecificationStatus composeFullInfoById(int id) throws LogicalException {
        ComposedRequestSpecificationStatus composedInfo = new ComposedRequestSpecificationStatus();

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {

            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            StatusDao statusDao = new StatusDao(wc);
            SpecificationDao specificationDao = new SpecificationDao(wc);

            ClientRequest clientRequest = clientRequestDao.findEntityById(id);
            Status status = statusDao.findEntityById(clientRequest.getIdStatus());
            Specification specification = specificationDao.findEntityById(clientRequest.getIdSpecification());

            composedInfo.setClientRequest(clientRequest);
            composedInfo.setStatus(status);
            composedInfo.setSpecification(specification);
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return composedInfo;
    }

    public static ArrayList<ComposedRequestSpecificationStatus> composeFullInfo() throws LogicalException {
        ArrayList<ClientRequest> allRequests = new ArrayList<ClientRequest>();
        ArrayList<ComposedRequestSpecificationStatus> composedInfoArray =
                new ArrayList<ComposedRequestSpecificationStatus>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            allRequests = clientRequestDao.findAll();
            StatusDao statusDao = new StatusDao(wc);
            SpecificationDao specificationDao = new SpecificationDao(wc);
            for (ClientRequest clientRequest: allRequests) {
                Status status = statusDao.findEntityById(clientRequest.getIdStatus());
                Specification specification = specificationDao.findEntityById(clientRequest.getIdSpecification());
                ComposedRequestSpecificationStatus composedInfo = new ComposedRequestSpecificationStatus();
                composedInfo.setClientRequest(clientRequest);
                composedInfo.setStatus(status);
                composedInfo.setSpecification(specification);
                composedInfoArray.add(composedInfo);
            }

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return composedInfoArray;
    }


}
