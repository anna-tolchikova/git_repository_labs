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
import java.sql.Statement;
import java.util.ArrayList;

public class ClientRequestDao extends AbstractDao<Integer, ClientRequest> {

    private static Logger log = Logger.getLogger(ClientRequestDao.class);

    private final static int NEW_REQUESTS_STATUS = 1;
    private final static int ACCEPTED_REQUESTS_STATUS = 2;
    private final static int PAYED_REQUESTS_STATUS = 4;
    private final static int DAMAGED_REQUESTS_STATUS = 6;

    private final static String SQL_SELECT_ALL =
            "select * from request";

    private final static String SQL_SELECT_BY_ID =
            "select * " +
                    "from request " +
                    "where id = ?";

    private final static String SQL_SELECT_NEW_REQUESTS =
            "select * " +
                    "from request " +
                    "inner join specifications " +
                    "where request.idSpecification = specifications.id " +
                    "and idStatus = ? " +
                    "and CURDATE() < rentalDate";

    private final static String SQL_SELECT_EXPIRED_REQUESTS =
            "select *" +
                    "from request " +
                    "inner join specifications " +
                    "where request.idSpecification = specifications.id " +
                    "and idStatus IN (" + NEW_REQUESTS_STATUS +
                    "," + ACCEPTED_REQUESTS_STATUS +
                    ") " +
                    "and CURDATE() >= rentalDate";

    private final static String SQL_SELECT_NOT_RETURNED_CARS =
            "select *," +
                    "DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY) as 'expectedReturnDate'" +
                    "from request " +
                    "inner join specifications " +
                    "where request.idSpecification = specifications.id " +
                    "and idStatus = " + PAYED_REQUESTS_STATUS + " " +
                    "and CURDATE() > DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY)";

    private final static String SQL_SELECT_DAMAGED_CARS =
            "select *" +
                    "from request " +
                    "inner join specifications " +
                    "where request.idSpecification = specifications.id " +
                    "and idStatus = ? ";

    private final static String SQL_SELECT_ALL_JOINED_SPECIFICATIONS =
            "select *" +
                    "FROM request " +
                    "inner join specifications " +
                    "where request.idSpecification = specifications.id ";

    private final static String SQL_SELECT_OPENED_REQUESTS_ON_CAR_COUNT =
            "select COUNT(*) as rowsCount" +
                    "FROM request" +
                    "WHERE idCar = ? " +
                    "AND returnDate IS NULL";

    private final static String SQL_UPDATE =
            "UPDATE request " +
                    "SET returnDate = ?, " +
                    "repairCost = ?, " +
                    "idStatus = ?, " +
                    "statusComment = ?, " +
                    "idCar = ? " +
                    "WHERE id = ?";
    private final static String SQL_UPDATE_WITHOUT_ID_CAR =
            "UPDATE request " +
                    "SET returnDate = ?, " +
                    "repairCost = ?, " +
                    "idStatus = ?, " +
                    "statusComment = ? " +
                    "WHERE id = ?";
    private final static String SQL_CREATE =
            "INSERT INTO request(idUser,idSpecification,requestDate,rentalDate,\n"+
            "rentalPeriod,serialNumber,issueDate,\n"+
            "birthDate,firstName,lastName,idStatus)\n"+
            "values (?,?,?,?,?,?,?,?,?,?,?)";

    public ClientRequestDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public ArrayList<ClientRequest> findAll() throws DaoException {
        ArrayList<ClientRequest> requests = new ArrayList<ClientRequest>();

        PreparedStatement st = null;
        try {

            st = connector.prepareStatement(SQL_SELECT_ALL);

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

            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return requests;
    }

    @Override
    public ClientRequest findEntityById(Integer id)
            throws DaoException {

        ClientRequest clientRequest = null;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_BY_ID);
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
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return clientRequest;
    }

