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
import java.util.List;

public class ClientRequestDao extends AbstractDao<Integer, ClientRequest> {

    private static Logger log = Logger.getLogger(ClientRequestDao.class);

    private final static int NEW_REQUESTS_STATUS = 1;
    private final static int ACCEPTED_REQUESTS_STATUS = 2;
    private final static int PAYED_REQUESTS_STATUS = 4;
    private final static int DAMAGED_REQUESTS_STATUS = 6;

    private final static String SQL_SELECT_REQUEST_BY_STATUS = "select * from request where idStatus = ?";
    private final static String SQL_SELECT_NEW_REQUESTS = "select * " +
            "from request " +
            "inner join specifications " +
            "where request.id = specifications.id " +
            "and idStatus = ? ";

    private final static String SQL_SELECT_EXPIRED_REQUESTS = "select *" +
            "from request " +
            "inner join specifications " +
            "where request.id = specifications.id " +
            "and idStatus = ? " +
            "and CURDATE() >= rentalDate";

    private final static String SQL_SELECT_NOT_RETURNED_CARS = "select *," +
            "DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY) as 'expectedReturnDate'" +
            "from request " +
            "inner join specifications " +
            "where request.id = specifications.id " +
            "and idStatus = ? " +
            "and CURDATE() > DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY)";

    private final static String SQL_SELECT_DAMAGED_CARS = "select *" +
            "from request " +
            "inner join specifications " +
            "where request.id = specifications.id " +
            "and idStatus = ? ";

    public ClientRequestDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<ClientRequest> findAll() {
        return null;
    }

    @Override
    public ClientRequest findEntityById(Integer id) {
        return null;
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


    public ArrayList<Composed> findByStatus(int statusCode) throws DaoException {
        ArrayList<Composed> requests = new ArrayList<Composed>();

        PreparedStatement st = null;
        try {
            switch (statusCode) {
                case NEW_REQUESTS_STATUS:
                    st = connector.prepareStatement(SQL_SELECT_NEW_REQUESTS);
                    log.info("SQL_SELECT_NEW_REQUESTS");
                    break;
                case ACCEPTED_REQUESTS_STATUS:
                    st = connector.prepareStatement(SQL_SELECT_EXPIRED_REQUESTS);
                    log.info("SQL_SELECT_EXPIRED_REQUESTS");
                    break;
                case PAYED_REQUESTS_STATUS:
                    st = connector.prepareStatement(SQL_SELECT_NOT_RETURNED_CARS);
                    log.info("SQL_SELECT_NOT_RETURNED_CARS");
                    break;
                case DAMAGED_REQUESTS_STATUS:
                    st = connector.prepareStatement(SQL_SELECT_DAMAGED_CARS);
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
                Specification specification = new Specification();
                clientRequest.setId(rs.getInt("request.id"));
//                clientRequest.setBirthDate(rs.getDate("birthDate"));
                clientRequest.setFirstName(rs.getString("firstName"));
                clientRequest.setLastName(rs.getString("lastName"));
//                clientRequest.setIdStatus(rs.getInt("idStatus"));
//                clientRequest.setIssueDate(rs.getDate("issueDate"));
                clientRequest.setRentalPeriod(rs.getInt("rentalPeriod"));
                clientRequest.setRentalDate(rs.getDate("rentalDate"));
//                clientRequest.setSerialNumber(rs.getString("serialNumber")); //for new requests
                clientRequest.setReturnDate(rs.getDate("returnDate"));
                clientRequest.setRepairCost(rs.getFloat("repairCost"));
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
            connector.closeStatement(st);
        }

        return requests;

    }

}
