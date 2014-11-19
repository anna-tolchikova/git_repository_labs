package by.bsuir.forlabs.logic.client;

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
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientRequestsProcessingLogic {

    private final static int REQUIRED_FIELDS_COUNT = 7;
    private final static int NEW_REQUEST_STATUS_CODE = 1;

    /**
     * @return
     * @throws by.bsuir.forlabs.exceptions.LogicalException
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

    public static boolean setRequiredFields(HttpServletRequest request, ClientRequest clientRequest) throws ParseException {

        int counter = 0;
        Date requestDate = new Date();
        clientRequest.setRequestDate(requestDate);

        String idUser = request.getParameter("idUser");
        if (idUser != null && !idUser.isEmpty()) {
            clientRequest.setIdUser(Integer.parseInt(idUser));
        }

        String rentalDate = request.getParameter("rentalDate");
        if (rentalDate != null && !rentalDate.isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rentalDate);
            clientRequest.setRentalDate(date);
            ++counter;
        }

        String rentalPeriod = request.getParameter("rentalPeriod");
        if (rentalPeriod != null && !rentalPeriod.isEmpty()) {
            clientRequest.setRentalPeriod(Integer.parseInt(rentalPeriod));
            ++counter;
        }

        String serialNumber = request.getParameter("serialNumber");
        if (serialNumber != null && !serialNumber.isEmpty()) {
            clientRequest.setSerialNumber(serialNumber);
            ++counter;
        }

        String issueDate = request.getParameter("issueDate");
        if (issueDate != null && !issueDate.isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(issueDate);
            clientRequest.setIssueDate(date);
            ++counter;
        }

        String birthDate = request.getParameter("birthDate");
        if (birthDate != null && !birthDate.isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
            clientRequest.setBirthDate(date);
            ++counter;
        }

        String firstName = request.getParameter("firstName");
        if (firstName != null && !firstName.isEmpty()) {
            clientRequest.setFirstName(firstName);
            ++counter;
        }
        String lastName = request.getParameter("lastName");
        if (lastName != null && !lastName.isEmpty()) {
            clientRequest.setLastName(lastName);
            ++counter;
        }
        clientRequest.setIdStatus(NEW_REQUEST_STATUS_CODE);


        return counter == REQUIRED_FIELDS_COUNT ? true : false;

    }


    public static void addClientRequest(ClientRequest clientRequest) throws LogicalException {
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            SpecificationDao specificationDao = new SpecificationDao(wc);
            if (specificationDao.findEntityById(clientRequest.getIdSpecification()) != null) {
                ClientRequestDao clientRequestDao = new ClientRequestDao(wc);
                if (clientRequestDao.create(clientRequest) == 0) {
                    throw new LogicalException("Request wasn't added");
                }
            }
            else {
                throw new LogicalException("Specifications with id = " + clientRequest.getIdSpecification() + " doesn't exist");
            }

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

    }

}

