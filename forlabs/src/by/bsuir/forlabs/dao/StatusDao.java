package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.Status;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatusDao extends AbstractDao<Integer, Status> {

    private static Logger log = Logger.getLogger(StatusDao.class);


    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_BY_ID = "select * from requestStatus where id = ?";

    public StatusDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<Status> findAll() {
        return null;
    }

    @Override
    public Status findEntityById(Integer id) throws DaoException {

        Status status = null;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_BY_ID);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                status = new Status();
                fillObject(status, rs);

            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return status;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Status entity) throws DaoException {
        return false;
    }

    @Override
    public int create(Status entity) {
        return 1;
    }

    @Override
    public void update(Status entity) {

    }

    private void fillObject (Status status, ResultSet rs) throws SQLException {
        status.setId(rs.getInt("id"));
        status.setName(rs.getString("name"));
        status.setDescription(rs.getString("description"));
    }

}
