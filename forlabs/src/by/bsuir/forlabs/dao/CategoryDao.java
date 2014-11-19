package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.Category;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDao extends AbstractDao<Integer, Category> {

    private static Logger log = Logger.getLogger(CategoryDao.class);


    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_BY_ID =
            "select * " +
                    "from category " +
                    "where id = ?";

    public CategoryDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    /**
     *
     * @param id
     * @return Category object, null if not found
     * @throws DaoException
     */

    @Override
    public Category findEntityById(Integer id) throws DaoException {

        Category category = null;
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
                category = new Category();
                fillObject(category, rs);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return category;
    }

    @Override
    public boolean delete(Category entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public int create(Category entity) {
        return 1;
    }

    @Override
    public void update(Category entity) {

    }


    private void fillObject (Category category, ResultSet rs) throws SQLException {
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
    }



}
