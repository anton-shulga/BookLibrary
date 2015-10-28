package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.FindDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.helper.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileFindDao implements FindDao {
	private final static FileFindDao instance = new FileFindDao();
    private static final String fileName = new PropertiesLoader().getBooksFilePath();
    private List<Book> books = new ArrayList<>();
	
	private FileFindDao(){}
	
	public static FileFindDao getInstance(){
		return instance;
	}
	
	public List<Book> findBookByAuthor(String author) throws DaoException{
        try {
            books.clear();
            List<String> stringBooks = FileHelper.readAllFile(fileName);
            for(String stringBook : stringBooks){
                if(stringBook.split(" ")[1].equals(author)) {
                    Book currentBook = new Book(stringBook.split(" ")[0], stringBook.split(" ")[1]);
                    books.add(currentBook);
                }
            }
        }catch (IOException e){
            throw new DaoException("Dao exception", e);
        }
		return books;
	}
}
