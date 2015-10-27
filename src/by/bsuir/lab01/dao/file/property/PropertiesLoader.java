package by.bsuir.lab01.dao.file.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Антон on 26.10.2015.
 */
public class PropertiesLoader  {
    private static final String PROPERTIES_FILE_PATH = "resources/config/config.properties";
    private static Properties properties;
    private InputStream inputStream;

    {
        try {
            properties = new Properties();
            inputStream.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH);

            if (inputStream != null)
                properties.load(inputStream);
            else
                throw new FileNotFoundException("Property file " + PROPERTIES_FILE_PATH + " not found");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getUserDataFilePath(){ return properties.getProperty("booksFilePath");}
    public static String getBooksFilePath(){return properties.getProperty("usersDataFilePath");}
    public static String getDaoType(){return properties.getProperty("daoType");}

}
