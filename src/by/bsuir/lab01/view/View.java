package by.bsuir.lab01.view;

import by.bsuir.lab01.bean.*;
import by.bsuir.lab01.controller.BookController;
import by.bsuir.lab01.controller.command.CommandName;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.helper.ConsoleHelper;

import java.io.IOException;
import java.util.List;

public class View {

	private BookController controller = new BookController();
    private boolean isAdmin = false;
    private boolean isExecuted = false;
    private CommandName commandName = null;

	public void menu() {

        ConsoleHelper.write("Hello! It's your console book library!");

        do {
            ConsoleHelper.write("Enter your login and password:");
            LoginRequest request = new LoginRequest();
            request.setUserData(ConsoleHelper.read());
            request.setCommandName(CommandName.LOGIN_USER.toString());
            Response response = controller.executeRequest(request);

            if (response.getErrorMessage() != null) {
                ConsoleHelper.write(response.getErrorMessage());
            } else {
                LoginResponse loginResponse = (LoginResponse) response;
                isAdmin = loginResponse.isAdmin();
                ConsoleHelper.write(loginResponse.getResultMessage());
                isExecuted = true;
            }
        } while (!isExecuted);

        do {
            if (isAdmin) {
                ConsoleHelper.write("You are administrator");

                try {
                    commandName = getAdminsCommandName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                ConsoleHelper.write("You are user");
                try {
                    commandName = getUsersCommandName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            switch (commandName) {
                case SHOW_BOOKS: showBooks(commandName); break;
                case ADD_NEW_BOOK: addNewBook(commandName);break;
                case REMOVE_BOOK: removeBook(commandName);break;
                case FIND_BOOK: findBook(commandName);break;
                default:
            }
        } while (commandName != CommandName.EXIT);
    }

    public CommandName getUsersCommandName() throws IOException {
        ConsoleHelper.write(String.format("\t %d - show all books", CommandName.SHOW_BOOKS.ordinal()));
        ConsoleHelper.write(String.format("\t %d - find books by author", CommandName.FIND_BOOK.ordinal()));
        ConsoleHelper.write(String.format("\t %d - exit", CommandName.EXIT.ordinal()));

        int commandNumber = ConsoleHelper.readInt();
        while (commandNumber > CommandName.values().length - 1) {
            ConsoleHelper.write("Wrong number of command");
            commandNumber = ConsoleHelper.readInt();
        }

        return CommandName.values()[commandNumber];
    }

    public CommandName getAdminsCommandName() throws IOException {
        ConsoleHelper.write(String.format("\t %d -- show all books", CommandName.SHOW_BOOKS.ordinal()));
        ConsoleHelper.write(String.format("\t %d -- find books by author", CommandName.FIND_BOOK.ordinal()));
        ConsoleHelper.write(String.format("\t %d -- add new book", CommandName.ADD_NEW_BOOK.ordinal()));
        ConsoleHelper.write(String.format("\t %d -- remove book", CommandName.REMOVE_BOOK.ordinal()));
        ConsoleHelper.write(String.format("\t %d -- exit", CommandName.EXIT.ordinal()));

        int commandNumber = ConsoleHelper.readInt();
        while (commandNumber > CommandName.values().length - 1) {
            ConsoleHelper.write("Enter correct number of command:");
            commandNumber = ConsoleHelper.readInt();
        }
        return CommandName.values()[commandNumber];
    }

    public void showBooks(CommandName commandName) {
        ShowBooksRequest request = new ShowBooksRequest();
        request.setCommandName(commandName.toString());

        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null){
            ConsoleHelper.write(response.getErrorMessage());
        }else {
            ShowBooksResponse viewBooksResponse = (ShowBooksResponse) response;
            List<Book> books = viewBooksResponse.getBooks();
            ConsoleHelper.write("---------------LIST OF BOOKS:--------------");
            for (Book book : books) {
                ConsoleHelper.write("Title: " + book.getTitle() + " - " + "Author: " + book.getAuthor());
            }
            ConsoleHelper.write("-------------------------------------------");
        }
    }

    public void removeBook(CommandName commandName){
        RemoveBookRequest request = new RemoveBookRequest();
        request.setCommandName(commandName.toString());

        ConsoleHelper.write("Enter title and author of book:");
        request.setTitle(ConsoleHelper.read());
        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null)
            ConsoleHelper.write(response.getErrorMessage());
        else {
            RemoveBookResponse removeBookResponse = (RemoveBookResponse) response;
            ConsoleHelper.write(removeBookResponse.getResultMessage());
        }
    }

    public void addNewBook(CommandName commandName)
    {
        NewBookRequest request = new NewBookRequest();
        request.setCommandName(commandName.toString());
        ConsoleHelper.write("Enter title and author of book:");
        request.setTitle(ConsoleHelper.read());

        Response response = controller.executeRequest(request);
        if(response.getErrorMessage() != null)
            ConsoleHelper.write(response.getErrorMessage());
        else {
            NewBookResponse newBookResponse = (NewBookResponse) response;
            ConsoleHelper.write(newBookResponse.getResultMessage());
        }

    }

    public void findBook(CommandName commandName){
        ConsoleHelper.write("Enter author of book:");
        FindBookRequest request = new FindBookRequest();
        request.setCommandName(commandName.toString());

        request.setAuthor(ConsoleHelper.read());

        Response response = controller.executeRequest(request);
        if (response.getErrorMessage() != null)
            ConsoleHelper.write(response.getErrorMessage());
        else {
            FindBookResponse findBookResponse = (FindBookResponse) response;
            ConsoleHelper.write("Found " + findBookResponse.getBooks().size() + " books: ");
            for(Book book : findBookResponse.getBooks()){
                ConsoleHelper.write("Title: " + book.getTitle() + " - " + "Author: " + book.getAuthor());
            }

        }
    }
}



