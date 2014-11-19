package by.bsuir.forlabs.logic.admin;

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

public class AdminRequestsProcessingLogic {

    private final static int NEW_REQUESTS_STATUS = 1;
    private final static int DAMAGED_REQUESTS_STATUS = 6;

    /**
     * @return
     * @throws LogicalException
     */
    public static ArrayList<Composed> findNewRequests() throws LogicalException {

        ArrayList<Composed> newRequests = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            newRequests = clientRequestDao.findJoinedWithSpecificationByStatus(NEW_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return newRequests;
    }

    /**
     * @return
     * @throws LogicalException
     */
    public static ArrayList<Composed> findExpiredRequests() throws LogicalException {
        ArrayList<Composed> carsForRepair = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            carsForRepair = clientRequestDao.findJoinedWithSpecificationExpiredRequests();
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return carsForRepair;
    }

    /**
     * @return
     * @throws LogicalException
     */
    public static ArrayList<Composed> findNotReturnedRequests() throws LogicalException {
        ArrayList<Composed> carsForRepair = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        ClientRequestDao clientRequestDao = new ClientRequestDao(wc);

        try {
            carsForRepair = clientRequestDao.findJoinedWithSpecificationNotReturnedRequests();

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }

    /**
     * @return
     * @throws LogicalException
     */
    public static ArrayList<Composed> findDamagedRequests() throws LogicalException {
        ArrayList<Composed> carsForRepair = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            carsForRepair = clientRequestDao.findJoinedWithSpecificationByStatus(DAMAGED_REQUESTS_STATUS);

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }


        return carsForRepair;
    }

    /**
     * @param id
     * @return ComposedRequestSpecificationStatus object - full info for dispaying
     * @throws LogicalException if request not found
     */
    public static ComposedRequestSpecificationStatus composeFullInfoById(int id) throws LogicalException {
        ComposedRequestSpecificationStatus composedInfo = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {

            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            StatusDao statusDao = new StatusDao(wc);
            SpecificationDao specificationDao = new SpecificationDao(wc);

            ClientRequest clientRequest = clientRequestDao.findEntityById(id);
            if (clientRequest != null) {
                composedInfo = new ComposedRequestSpecificationStatus();
                Status status = statusDao.findEntityById(clientRequest.getIdStatus());
                if (status != null) {
                    Specification specification = specificationDao.findEntityById(clientRequest.getIdSpecification());
                    if (specification != null) {

                        composedInfo.setClientRequest(clientRequest);
                        composedInfo.setStatus(status);
                        composedInfo.setSpecification(specification);

                    }
                    else {
                        throw new LogicalException("Specifications with id = " + clientRequest.getIdSpecification() + " doesn't exist");
                    }
                }
                else {
                    throw new LogicalException("Status with id = " + clientRequest.getIdStatus() + " doesn't exist");
                }
            } else {
                throw new LogicalException("Request with id = " + id + " doesn't exist");
            }
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return composedInfo;
    }

    /**
     * @return
     * @throws LogicalException
     */
    public static ArrayList<ComposedRequestSpecificationStatus> composeFullInfo() throws LogicalException {
        ArrayList<ClientRequest> allRequests;
        ArrayList<ComposedRequestSpecificationStatus> composedInfoArray = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            allRequests = clientRequestDao.findAll();
            StatusDao statusDao = new StatusDao(wc);
            SpecificationDao specificationDao = new SpecificationDao(wc);
            for (ClientRequest clientRequest : allRequests) {
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
