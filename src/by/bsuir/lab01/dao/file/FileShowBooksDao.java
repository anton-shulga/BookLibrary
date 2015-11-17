package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.helper.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 27.10.2015.
 */
public class FileShowBooksDao implements ShowBooksDao {
    private static final FileShowBooksDao instance = new FileShowBooksDao();
    private List<String> books =  new ArrayList<>();
    private List<Book> response = new ArrayList<>();
    private static String userDataFilePath = new PropertiesLoader().getBooksFilePath();

    private FileShowBooksDao(){}

    public static FileShowBooksDao getInstance(){return  instance;}

    @Override
    public List<Book> getBooks() throws DaoException {
        try {
            response.clear();
            books = FileHelper.readAllFile(userDataFilePath);
            for(String stringBook : books){
                Book currentBook = new Book(stringBook.split(" ")[0], stringBook.split(" ")[1]);
                response.add(currentBook);
            }
        } catch (IOException e) {
            throw new DaoException("Dao exception " + e.getMessage());
        }

        return response;
    }
}
