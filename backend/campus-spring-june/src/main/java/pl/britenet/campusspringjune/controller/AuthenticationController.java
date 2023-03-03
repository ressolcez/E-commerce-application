package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campusspringjune.Service.AuthenticationService;
import pl.britenet.campusspringjune.model.AuthenticationResponse;
import pl.britenet.campusspringjune.model.Credentials;
import pl.britenet.consoleapp.obj.model.Users;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/authentication")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody Credentials credentials) {
        return this.authenticationService.authenticate(credentials);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logutUser(@RequestBody AuthenticationResponse authenticationResponse) {
        boolean isLogout = this.authenticationService.logoutUser(authenticationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(isLogout);
    }

    @GetMapping
    public ResponseEntity<Object> userAuthorization(@RequestHeader("Authorization") String token) {
        if (authenticationService.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    Collections.singletonMap("response", "pass")
            );
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Collections.singletonMap("response", "fail")
            );
        }
    }
}
