package by.bsuir.forlabs.logic.admin;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CategoryDao;
import by.bsuir.forlabs.dao.SpecificationDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Category;
import by.bsuir.forlabs.subjects.Specification;

import java.util.ArrayList;

public class ShowCarsIndexLogic {

    /**
     *
     * @param idCategory
     * @return Category object or null if object not found
     * @throws LogicalException
     */
    public static Category findCategory(int idCategory)
            throws LogicalException {

        Category category = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            CategoryDao categoryDao = new CategoryDao(wc);
            category = categoryDao.findEntityById(idCategory);
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return category;
    }

    /**
     *
     * @param category - Category class object
     * @return ArrayList<Specification> - set of specifications in category, empty set if nothing was found
     * @throws LogicalException
     */
    public static ArrayList<Specification> findSpecificationsByCategory(Category category)
            throws LogicalException {

        ArrayList<Specification> specifications = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            SpecificationDao specificationDao = new SpecificationDao(wc);
            specifications = specificationDao.findByCategoryWithComputedColumns(category.getId());
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return specifications;
    }


}
