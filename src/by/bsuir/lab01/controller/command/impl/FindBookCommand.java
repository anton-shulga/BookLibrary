package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.FindBookRequest;
import by.bsuir.lab01.bean.FindBookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.service.FindBookService;
import by.bsuir.lab01.service.ServiceException;

import java.util.List;

/**
 * Created by Антон on 27.10.2015.
 */
public class FindBookCommand implements Command{
    @Override
    public Response execute(Request request) throws CommandException, ServiceException {
        List<Book> books = null;
        FindBookRequest findBookRequest = (FindBookRequest)request;
        try {
            books = FindBookService.findBooksByAuthor(findBookRequest.getAuthor());
        }catch (ServiceException e) {
            throw  new CommandException("Command exception", e);}

        FindBookResponse response = new FindBookResponse();

        if(books != null) {
            response.setBooks(books);
            return response;
        } else response.setErrorMessage("Can't find books");
        return response;
    }
}
