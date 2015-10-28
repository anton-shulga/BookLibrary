package by.bsuir.lab01.dao.file.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Антон on 26.10.2015.
 */
public class PropertiesLoader  {
    public static final String PROPERTIES_FILE = "resources/config/config.properties";

    private InputStream inputStream;
    private static Properties properties;

    {
        try {
            properties = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);

            if (inputStream != null) {
                properties.load(inputStream);
            }
            else {
                throw new FileNotFoundException("property file '" + PROPERTIES_FILE + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUserDataFilePath(){ return properties.getProperty("usersDataFilePath");}
    public static String getBooksFilePath(){return properties.getProperty("booksFilePath");}
    public static String getDaoType(){return properties.getProperty("daoType");}

}
