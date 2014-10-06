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

    private final static int NEW_REQUESTS_STATUS = 1;

    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_FREE_CARS_ON_DATE_BY_SPEC = 
            "SELECT * FROM car " +
                    "WHERE (isFree=1 " +
                    "AND idSpecification = ?) " +
                    "OR " +
                    "id IN ( " +
                    "    SELECT idCar FROM request " +
                    "    WHERE idSpecification = ?  " +
                    "    AND returnDate IS NULL " +
                    "    AND (DATEDIFF(?, rentalDate) > rentalPeriod  " +
                    "         OR " +
                    "         DATEDIFF(rentalDate, ?) >= ? " +
                    "        )" +
                    "    AND idStatus<>" + NEW_REQUESTS_STATUS +
                    ")";

    private final static String SQL_SELECT_COUNT_FREE_CARS_ON_DATE_BY_SPEC =
            "SELECT COUNT(*) FROM car " +
                    "WHERE (isFree=1 " +
                    "AND idSpecification = ?) " +
                    "OR " +
                    "id IN ( " +
                    "    SELECT idCar FROM request " +
                    "    WHERE idSpecification = ?  " +
                    "    AND returnDate IS NULL " +
                    "    AND (DATEDIFF(?, rentalDate) > rentalPeriod  " +
                    "         OR " +
                    "         DATEDIFF(rentalDate, ?) >= ? " +
                    "        )" +
                    "    AND idStatus<>" + NEW_REQUESTS_STATUS +
                    ")";

    private final static String SQL_UPDATE =
            "UPDATE car " +
                    "SET isFree = ? " +
                    "WHERE id = ?";

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
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public void update(Car entity) throws DaoException {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_UPDATE);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            st.setBoolean(1, entity.isFree());
            st.setInt(2, entity.getId());
            log.info("update Car. Affected rows count = " + st.executeUpdate());

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private void fillObject (Car car, ResultSet rs) throws SQLException {
        car.setId(rs.getInt("id"));
        car.setNumber(rs.getString("number"));
        car.setFree(rs.getBoolean("isFree"));
        car.setIdSpecification(rs.getInt("idSpecification"));
    }

    public ArrayList<Car> findFreeCarsBySpecificationsOnDate(int idSpecification, Date rentalDate, int rentalPeriod )
            throws DaoException {

        ArrayList<Car> cars = new ArrayList<Car>();
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_FREE_CARS_ON_DATE_BY_SPEC);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            st.setInt(1, idSpecification);
            st.setInt(2, idSpecification);
            st.setDate(3, (java.sql.Date) rentalDate);
            st.setDate(4, (java.sql.Date) rentalDate);
            st.setInt(5, rentalPeriod);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                fillObject(car, rs);
                cars.add(car);
            }
            rs.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return cars;
    }

    public int countFreeCarsBySpecificationsOnDate(int idSpecification, Date rentalDate, int rentalPeriod)
            throws DaoException {

        int freeCarsCount = 0;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_COUNT_FREE_CARS_ON_DATE_BY_SPEC);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {

            st.setInt(1, idSpecification);
            st.setInt(2, idSpecification);
            st.setDate(3, (java.sql.Date) rentalDate);
            st.setDate(4, (java.sql.Date) rentalDate);
            st.setInt(5, rentalPeriod);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                freeCarsCount = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }

        return freeCarsCount;
    }

}
