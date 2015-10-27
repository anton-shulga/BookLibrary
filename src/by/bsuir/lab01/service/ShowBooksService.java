package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.file.ShowBooksDao;
import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by Антон on 27.10.2015.
 */
public class ShowBooksService {

    private ShowBooksService(){}

    public static List<Book> getBooks() throws ServiceException {
        DaoFactory factory = DaoFactory.getDaoFactory();
        ShowBooksDao showBooksDao =  factory.getShowBooksDao();
        try {
            return showBooksDao.getBooks();
        } catch (DaoException e) {
            throw new ServiceException("Service exception");
        }
    }
}
