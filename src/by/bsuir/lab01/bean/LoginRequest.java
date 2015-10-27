package by.bsuir.lab01.bean;

/**
 * Created by Антон on 25.10.2015.
 */
public class LoginRequest extends Request {

    private String userData;

    public String getUserData() {
        return userData;
    }

    public  void setUserData(String  userData){
        this.userData = userData;
    }
}
