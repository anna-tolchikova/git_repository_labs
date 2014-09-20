package by.bsuir.forlabs.logic;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.UserDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.User;


public class LoginLogic {

    public static User checkLogin(String enterLogin, String enterPass) throws LogicalException {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();
        UserDao userDao = new UserDao(wc);
        User user;
        try {
            if ((user = userDao.findByLoginPassword(enterLogin, enterPass)) != null) {
                return user;
            }

        } catch (DaoException e) {
            throw new LogicalException(e);
        } finally {
            pool.releaseConnection(wc);
        }
        return null;

    }

}