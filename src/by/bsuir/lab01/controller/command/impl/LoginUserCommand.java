package by.bsuir.lab01.controller.command.impl;

import by.bsuir.lab01.bean.LoginRequest;
import by.bsuir.lab01.bean.LoginResponse;
import by.bsuir.lab01.bean.Request;
import by.bsuir.lab01.bean.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.file.LoginDao;
import by.bsuir.lab01.service.LoginService;
import by.bsuir.lab01.service.ServiceException;

public class LoginUserCommand implements Command{

	@Override
	public Response execute(Request request) throws CommandException {
		if(!isValidParams(request))
            throw new CommandException("Incorrect data!");
        LoginRequest loginRequest = (LoginRequest) request;
        boolean[] response = new boolean[2];
        try {
            response = LoginService.login(((LoginRequest) request).getUserData());
        } catch (ServiceException e) {
            throw new CommandException("Login command exception");
        }

        LoginResponse loginResponse = new LoginResponse();
        if(response[0]){
            loginResponse.setResultMessage("Success!");
            if(response[1])
                loginResponse.setIsAdmin(true);
            else
                loginResponse.setIsAdmin(false);
        }
        else
            loginResponse.setErrorMessage("Login error");
        return  loginResponse;
	}

    private  boolean isValidParams(Request request)
    {
        LoginRequest loginRequest = (LoginRequest) request;
        String loginData = loginRequest.getUserData();
        if(loginData.split(" ").length == 2)
            return true;
        else
            return false;
    }
}
