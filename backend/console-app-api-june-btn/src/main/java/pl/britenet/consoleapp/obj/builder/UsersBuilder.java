package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.command.Command;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.Users;

public class UsersBuilder {

    private final Users users;

    public UsersBuilder(int id){
        this.users = new Users(id);
    }

    public UsersBuilder(){
        this.users = new Users();
    }

    public UsersBuilder setName(String name){
        this.users.setName(name);
        return this;
    }

    public UsersBuilder setSurname(String name){
        this.users.setSurname(name);
        return this;
    }

    public UsersBuilder setPhone(String phone){
        this.users.setPhone(phone);
        return this;
    }

    public UsersBuilder setLogin(String login){
        this.users.setLogin(login);
        return this;
    }

    public UsersBuilder setPassword(String password){
        this.users.setPassword(password);
        return this;
    }

    public UsersBuilder setAddress(String address){
        this.users.setAddress(address);
        return this;
    }

    public Users getUsers() {
        return this.users;
    }

}
