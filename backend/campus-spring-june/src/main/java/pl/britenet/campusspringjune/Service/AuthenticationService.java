package pl.britenet.campusspringjune.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campusspringjune.model.AuthenticationResponse;
import pl.britenet.campusspringjune.model.Credentials;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.UsersService;
import java.util.*;

@Service
public class AuthenticationService {
    private final Map<String, Users> authenticatedUsersMap;
    private final UsersService usersService;

    @Autowired
    public AuthenticationService(UsersService usersService) {
        this.usersService = usersService;
        this.authenticatedUsersMap = new HashMap<>();
    }

    public AuthenticationResponse authenticate(Credentials credentials) {
        String username = credentials.getLogin();
        String password = credentials.getPassword();
        Users users;
        try {
            users = this.usersService.getUserByCredentials(username, password).orElseThrow();
        }catch (NoSuchElementException e){
            return new AuthenticationResponse("-1",-1);
        }

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        this.authenticatedUsersMap.put(token, users);

        return new AuthenticationResponse(token, users.getId());
    }

    public boolean logoutUser(AuthenticationResponse authenticationResponse){
        this.authenticatedUsersMap.remove(authenticationResponse.getToken());
        return true;

    }
    public boolean isAuthenticated(String token) {
        return this.authenticatedUsersMap.containsKey(token);
    }

    public Optional<List<Users>> getAllUsers(){
        return  Optional.of(new ArrayList<>(this.authenticatedUsersMap.values()));
    }
}
