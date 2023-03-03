package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.UsersService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public Optional<Collection<Users>> getAllUsers() {
        return this.usersService.getAllUsers();
    }

    @GetMapping("/{usersId}")
    public Optional<Users> getUsers(@PathVariable int usersId) {
        return this.usersService.getUsers(usersId);
    }

    @PostMapping
    public void insertUsers(@RequestBody Users users) {
        this.usersService.insertUsers(users);
    }

    @PutMapping
    public void updateUsers(@RequestBody Users users) {
        this.usersService.updateUsers(users);
    }

    @DeleteMapping("/{usersId}")
    public void deleteUsers(@PathVariable int usersId) {
        this.usersService.deleteUsers(new Users(usersId));
    }
}
