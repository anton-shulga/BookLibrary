package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.helper.FileHelper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Антон on 26.10.2015.
 */
public final class FileLoginDao implements LoginDao {
    private final static FileLoginDao instance = new FileLoginDao();
    private static String userDataFilePath = new PropertiesLoader().getUserDataFilePath();

    private FileLoginDao(){}

    public static FileLoginDao getInstance(){
        return instance;
    }

    @Override
    public boolean[] loginUser(String userData) throws DaoException {

        boolean[] response = new boolean[2];
        try {
            List<String> usersData = FileHelper.readAllFile(userDataFilePath);
            for (String line : usersData) {
                String login = line.split(" ")[0];
                String password = line.split(" ")[1];
                if (login.equals(userData.split(" ")[0]) && password.equals(userData.split(" ")[1])) {
                    response[0] = true;
                    if (userData.split(" ")[1].equals("admin"))
                        response[1] = true;
                    break;
                }
            }
            return response;
        }catch (IOException e){
            throw new DaoException("Dao exception " + e.getMessage());
        }
    }
}
