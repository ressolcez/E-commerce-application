package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.UsersBuilder;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.UsersService;

import java.util.Scanner;

public class DeleteUsersCommand extends Command{

    private final UsersService usersService;

    public DeleteUsersCommand(UsersService usersService) {
        super(Constants.COMMAND_DELETE_USERS);
        this.usersService = usersService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID użytkownika:");

        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        if(this.usersService.getUsers(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        Users users = new UsersBuilder(id).getUsers();
        try{

            this.usersService.deleteUsers(users);

        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Usunięto użytkownika");
    }
}
