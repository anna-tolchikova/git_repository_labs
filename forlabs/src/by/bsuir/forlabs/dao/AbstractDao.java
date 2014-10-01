package by.bsuir.forlabs.dao;

/* реализовать работу с пулом */

import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.subjects.Entity;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao <K, T extends Entity> {

    private static Logger log = Logger.getLogger(AbstractDao.class);

    private WrapperConnector connector;

    public AbstractDao(WrapperConnector connector) {
        this.connector = connector;
    }

    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(K id) throws DaoException;
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);


    public void closeStatement(PreparedStatement st) {
        connector.closeStatement(st);
    }

    public PreparedStatement prepareStatement (String st) throws SQLException {
        return connector.prepareStatement(st);
    }
}
