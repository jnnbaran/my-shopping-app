package com.shoppingapp.demo.auth;

import com.shoppingapp.demo.auth.jwt.Credentials;
import com.shoppingapp.demo.auth.jwt.JwtProvider;
import com.shoppingapp.demo.auth.jwt.Token;
import com.shoppingapp.demo.shared.exceptions.EmailAlreadyTakenException;
import com.shoppingapp.demo.shared.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;
    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService,
                          JwtProvider jwtProvider,
                          AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Credentials credentials) {
        userService.addUser(credentials);
        return ResponseEntity.ok().body("Witamy w naszej skromnej appce");
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Credentials credentials) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(jwtProvider.generateToken(authentication));
    }

    @ExceptionHandler({ EmailAlreadyTakenException.class })
    public ResponseEntity<String> handleException() {
       return ResponseEntity.badRequest().body("Sorry ale ten email jest juz zajety");
    }

}
