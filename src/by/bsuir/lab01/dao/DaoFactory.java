package by.bsuir.lab01.dao;

import by.bsuir.lab01.dao.factoryimpl.FileDaoFactory;
import by.bsuir.lab01.dao.file.LoginDao;
import by.bsuir.lab01.dao.file.ShowBooksDao;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;

public abstract class DaoFactory {
	private static final String DAO_TYPE =  new PropertiesLoader().getDaoType();          //you must read it from property file
	
	public static DaoFactory getDaoFactory(){
		switch (DAO_TYPE){
		case "file":
			return FileDaoFactory.getInstance();
		}
		return null;
	}

	public abstract FindDao getFindDao();
	public abstract ModificationDao getModificationDao();
    public abstract LoginDao getLoginDao();
    public abstract ShowBooksDao getShowBooksDao();
}
