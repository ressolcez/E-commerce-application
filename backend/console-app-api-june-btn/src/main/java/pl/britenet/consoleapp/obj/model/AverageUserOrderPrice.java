package pl.britenet.consoleapp.obj.model;

public class AverageUserOrderPrice {
    private final int usersId;
    private String name;
    private String surname;
    private String Phone;
    private Double avgPriceOrder;

    public AverageUserOrderPrice(int usersId){
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Double getAvgPriceOrder() {
        return avgPriceOrder;
    }

    public void setAvgPriceOrder(Double avgPriceOrder) {
        this.avgPriceOrder = avgPriceOrder;
    }
}
