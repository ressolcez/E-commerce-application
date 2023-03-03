package pl.britenet.consoleapp.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class PaginationService<T> {
    private int page = 1;
    private int amount;
    private int correctIndex;
    private int totalPages;
    private final Collection<T> listRecords;
    private final int numberOfRows;
    private void setPage(int page){
        this.page = page;
    }
    private void setAmount(int amount){
        this.amount = amount;
    }
    public PaginationService(Collection<T> listRecords, int amount){
        this.listRecords = listRecords;
        this.amount = amount;
        this.numberOfRows = listRecords.size();
    }

    public List<T> PaginationMenu(String command){
        Scanner scanner = new Scanner(System.in);

            if(this.amount > this.numberOfRows){
            System.err.println("Za duża ilość wierszy, wyświetlone zostaną wszystkie wiersze");
                setAmount(this.numberOfRows);
            }else {
                setAmount(this.amount);
            }

        switch (command) {
            case "++":
                setAmount(this.amount = this.amount + 1);
                return doPagination((List<T>) this.listRecords);

            case "--":
                setAmount(this.amount = this.amount - 1);
                return doPagination((List<T>) this.listRecords);

            case "+":
                setPage(this.page = this.page + 1);
                return doPagination((List<T>) this.listRecords);

            case "-":
                setPage(this.page = this.page - 1);
                return doPagination((List<T>) this.listRecords);

            case "=":
                return doPagination((List<T>) this.listRecords);

            case "->":
                System.out.println("Która strone chcesz otworzyc?");
                int insideCommand = scanner.nextInt();
                setPage(insideCommand);
                return doPagination((List<T>) this.listRecords);

            case "exit":
                ON = false;
                return Collections.emptyList();

            case "help":
                System.out.println("+: Zmiana strony w góre\n" +
                        "-: Zmiana strony w dół\n" +
                        "++: Dodanie wiersza na stronie\n" +
                        "--: Odjęcie wiersza na stronie \n" +
                        "->: Skok do podanej strony\n" +
                        "=: Wyświetlenie wierszy");

                return Collections.emptyList();
            default:
                System.out.println("Nie ma takiego polecenia");
                break;
        }
            return Collections.emptyList();
    }

        public List<T> doPagination(List<T> newList){

            int fromIndex = (this.page - 1) * this.amount;

            if(this.amount < 1){
                System.err.println("Nie można wyświetlić ujemnej ilości rekordów");
                setAmount(this.amount + 1);
                setPage(1);
            }else if(this.amount > this.numberOfRows){
                System.err.println("Nie można wyświetlić większej ilości rekordów");
                setAmount(this.amount - 1);
            }else{
                 this.totalPages = (int) Math.ceil((double) this.listRecords.size() / this.amount);
            }

            if (fromIndex < newList.size()) {
                correctIndex = fromIndex;
            }

            if (fromIndex < 0) {
                correctIndex = 0;
            }

            if (this.page > totalPages) {
                setPage(this.page - 1);
                System.err.println("Nie ma więcej stron!!");
            }else if (this.page < 1) {
                setPage(this.page = 1);
                System.err.println("Nie ma mniejszej ilości stron");
            }

            System.out.println(this.page + "/" + this.totalPages);
            return newList.subList(correctIndex, Math.min(correctIndex + this.amount, newList.size()));
    }
}
