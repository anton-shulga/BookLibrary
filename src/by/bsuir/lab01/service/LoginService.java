package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.file.LoginDao;

/**
 * Created by Антон on 25.10.2015.
 */
public class LoginService {

    private  LoginService(){}

    public static boolean[] login(String data) throws ServiceException {

        DaoFactory factory = DaoFactory.getDaoFactory();
        LoginDao loginDao = factory.getLoginDao();

        try {
            return loginDao.loginUser(data);
        } catch (DaoException e) {
            throw new ServiceException("Login service exception");
        }
    }
}