    @Override
    public boolean delete(ClientRequest entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public int create(ClientRequest entity) throws DaoException {
        log.info("request creaton");
        int createdId = 0;
        PreparedStatement st;
        try {
            st = connector.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, entity.getIdUser());
            st.setInt(2, entity.getIdSpecification());
            st.setDate(3,  new java.sql.Date(entity.getRequestDate().getTime()));
            st.setDate(4, new java.sql.Date(entity.getRentalDate().getTime()));
            st.setInt(5, entity.getRentalPeriod());
            st.setString(6, entity.getSerialNumber());
            st.setDate(7, new java.sql.Date(entity.getIssueDate().getTime()));
            st.setDate(8, new java.sql.Date(entity.getBirthDate().getTime()));
            st.setString(9, entity.getFirstName());
            st.setString(10, entity.getLastName());
            st.setInt(11, entity.getIdStatus());
            st.executeUpdate();
            ResultSet keyset = st.getGeneratedKeys();
            if(keyset.next()) {
                createdId = keyset.getInt(1);
                log.info("created id = " + createdId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }
        return createdId;
    }

    @Override
    public void update(ClientRequest entity)
            throws DaoException {
        PreparedStatement st = null;
        try {
            if (entity.getIdCar() == 0) {
                st = connector.prepareStatement(SQL_UPDATE_WITHOUT_ID_CAR);
                st.setInt(5, entity.getId());
            }
            else {
                st = connector.prepareStatement(SQL_UPDATE);
                st.setInt(5, entity.getIdCar());
                st.setInt(6, entity.getId());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setDate(1, (java.sql.Date) entity.getReturnDate());
            st.setFloat(2, entity.getRepairCost());
            st.setInt(3, entity.getIdStatus());
            st.setString(4, entity.getStatusComment());

            log.info("update ClientRequest. Affected rows count = " + st.executeUpdate());
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }
    }


    private void fillObject(ClientRequest clientRequest, ResultSet rs)
            throws SQLException {
        clientRequest.setId(rs.getInt("request.id"));
        clientRequest.setIdUser(rs.getInt("idUser"));
        clientRequest.setIdSpecification(rs.getInt("idSpecification"));
        clientRequest.setRequestDate(rs.getDate("requestDate"));
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

    public ArrayList<Composed> findJoinedWithSpecificationByStatus(int statusCode)
            throws DaoException {
        ArrayList<Composed> requests = new ArrayList<Composed>();

        PreparedStatement st = null;

        try {
            switch (statusCode) {
                case NEW_REQUESTS_STATUS:
                    st = connector.prepareStatement(SQL_SELECT_NEW_REQUESTS);
                    log.info("SQL_SELECT_NEW_REQUESTS");
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
                fillObject(clientRequest, rs);

                Specification specification = new Specification();
                specification.setModel(rs.getString("model"));

                ComposedRequestSpecification requestSpecification = new ComposedRequestSpecification();
                requestSpecification.setClientRequest(clientRequest);
                requestSpecification.setSpecification(specification);

                requests.add(requestSpecification);
            }
            log.info("Requests count = " + requests.size());
            rs.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return requests;

    }


    public ArrayList<Composed> findJoinedWithSpecificationExpiredRequests()
            throws DaoException {
        ArrayList<Composed> expiredRequests = new ArrayList<Composed>();

        PreparedStatement st = null;
        try {

            st = connector.prepareStatement(SQL_SELECT_EXPIRED_REQUESTS);

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

                ComposedRequestSpecification composedRequestSpecification = new ComposedRequestSpecification();
                composedRequestSpecification.setClientRequest(clientRequest);
                composedRequestSpecification.setSpecification(specification);

                expiredRequests.add(composedRequestSpecification);
            }
            log.info("Total expired requests count = " + expiredRequests.size());
            rs.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return expiredRequests;

    }

    public ArrayList<Composed> findJoinedWithSpecificationNotReturnedRequests()
            throws DaoException {
        ArrayList<Composed> notReturnedRequests = new ArrayList<Composed>();

        PreparedStatement st = null;
        try {

            st = connector.prepareStatement(SQL_SELECT_NOT_RETURNED_CARS);

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClientRequest clientRequest = new ClientRequest();
                fillObject(clientRequest, rs);
                clientRequest.setExpectedReturnDate(rs.getDate("expectedReturnDate"));

                Specification specification = new Specification();
                specification.setModel(rs.getString("model"));

                ComposedRequestSpecification composedRequestSpecification = new ComposedRequestSpecification();
                composedRequestSpecification.setClientRequest(clientRequest);
                composedRequestSpecification.setSpecification(specification);

                notReturnedRequests.add(composedRequestSpecification);
            }
            log.info("Total not returned requests count = " + notReturnedRequests.size());
            rs.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return notReturnedRequests;

    }


    public ArrayList<Composed> findJoinedWithSpecification()
            throws DaoException {
        ArrayList<Composed> requests = new ArrayList<Composed>();

        PreparedStatement st = null;
        try {

            st = connector.prepareStatement(SQL_SELECT_ALL_JOINED_SPECIFICATIONS);

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
            rs.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return requests;

    }

    public int countOpenedRequestsOnCar(int idCar)
            throws DaoException {
        int openedRequestsCount = 0;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_OPENED_REQUESTS_ON_CAR_COUNT);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, idCar);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                openedRequestsCount = rs.getInt("rowsCount");
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return openedRequestsCount;

    }
}
