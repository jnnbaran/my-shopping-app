package com.shoppingapp.demo.auth;

import com.shoppingapp.demo.auth.jwt.Credentials;
import com.shoppingapp.demo.auth.jwt.JwtProvider;
import com.shoppingapp.demo.auth.jwt.Token;
import com.shoppingapp.demo.shared.exceptions.EmailAlreadyTakenException;
import com.shoppingapp.demo.profile.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String EMAIL_TAKEN = "Niestety ale ten email jest już zajęty";
    private static final String CREDENTIALS_CANNOT_BE_BLANK = "Email i hasło nie mogą być puste";

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

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody Credentials credentials) {
        userService.addUser(credentials);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Credentials credentials) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),
                        credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(jwtProvider.generateToken(authentication));
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleException() {
        return ResponseEntity.badRequest().body(EMAIL_TAKEN);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleCredentialsConstraintException() {
        return ResponseEntity.badRequest().body(CREDENTIALS_CANNOT_BE_BLANK);
    }
}
