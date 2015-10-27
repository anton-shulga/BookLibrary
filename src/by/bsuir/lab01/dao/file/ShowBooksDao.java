package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by Антон on 27.10.2015.
 */
public interface ShowBooksDao {
    public List<Book> getBooks() throws DaoException;
}
