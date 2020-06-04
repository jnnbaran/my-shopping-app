package com.shoppingapp.demo.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoginController {

    @PostMapping("/login")
    public String showMyLoginPage(String email, String password){
        System.out.println(email);
        return  "login.component.html";
    }


}
