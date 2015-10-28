package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.ModificationDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.helper.FileHelper;

import java.io.IOException;
import java.util.List;

public final class FileModificationDao implements ModificationDao {
	private final static FileModificationDao instance = new FileModificationDao();
	
	private static final String fileName = new PropertiesLoader().getBooksFilePath();//you must read it from property file
	
	private FileModificationDao(){}
	
	public static FileModificationDao getInstance(){
		return instance;
	}
	@Override
	public boolean addNewBook(String title) throws DaoException {

        try {
            List<String> stringBooks = FileHelper.readAllFile(fileName);
            for(String stringBook : stringBooks){
                if(stringBook.equals(title))
                    return false;
            }
            FileHelper.append(fileName, title);
        }catch (IOException e){
            throw new DaoException("Dao exception");
        }
		return true;
	}

    public boolean removeBook(String title) throws DaoException {

        try {
            List<String> stringBooks = FileHelper.readAllFile(fileName);
            for(String stringBook : stringBooks){
                if(stringBook.equals(title)){
                    stringBooks.remove(stringBooks.indexOf(stringBook));
                    break;
                }
            }
            FileHelper.clearFile(fileName);
            for (String stringBook : stringBooks)
                FileHelper.append(fileName, stringBook);
        }catch (IOException e){
            throw new DaoException("Dao exception", e);
        }
        return true;
    }

}
