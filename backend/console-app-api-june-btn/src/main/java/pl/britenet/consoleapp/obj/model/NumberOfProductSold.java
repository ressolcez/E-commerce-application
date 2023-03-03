package pl.britenet.consoleapp.obj.model;

public class NumberOfProductSold {
    private final int productId;
    private String name;
    private int numberOfSold;

    public NumberOfProductSold (int productId){
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSold() {
        return numberOfSold;
    }

    public void setNumberOfSold(int numberOfSold) {
        this.numberOfSold = numberOfSold;
    }
}
