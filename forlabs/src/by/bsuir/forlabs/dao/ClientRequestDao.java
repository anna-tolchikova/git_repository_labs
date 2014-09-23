package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.ClientRequest;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRequestDao extends AbstractDao<Integer, ClientRequest> {

    private static Logger log = Logger.getLogger(ClientRequestDao.class);

    private final static String SQL_SELECT_REQUEST_BY_STATUS = "select * from request where idStatus = ?";
    private final static String SQL_SHORT_SELECT_NEW_REQUESTS = "select " +
            "request.id, request.firstName, request.lastName, request.rentalDate, request.rentalPeriod, specifications.model " +
            "from request " +
            "inner join specifications " +
            "where request.id = specifications.id " +
            "and request.idStatus = ? ";


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


    public ArrayList<ClientRequest> findShortByStatus(int statusCode) throws DaoException {
        ArrayList<ClientRequest> newRequests = new ArrayList<ClientRequest>();

        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SHORT_SELECT_NEW_REQUESTS);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            st.setInt(1, statusCode);
            ResultSet rs = st.executeQuery();
            int newRequestsCount = 0;
            while (rs.next()) {
                ++newRequestsCount;
                ClientRequest clientRequest = new ClientRequest();
                clientRequest.setId(rs.getInt("id"));
//                clientRequest.setBirthDate(rs.getDate("birthDate"));
                clientRequest.setFirstName(rs.getString("firstName"));
                clientRequest.setLastName(rs.getString("lastName"));
//                clientRequest.setIdStatus(rs.getInt("idStatus"));
//                clientRequest.setIssueDate(rs.getDate("issueDate"));
                clientRequest.setPeriod(rs.getInt("rentalPeriod"));
                clientRequest.setRentalDate(rs.getDate("rentalDate"));
//                clientRequest.setSerialNumber(rs.getString("serialNumber")); //for new requests
                clientRequest.setModel(rs.getString("model"));


                newRequests.add(clientRequest);
            }
            log.info("New requests count = " + newRequestsCount);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return newRequests;

    }

}
