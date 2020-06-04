package com.shoppingapp.demo.user;

import com.shoppingapp.demo.recipe.RecipeDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDTO {


    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
