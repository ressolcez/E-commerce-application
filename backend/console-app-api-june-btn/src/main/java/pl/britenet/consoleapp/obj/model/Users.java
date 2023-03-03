package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class Users {

    private final int id;
    private String name;
    private String surname;
    private String phone;
    private String login;
    private String password;
    private String address;

    public Users(int id) {
        this.id = id;
    }

    public Users(){
        this.id = Constants.INVALID_ID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

}
