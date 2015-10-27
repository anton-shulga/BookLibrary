package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.NewBookRequest;
import by.bsuir.lab01.bean.NewBookResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.ModificationRepositoryService;
import by.bsuir.lab01.service.ServiceException;

public class AddNewBookCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		// validation
		if (!validationParameters(request)) {
			throw new CommandException("Validation Exception.");
		}

		// call service
		NewBookRequest newBookRequest = (NewBookRequest) request;
		boolean result = false;
		try {
			result = ModificationRepositoryService
					.addNewBookService(newBookRequest.getTitle());
		} catch (ServiceException ex) {
			throw new CommandException("Command message about exception", ex);
		}

		// create RESPONSE
		NewBookResponse response = new NewBookResponse();
		if (result) {
			response.setResultMessage("Success.");
		} else {
			response.setErrorMessage("Can't add the book.");
		}
		return response;
	}

	private boolean validationParameters(Request request) {

        NewBookRequest newBookRequest = (NewBookRequest) request;
        if(newBookRequest.getTitle().split(" ").length == 2) return true;
        else
            return false;
	}

}
