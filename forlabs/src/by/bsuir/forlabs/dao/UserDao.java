package by.bsuir.forlabs.dao;

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractDao<Integer, User> {

    private static Logger log = Logger.getLogger(UserDao.class);


    private final static String SQL_SELECT_ALL = "";
    private final static String SQL_SELECT_BY_LOGIN_PASSWORD = "select * from user where login = ? and password = ?";

    public UserDao(WrapperConnector connector) {
        super(connector);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void close(PreparedStatement st) {
        super.close(st);
    }

    public User findByLoginPassword (String login, String password) throws DaoException {
        User user = null;

        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SQL_SELECT_BY_LOGIN_PASSWORD);
        } catch (SQLException e) {
            throw new DaoException(e);
        }


        try {
            st.setString(1, login);

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String passwordMd5 = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(passwordMd5.length() < 32 ){
                passwordMd5 = "0"+passwordMd5;
            }

            log.info("result md5 = " + passwordMd5);

            st.setString(2, passwordMd5);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                user = new User();
                log.info("SQL_SELECT_BY_LOGIN_PASSWORD has result");
                user.setId(rs.getInt("id"));
                user.setLogin(login);
                user.setPassword(passwordMd5);
                user.setIdRole(rs.getInt("idRole"));
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DaoException(e);
        } finally {
            connector.closeStatement(st);
        }
        return user;
    }
}
