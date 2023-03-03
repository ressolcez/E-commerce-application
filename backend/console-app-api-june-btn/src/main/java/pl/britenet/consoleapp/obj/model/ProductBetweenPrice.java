package pl.britenet.consoleapp.obj.model;

public class ProductBetweenPrice {
    private final int id;
    private String name;
    private double price;

    public ProductBetweenPrice(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
