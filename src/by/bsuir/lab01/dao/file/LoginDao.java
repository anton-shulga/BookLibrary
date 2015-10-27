package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;

/**
 * Created by Антон on 26.10.2015.
 */
public interface LoginDao {
    public boolean[] loginUser(String userData) throws DaoException;
}