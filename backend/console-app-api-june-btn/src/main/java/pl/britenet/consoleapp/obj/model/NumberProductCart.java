package pl.britenet.consoleapp.obj.model;

public class NumberProductCart {

    private final int usersId;
    private String name;
    private String surname;
    private int numberOfProducts;

    public NumberProductCart(int usersId){
        this.usersId = usersId;
    }

    public int getUsersId() {
        return usersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
