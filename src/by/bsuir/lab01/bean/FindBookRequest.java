package by.bsuir.lab01.bean;

/**
 * Created by Антон on 27.10.2015.
 */
public class FindBookRequest extends Request {
    private String author;

    public String getAuthor(){return this.author;}
    public void setAuthor(String author){this.author = author;}
}
