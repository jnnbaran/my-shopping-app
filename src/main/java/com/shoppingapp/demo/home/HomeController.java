package com.shoppingapp.demo.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getEmpty() {
        return "/index.html";
    }

    @GetMapping("/home")
    public String getHome() {
        return "/index.html";
    }

    @GetMapping("/null")
    public String getNull() {
        return "/index.html";
    }
}
