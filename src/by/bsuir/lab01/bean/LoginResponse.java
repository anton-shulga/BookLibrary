package by.bsuir.lab01.bean;

/**
 * Created by Антон on 25.10.2015.
 */
public class LoginResponse extends Response {

    private boolean isAdmin;

    private String resultMessage;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
