package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.Specification;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecificationDao extends AbstractDao<Integer, Specification> {

    private static Logger log = Logger.getLogger(SpecificationDao.class);


    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_BY_ID =
            "SELECT * " +
                    "FROM specifications " +
                    "WHERE id = ?";
    private final static String SQL_SELECT_BY_ID_CATEGORY =
            "SELECT * " +
                    "FROM specifications " +
                    "WHERE idCategory = ?";
    private final static String SQL_CREATE =
            "INSERT INTO specifications(idCategory, producer, model, year, transmission, fuelType, engineCapacity, costPerDay, imageContent) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_ID_CATEGORY_WITH_COMPUDET_COLUMNS =
            "SELECT *, COUNT(*) as 'totalCount', SUM(car.isFree) as 'freeCount' " +
                    "FROM specifications " +
                    "INNER JOIN car " +
                    "WHERE specifications.id = car.idSpecification " +
                    "AND idCategory= ? " +
                    "GROUP BY specifications.id";

    private final static String SQL_SELECT_IMAGE_BY_SPECIFICATIONS =
            "SELECT imageContent " +
                    "FROM specifications " +
                    "WHERE id = ?";

    public SpecificationDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<Specification> findAll() {
        return null;
    }

    @Override
    public Specification findEntityById(Integer id) throws DaoException {

        Specification specification = null;
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
                specification = new Specification();
                fillObject(specification, rs);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return specification;
    }

    @Override
    public boolean delete(Specification entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public int create(Specification specification) throws DaoException {
        int createdId = 0;
        PreparedStatement st;
        try {
            st = connector.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, specification.getIdCategory());
            st.setString(2, specification.getProducer());
            st.setString(3, specification.getModel());
            st.setInt(4, specification.getYear());
            st.setString(5, specification.getTransmission());
            st.setString(6, specification.getFuelType());
            st.setInt(7, specification.getEngineCapacity());
            st.setFloat(8, specification.getCostPerDay());
            st.setBinaryStream(9, specification.getImage().getInputStream(), specification.getImage().getSize());
            st.executeUpdate();
            ResultSet keyset = st.getGeneratedKeys();
            if(keyset.next()) {
                createdId = keyset.getInt(1);
                log.info("created id = " + createdId);
            }

        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }
        return createdId;
    }

    @Override
    public void update(Specification entity) {

    }

    private void fillObject(Specification specification, ResultSet rs) throws SQLException {
        specification.setId(rs.getInt("id"));
        specification.setIdCategory(rs.getInt("idCategory"));
        specification.setProducer(rs.getString("producer"));
        specification.setModel(rs.getString("model"));
        specification.setYear(rs.getInt("year"));
        specification.setTransmission(rs.getString("transmission"));
        specification.setFuelType(rs.getString("fuelType"));
        specification.setEngineCapacity(rs.getInt("engineCapacity"));
        specification.setCostPerDay(rs.getFloat("costPerDay"));
    }

    private void fillComputedColumns(Specification specification, ResultSet rs) throws SQLException {
        specification.setTotalCount(rs.getInt("totalCount"));
        specification.setFreeCount(rs.getInt("freeCount"));
    }

    /**
     *
     * @param idCategory
     * @return ArrayList<Specification> - set of specifications in category, empty set if nothing was found
     * @throws DaoException
     */
    public ArrayList<Specification> findByCategory(int idCategory) throws DaoException {

        ArrayList<Specification> specifications = new ArrayList<Specification>();
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_BY_ID_CATEGORY);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, idCategory);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Specification specification = new Specification();
                fillObject(specification, rs);
                specifications.add(specification);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return specifications;
    }

    /**
     *
     * @param idCategory
     * @return ArrayList<Specification> - set of specifications in category, empty set if nothing was found
     * @throws DaoException
     */
    public ArrayList<Specification> findByCategoryWithComputedColumns(int idCategory) throws DaoException {

        ArrayList<Specification> specifications = new ArrayList<>();
        PreparedStatement st;
        try {
            st = connector.prepareStatement(SQL_SELECT_BY_ID_CATEGORY_WITH_COMPUDET_COLUMNS);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, idCategory);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Specification specification = new Specification();
                fillObject(specification, rs);
                fillComputedColumns(specification, rs);
                specifications.add(specification);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return specifications;
    }

    /**
     *
     * @param idSpecifications
     * @return Blob object - image from db or null if image in specified specifications not found
     * @throws DaoException
     */
    public Blob findImageBySpecificationId(int idSpecifications) throws DaoException {
        Blob image = null;
        PreparedStatement st;
        try {
            st = connector.prepareStatement(SQL_SELECT_IMAGE_BY_SPECIFICATIONS);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            st.setInt(1, idSpecifications);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                image = rs.getBlob("imageContent");
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }
        return image;
    }
}
