package by.bsuir.lab01.bean;

/**
 * Created by Антон on 27.10.2015.
 */
public class RemoveBookRequest extends Request {
    private String title;

    public String getTitle() {
        return title;
    }

    public  void setTitle(String  title){
        this.title = title;
    }
}
