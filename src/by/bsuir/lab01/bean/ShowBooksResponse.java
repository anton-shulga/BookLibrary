package by.bsuir.lab01.bean;

import by.bsuir.lab01.entity.Book;

import java.util.List;

/**
 * Created by Антон on 27.10.2015.
 */
public class ShowBooksResponse extends Response {
    private List<Book> books;

    public List<Book> getBooks(){return books;}

    public void setBooks(List<Book> books){this.books = books;}
}
