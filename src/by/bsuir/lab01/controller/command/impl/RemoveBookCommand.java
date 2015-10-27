package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.RemoveBookRequest;
import by.bsuir.lab01.bean.RemoveBookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.ModificationRepositoryService;
import by.bsuir.lab01.service.ServiceException;

/**
 * Created by Антон on 27.10.2015.
 */
public class RemoveBookCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        RemoveBookRequest removeBookRequest = (RemoveBookRequest) request;
        boolean result = false;

        try {
            result = ModificationRepositoryService.removeBookService(removeBookRequest.getTitle());
        } catch (ServiceException e) {
            throw new CommandException("Command exception", e);
        }

        RemoveBookResponse removeBookResponse = new RemoveBookResponse();

        if(result) removeBookResponse.setResultMessage("Success");
        else removeBookResponse.setErrorMessage("Can't remove book");

        return removeBookResponse;
    }
}
