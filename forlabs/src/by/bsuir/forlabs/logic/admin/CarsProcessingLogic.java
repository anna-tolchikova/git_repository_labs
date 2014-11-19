package by.bsuir.forlabs.logic.admin;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CarDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Car;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarsProcessingLogic {

    public static boolean checkWithRegExp(String carNumber) {
        Pattern p = Pattern.compile("\\d{4}-\\d{2}\\s[A-Za-z]{2}");
        Matcher m = p.matcher(carNumber);
        return m.matches();
    }

    public static void editCar(int idCar, String number)
            throws LogicalException {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            CarDao carDao = new CarDao(wc);
            Car car  = carDao.findEntityById(idCar);
            if (car != null) {
                car.setNumber(number);
                carDao.update(car);
            }
            else {
                throw new LogicalException("Car with id = " + idCar + " doesn't exist");
            }

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

    }

    public static void deleteCar(int idCar)
            throws LogicalException {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            CarDao carDao = new CarDao(wc);
            if (carDao.delete(idCar) == false) {
                throw new LogicalException("Car with id = " + idCar + " doesn't exist");
            }

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

    }

    public static void addCar(String number, int idSpecifications)
            throws LogicalException {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            Car car = new Car();
            car.setNumber(number);
            car.setIdSpecification(idSpecifications);
            CarDao carDao = new CarDao(wc);
            if (carDao.create(car) == 0) {
                throw new LogicalException("Car wasn't added");
            }

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }

    }

}
