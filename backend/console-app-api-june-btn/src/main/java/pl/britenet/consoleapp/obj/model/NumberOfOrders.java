package pl.britenet.consoleapp.obj.model;


public class NumberOfOrders {
    private final int usersId;
    private String name;
    private String surrname;
    private String phone;
    private int numberOfOrders;

    public NumberOfOrders(int usersId){
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

    public String getSurrname() {
        return surrname;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
