package by.bsuir.lab01.controller.command;

import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.service.ServiceException;

public interface Command {
	Response execute(Request request) throws CommandException, ServiceException;
}
