package by.bsuir.forlabs.logic.admin;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CarDao;
import by.bsuir.forlabs.dao.ClientRequestDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Car;
import by.bsuir.forlabs.subjects.ClientRequest;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ChangeStatusLogic {

    private final static Logger log = Logger.getLogger(ChangeStatusLogic.class);

    private final static int NEW_REQUESTS_STATUS = 1;
    private final static int ACCEPTED_REQUESTS_STATUS = 2;
    private final static int REJECTED_REQUESTS_STATUS = 3;
    private final static int PAYED_REQUESTS_STATUS = 4;
    private final static int RETURNED_REQUESTS_STATUS = 5;
    private final static int DAMAGED_REQUESTS_STATUS = 6;

    /**
     * @param requestId
     * @throws LogicalException when DaoException occurs or request was not found
     */
    public static void approveRequest(int requestId) throws LogicalException {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            wc.setAutoCommit(false);
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(requestId);

            if (clientRequest != null) {
                ArrayList<Car> freeCars = new ArrayList<Car>();
                CarDao carDao = new CarDao(wc);
                freeCars = carDao.findFreeCarsBySpecificationsOnDate(
                        clientRequest.getIdSpecification(),
                        clientRequest.getRentalDate(),
                        clientRequest.getRentalPeriod());
                Car carForRequest = freeCars.get(0);
                carForRequest.setFree(false);
                carDao.update(carForRequest);

                clientRequest.setIdStatus(ACCEPTED_REQUESTS_STATUS);
                clientRequest.setIdCar(carForRequest.getId());
                clientRequestDao.update(clientRequest);

                wc.commit();
                wc.setAutoCommit(true);
            } else {
                wc.setAutoCommit(true);
                throw new LogicalException("Request with id = " + requestId + " doesn't exist");
            }
        } catch (DaoException | SQLException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }
    }

    /**
     * @param requestId
     * @param rejectComment
     * @throws LogicalException when DaoException occurs or request was not found
     */
    public static void rejectRequest(int requestId, String rejectComment) throws LogicalException {
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(requestId);
            if (clientRequest != null) {
                clientRequest.setIdStatus(REJECTED_REQUESTS_STATUS);
                clientRequest.setStatusComment(rejectComment);
                clientRequestDao.update(clientRequest);
            } else {
                throw new LogicalException("Request with id = " + requestId + " doesn't exist");
            }
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }
    }

    /**
     * @param requestId
     * @throws LogicalException when DaoException occurs or request was not found
     */
    public static void setPayedRequestsStatus(int requestId) throws LogicalException {
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(requestId);
            if (clientRequest != null) {
                clientRequest.setIdStatus(PAYED_REQUESTS_STATUS);
                clientRequestDao.update(clientRequest);
            } else {
                throw new LogicalException("Request with id = " + requestId + " doesn't exist");
            }
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }
    }

    /**
     * @param requestId
     * @param returnComment
     * @throws LogicalException when DaoException occurs or request was not found
     */
    public static void returnCar(int requestId, String returnComment) throws LogicalException {
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(requestId);
            if (clientRequest != null) {
                clientRequest.setReturnDate(new Date());
                clientRequest.setIdStatus(RETURNED_REQUESTS_STATUS);
                clientRequest.setStatusComment(returnComment);
                clientRequestDao.update(clientRequest);

                int openedRequestsOnCarCount = clientRequestDao.countOpenedRequestsOnCar(clientRequest.getIdCar());
                log.info("openedRequestsOnCarCount = " + openedRequestsOnCarCount + " idCar = " + clientRequest.getIdCar());
                if (openedRequestsOnCarCount == 0) {
                    CarDao carDao = new CarDao(wc);
                    Car car = carDao.findEntityById(clientRequest.getIdCar());
                    car.setFree(true);
                    carDao.update(car);
                }
            } else {
                throw new LogicalException("Request with id = " + requestId + " doesn't exist");
            }


        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }
    }


    /**
     * @param requestId
     * @param repairCost
     * @param damageDescription
     * @throws LogicalException when DaoException occurs or request was not found
     */
    public static void setRepairBill(int requestId, float repairCost, String damageDescription)
            throws LogicalException {
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
            ClientRequest clientRequest = clientRequestDao.findEntityById(requestId);
            if (clientRequest != null) {
                clientRequest.setRepairCost(repairCost);
                clientRequest.setStatusComment(damageDescription);
                clientRequest.setIdStatus(DAMAGED_REQUESTS_STATUS);
                clientRequestDao.update(clientRequest);
            } else {
                throw new LogicalException("Request with id = " + requestId + " doesn't exist");
            }
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }
    }

}
