package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.UsersBuilder;
import pl.britenet.consoleapp.obj.model.Users;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class UsersService {

    private final DatabaseService databaseService;
    public UsersService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Users> getUserByCredentials(String login, String password) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM users WHERE login='%s' AND password='%s'", login, password),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int id = resultSet.getInt("Users_id");
                            String name = resultSet.getString("Name");
                            String surname = resultSet.getString("Surname");
                            String phone = resultSet.getString("Phone");
                            String addres = resultSet.getString("Address");

                            return new UsersBuilder(id)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setPhone(phone)
                                    .setAddress(addres)
                                    .setLogin(login)
                                    .setPassword(password)
                                    .getUsers();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }

    public  Optional<Collection<Users>> getAllUsers() {

         return this.databaseService.performSQL(
                "SELECT * FROM users",
                resultSet -> {
                    try {
                        Collection<Users> allUsers = new LinkedList<>();

                        while(resultSet.next()) {

                            int id = resultSet.getInt("users_id");
                            String name = resultSet.getString("name");
                            String surname = resultSet.getString("surname");
                            String phone = resultSet.getString("phone");
                            String login = resultSet.getString("login");
                            String password = resultSet.getString("password");
                            String address = resultSet.getString("address");

                            Users users = new UsersBuilder(id)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setPhone(phone)
                                    .setLogin(login)
                                    .setPassword(password)
                                    .setAddress(address)
                                    .getUsers();

                            allUsers.add(users);

                        }

                        return allUsers;

                    } catch (SQLException e) {
                        e.getMessage();
                    }
                    return null;
                }
        );
    }

    public Optional<Users> getUsers(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM users WHERE users_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            String name = resultSet.getString("name");
                            String surname = resultSet.getString("surname");
                            String phone = resultSet.getString("phone");
                            String login = resultSet.getString("login");
                            String password = resultSet.getString("password");
                            String address = resultSet.getString("address");

                            return new UsersBuilder(id)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setPhone(phone)
                                    .setLogin(login)
                                    .setPassword(password)
                                    .setAddress(address)
                                    .getUsers();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public void insertUsers(Users users) {
        this.databaseService.performDML(
                String.format("INSERT INTO Users (name,surname,phone,login,password,address) " +
                                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                        users.getName(),
                        users.getSurname(),
                        users.getPhone(),
                        users.getLogin(),
                        users.getPassword(),
                        users.getAddress())
        );
    }

    public void updateUsers(Users users) {
        this.databaseService.performDML(
                String.format("UPDATE users SET name='%s', surname = '%s' ,phone='%s', login = '%s', password='%s', address='%s' WHERE Users_id='%d'",
                        users.getName(),
                        users.getSurname(),
                        users.getPhone(),
                        users.getLogin(),
                        users.getPassword(),
                        users.getAddress(),
                        users.getId())
        );
    }

    public void deleteUsers(Users users) {

        this.databaseService.performDML(
                String.format("DELETE FROM users WHERE Users_id='%d'",
                        users.getId())
        );
    }
}
