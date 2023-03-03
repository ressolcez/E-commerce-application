package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.UsersBuilder;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.UsersService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateUsersCommand extends Command{

    private final UsersService usersService;

    public UpdateUsersCommand(UsersService usersService) {
        super(Constants.COMMAND_UPDATE_USERS);
        this.usersService = usersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
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

        System.out.println("Wprowadź imie użytkownika:");
        scanner.nextLine();
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

        Users users = new UsersBuilder(id)
                .setName(name)
                .setSurname(surname)
                .setLogin(login)
                .setPhone(phone)
                .setPassword(password)
                .setAddress(address)
                .getUsers();
        this.usersService.updateUsers(users);

        System.out.println("Użytkownik został zmodyfikowany!");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
