package com.shoppingapp.demo.auth;


import com.shoppingapp.demo.home.Credentials;
import com.shoppingapp.demo.shared.exceptions.EmailAlreadyTakenException;
import com.shoppingapp.demo.shared.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private UserService userService;

    public AuthController( UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Credentials credentials) {
        try {

            userService.addUser(credentials);
        } catch (EmailAlreadyTakenException e) {
           return ResponseEntity.badRequest().body("Sorry ale ten email jest juz zajety");
        }
        return ResponseEntity.ok().body("Witamy w naszej skromnej appce");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
        if(userService.validateUser(credentials)) {
            return ResponseEntity.ok().body("Witamy w naszej skromnej appce");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Złe hasło hakerze");
    }

}
