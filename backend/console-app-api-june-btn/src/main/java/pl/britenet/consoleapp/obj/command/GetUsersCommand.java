package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.UsersService;

import java.util.Optional;
import java.util.Scanner;

public class GetUsersCommand extends Command{

    private final UsersService usersService;

    public GetUsersCommand(UsersService usersService) {
        super(Constants.COMMAND_GET_USERS);
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

        Optional<Users> users = this.usersService.getUsers(id);

        if(users.isPresent()){

            System.out.println("Id użytkownika: " + users.get().getId());
            System.out.println("Imie użytkownika: " + users.get().getName());
            System.out.println("Nazwisko użytkownika: " + users.get().getSurname());
            System.out.println("Telefon użytkownika: " + users.get().getPhone());
            System.out.println("Login użytkownika: " + users.get().getLogin());
            System.out.println("Hasło użytkownika: " + users.get().getPassword());
            System.out.println("Adres użytkownika: " + users.get().getAddress());

        }else{
            System.out.println("Nie ma użytkownika o takim ID");
        }
    }
}
