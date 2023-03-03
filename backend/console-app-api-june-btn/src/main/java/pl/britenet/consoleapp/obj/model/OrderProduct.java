package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class OrderProduct {
    private final int id;
    private int ordersId;
    private int productId;
    private int quantity;
    private double price;

    public OrderProduct(int id){
        this.id = id;
    }

    public OrderProduct(){
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
