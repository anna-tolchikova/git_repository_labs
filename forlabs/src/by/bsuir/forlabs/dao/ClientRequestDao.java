package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.ClientRequest;
import by.bsuir.forlabs.subjects.Specification;
import by.bsuir.forlabs.subjects.composers.Composed;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecification;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientRequestDao extends AbstractDao<Integer, ClientRequest> {

    private static Logger log = Logger.getLogger(ClientRequestDao.class);

    private final static int NEW_REQUESTS_STATUS = 1;
    private final static int ACCEPTED_REQUESTS_STATUS = 2;
    private final static int PAYED_REQUESTS_STATUS = 4;
    private final static int DAMAGED_REQUESTS_STATUS = 6;

    private final static String SQL_SELECT_ALL = "select * from request";
    private final static String SQL_SELECT_BY_ID = "select * from request where id = ?";
    private final static String SQL_SELECT_NEW_REQUESTS = "select * " +
            "from request " +
            "inner join specifications " +
            "where request.idSpecification = specifications.id " +
            "and idStatus = ? ";

    private final static String SQL_SELECT_EXPIRED_REQUESTS = "select *" +
            "from request " +
            "inner join specifications " +
            "where request.idSpecification = specifications.id " +
            "and idStatus = ? " +
            "and CURDATE() >= rentalDate";

    private final static String SQL_SELECT_NOT_RETURNED_CARS = "select *," +
            "DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY) as 'expectedReturnDate'" +
            "from request " +
            "inner join specifications " +
            "where request.idSpecification = specifications.id " +
            "and idStatus = ? " +
            "and CURDATE() > DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY)";

    private final static String SQL_SELECT_DAMAGED_CARS = "select *" +
            "from request " +
            "inner join specifications " +
            "where request.idSpecification = specifications.id " +
            "and idStatus = ? ";

    private final static String SQL_SELECT_ALL_JOINED_SPECIFICATIONS = "select *" +
            "FROM request " +
            "inner join specifications " +
            "where request.idSpecification = specifications.id ";

    public ClientRequestDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public ArrayList<ClientRequest> findAll() throws DaoException {
        ArrayList<ClientRequest> requests = new ArrayList<ClientRequest>();

        PreparedStatement st = null;
        try {

            st = prepareStatement(SQL_SELECT_ALL);

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClientRequest clientRequest = new ClientRequest();
                fillObject(clientRequest, rs);
                requests.add(clientRequest);
            }
            log.info("Total requests count = " + requests.size());

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return requests;
    }

    @Override
    public ClientRequest findEntityById(Integer id) throws DaoException {

        ClientRequest clientRequest = null;
        PreparedStatement st = null;
        try {
            st = prepareStatement(SQL_SELECT_BY_ID);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                clientRequest = new ClientRequest();
                fillObject(clientRequest, rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return clientRequest;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(ClientRequest entity) {
        return false;
    }

    @Override
    public boolean create(ClientRequest entity) {
        return false;
    }

    @Override
    public ClientRequest update(ClientRequest entity) {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String st) throws SQLException {
        return super.prepareStatement(st);
    }

    @Override
    public void closeStatement(PreparedStatement st) {
        super.closeStatement(st);
    }


    private void fillObject(ClientRequest clientRequest, ResultSet rs) throws SQLException {
        clientRequest.setId(rs.getInt("request.id"));
        clientRequest.setIdUser(rs.getInt("idUser"));
        clientRequest.setIdSpecification(rs.getInt("idSpecification"));
        clientRequest.setRentalDate(rs.getDate("rentalDate"));
        clientRequest.setRentalPeriod(rs.getInt("rentalPeriod"));
        clientRequest.setReturnDate(rs.getDate("returnDate"));
        clientRequest.setRepairCost(rs.getFloat("repairCost"));
        clientRequest.setSerialNumber(rs.getString("serialNumber"));
        clientRequest.setIssueDate(rs.getDate("issueDate"));
        clientRequest.setBirthDate(rs.getDate("birthDate"));
        clientRequest.setFirstName(rs.getString("firstName"));
        clientRequest.setLastName(rs.getString("lastName"));
        clientRequest.setIdStatus(rs.getInt("idStatus"));
        clientRequest.setStatusComment(rs.getString("statusComment"));
        clientRequest.setIdCar(rs.getInt("idCar"));

    }

    public ArrayList<Composed> findJoinedWithSpecificationByStatus(int statusCode) throws DaoException {
        ArrayList<Composed> requests = new ArrayList<Composed>();

        PreparedStatement st = null;
        try {
            switch (statusCode) {
                case NEW_REQUESTS_STATUS:
                    st = prepareStatement(SQL_SELECT_NEW_REQUESTS);
                    log.info("SQL_SELECT_NEW_REQUESTS");
                    break;
                case ACCEPTED_REQUESTS_STATUS:
                    st = prepareStatement(SQL_SELECT_EXPIRED_REQUESTS);
                    log.info("SQL_SELECT_EXPIRED_REQUESTS");
                    break;
                case PAYED_REQUESTS_STATUS:
                    st = prepareStatement(SQL_SELECT_NOT_RETURNED_CARS);
                    log.info("SQL_SELECT_NOT_RETURNED_CARS");
                    break;
                case DAMAGED_REQUESTS_STATUS:
                    st = prepareStatement(SQL_SELECT_DAMAGED_CARS);
                    log.info("SQL_SELECT_DAMAGED_CARS");
                    break;

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            st.setInt(1, statusCode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClientRequest clientRequest = new ClientRequest();
                fillObject(clientRequest, rs);

                Specification specification = new Specification();
                specification.setModel(rs.getString("model"));

                if (statusCode == PAYED_REQUESTS_STATUS) {
                    clientRequest.setExpectedReturnDate(rs.getDate("expectedReturnDate"));
                }

                ComposedRequestSpecification requestSpecification = new ComposedRequestSpecification();
                requestSpecification.setClientRequest(clientRequest);
                requestSpecification.setSpecification(specification);

                requests.add(requestSpecification);
            }
            log.info("Requests count = " + requests.size());

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return requests;

    }

    public ArrayList<Composed> findJoinedWithSpecification() throws DaoException {
        ArrayList<Composed> requests = new ArrayList<Composed>();

        PreparedStatement st = null;
        try {

            st = prepareStatement(SQL_SELECT_ALL_JOINED_SPECIFICATIONS);

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClientRequest clientRequest = new ClientRequest();
                fillObject(clientRequest, rs);

                Specification specification = new Specification();
                specification.setModel(rs.getString("model"));

                ComposedRequestSpecification requestSpecification = new ComposedRequestSpecification();
                requestSpecification.setClientRequest(clientRequest);
                requestSpecification.setSpecification(specification);

                requests.add(requestSpecification);
            }
            log.info("Total specifications joined requests count = " + requests.size());

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return requests;

    }


}
