package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.file.property.PropertiesLoader;
import by.bsuir.lab01.helper.FileHelper;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                if (login.equals(userData.split(" ")[0]) && password.equals(encryptPassword(userData.split(" ")[1]))) {
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

    private String encryptPassword(String password){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            return generatedPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return generatedPassword;
        }
    }
}
