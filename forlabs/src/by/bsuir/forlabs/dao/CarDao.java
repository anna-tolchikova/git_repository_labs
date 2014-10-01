package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.Car;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarDao extends AbstractDao<Integer, Car> {

    private static Logger log = Logger.getLogger(CarDao.class);


    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_FREE_CARS_ON_DATE = "SELECT DISTINCT car.id FROM car " +
            "LEFT JOIN request " +
            "ON  car.id = request.idCar " +
            "WHERE car.idSpecification = ? " +
            "AND ((DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY) < ? " +
            "AND returnDate = null) " +
            "OR isFree = 1)";

    private final static String SQL_SELECT_COUNT_FREE_CARS_ON_DATE = "SELECT COUNT(DISTINCT car.id) " +
            "FROM car " +
            "LEFT JOIN request " +
            "ON  car.id = request.idCar " +
            "WHERE car.idSpecification = ? " +
            "AND ((DATE_ADD(rentalDate, INTERVAL rentalPeriod DAY) < ? " +
            "AND returnDate = null) " +
            "OR isFree = 1)";
    public CarDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Car entity) {
        return false;
    }

    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public Car update(Car entity) {
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

    private void fillObject (Car car, ResultSet rs) throws SQLException {
        car.setId(rs.getInt("id"));
        car.setNumber(rs.getString("number"));
        car.setFree(rs.getBoolean("isFree"));
        car.setIdSpecification(rs.getInt("idSpecification"));
    }

    public ArrayList<Car> findFreeCarsBySpecificationsOnDate(int idSpecification, Date rentalDate) throws DaoException {

        ArrayList<Car> cars = new ArrayList<Car>();
        PreparedStatement st = null;
        try {
            st = prepareStatement(SQL_SELECT_FREE_CARS_ON_DATE);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            st.setInt(1, idSpecification);
            st.setDate(2, (java.sql.Date) rentalDate);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                fillObject(car, rs);
                cars.add(car);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return cars;
    }

    public int countFreeCarsBySpecificationsOnDate(int idSpecification, Date rentalDate) throws DaoException {

        int freeCarsCount;
        PreparedStatement st = null;
        try {
            st = prepareStatement(SQL_SELECT_FREE_CARS_ON_DATE);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            st.setInt(1, idSpecification);
            st.setDate(2, (java.sql.Date) rentalDate);
            ResultSet rs = st.executeQuery();
            rs.next();
            freeCarsCount = rs.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(st);
        }

        return freeCarsCount;
    }

}
