package by.bsuir.lab01.service;


import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.FindDao;
import by.bsuir.lab01.entity.Book;

import java.util.List;

public class FindBookService {
	private FindBookService(){}

    public static List<Book> findBooksByAuthor(String author) throws ServiceException {
        List<Book> books = null;
        DaoFactory factory = DaoFactory.getDaoFactory();
        FindDao fileFindDao = factory.getFindDao();

        try {
           return factory.getFindDao().findBookByAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
