package by.bsuir.lab01.controller.command;

import by.bsuir.lab01.controller.command.impl.AddNewBookCommand;
import by.bsuir.lab01.controller.command.impl.LoginUserCommand;
import by.bsuir.lab01.controller.command.impl.RemoveBookCommand;
import by.bsuir.lab01.controller.command.impl.ShowBooksCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandHelper(){
		commands.put(CommandName.ADD_NEW_BOOK, new AddNewBookCommand());
		commands.put(CommandName.LOGIN_USER, new LoginUserCommand());
        commands.put(CommandName.SHOW_BOOKS, new ShowBooksCommand());
        commands.put(CommandName.REMOVE_BOOK, new RemoveBookCommand());
	}	
	
	public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		return commands.get(command);		
	}
}
