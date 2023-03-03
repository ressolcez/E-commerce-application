package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;

import pl.britenet.consoleapp.obj.builder.UsersBuilder;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.UsersService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertUsersCommand extends Command{

    private final UsersService usersService;

    public InsertUsersCommand(UsersService usersService) {
        super(Constants.COMMAND_INSERT_USERS);
        this.usersService = usersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try {
            System.out.println("Wprowadź imie użytkownika:");
            String name = scanner.nextLine();

            System.out.println("Wprowadź nazwisko użytkownika:");
            String surname = scanner.nextLine();

            System.out.println("Wprowadź telefon użytkownika:");
            String phone = scanner.nextLine();

            System.out.println("Wprowadź login użytkownika:");
            String login = scanner.nextLine();

            System.out.println("Wprowadź hasło użytkownika:");
            String password = scanner.nextLine();

            System.out.println("Wprowadź adres użytkownika:");
            String address = scanner.nextLine();

            Users users = new UsersBuilder()
                    .setName(name)
                    .setSurname(surname)
                    .setLogin(login)
                    .setPhone(phone)
                    .setPassword(password)
                    .setAddress(address)
                    .getUsers();
            this.usersService.insertUsers(users);

            System.out.println("Użytkownik został dodany!");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }

    }
}
