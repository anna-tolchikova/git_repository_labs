package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.Specification;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpecificationDao extends AbstractDao<Integer, Specification> {

    private static Logger log = Logger.getLogger(SpecificationDao.class);


    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_BY_ID = "select * from specifications where id = ?";

    public SpecificationDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<Specification> findAll() {
        return null;
    }

    private void fillObject(Specification specification, ResultSet rs) throws SQLException {
        specification.setId(rs.getInt("id"));
        specification.setProducer(rs.getString("producer"));
        specification.setModel(rs.getString("model"));
        specification.setYear(rs.getInt("year"));
        specification.setTransmission(rs.getString("transmission"));
        specification.setFuelType(rs.getString("fuelType"));
        specification.setEngineCapacity(rs.getInt("engineCapacity"));
        specification.setCostPerDay(rs.getFloat("costPerDay"));
        specification.setImage(rs.getString("image"));
    }

    @Override
    public Specification findEntityById(Integer id) throws DaoException {

        Specification specification = null;
        PreparedStatement st = null;
        try {
            st = prepareStatement(SQL_SELECT_BY_ID);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                specification = new Specification();
                fillObject(specification, rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return specification;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Specification entity) {
        return false;
    }

    @Override
    public boolean create(Specification entity) {
        return false;
    }

    @Override
    public Specification update(Specification entity) {
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

}
