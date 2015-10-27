package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.bean.ShowBooksRequest;
import by.bsuir.lab01.bean.ShowBooksResponse;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.service.ServiceException;
import by.bsuir.lab01.service.ShowBooksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 27.10.2015.
 */
public class ShowBooksCommand implements Command {

    @Override
    public Response execute(Request request) throws CommandException, ServiceException {
        ShowBooksRequest showBooksRequest = (ShowBooksRequest) request;
        List<Book> response = new ArrayList<>();
        try {
            response = ShowBooksService.getBooks();
        }catch (ServiceException e){
            throw new CommandException("Show books command exception");
        }

        ShowBooksResponse showBooksResponse = new ShowBooksResponse();
        if(response.size() != 0)
            showBooksResponse.setBooks(response);
        else
            showBooksResponse.setErrorMessage("Cant show books");

        return showBooksResponse;
    }
}
