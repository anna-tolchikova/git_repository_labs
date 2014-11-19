package by.bsuir.forlabs.logic.admin;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CarDao;
import by.bsuir.forlabs.dao.CategoryDao;
import by.bsuir.forlabs.dao.SpecificationDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Car;
import by.bsuir.forlabs.subjects.Category;
import by.bsuir.forlabs.subjects.Specification;

import java.util.ArrayList;

public class ShowSpecificationsIndexLogic {

    /**
     *
     * @param idSpecification
     * @return Specification object found by id or null if nothing was found
     * @throws LogicalException
     */
    public static Specification findSpecification(int idSpecification)
            throws LogicalException {

        Specification specification = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            SpecificationDao specificationDao = new SpecificationDao(wc);
            specification = specificationDao.findEntityById(idSpecification);
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return specification;
    }

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
     * @param specification
     * @return set of cars belong specifications, empty set if nothing was found
     * @throws LogicalException / IllegalArgumentException
     */
    public static ArrayList<Car> findCars(Specification specification)
            throws LogicalException {

        if (specification == null) {
            throw new IllegalArgumentException("Parameter 'specification' cannot be null");
        }
        ArrayList<Car> carsInSpecifications;
        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        try {
            CarDao carDao = new CarDao(wc);
            carsInSpecifications = carDao.findBySpecifications(specification.getId());
        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

        return carsInSpecifications;
    }


}
