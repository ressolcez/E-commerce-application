package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.PaginationService;
import pl.britenet.consoleapp.service.UsersService;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import static pl.britenet.consoleapp.Constants.ON;

public class GetAllUsersCommand extends Command{
    private final UsersService usersService;
    public GetAllUsersCommand(UsersService usersService) {
        super(Constants.COMMAND_GETALLUSERS);
        this.usersService = usersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Collection<Users> allRows = this.usersService.getAllUsers().get();
        System.out.println("Podaj ilość wierszy");

        int amount;

        while(true){
            try{
                amount = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        if(amount < 1){
            System.out.println("Liczba rekordów musi być większa od 0");
            return;
        }


        scanner.nextLine();

        PaginationService<Users> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element -> System.out.println(
                      " Id użytkownika: " + element.getId()
                    + " Imie użytkownika: " + element.getName()
                    + " Nazwisko użytkownika: " + element.getSurname()
                    + " Login użytkownika: " + element.getLogin()
                    + " Hasło użytkownika: " + element.getPassword()
                    + " Telefon uzytkownika: " + element.getPhone()
            ));
        }
    }
}


