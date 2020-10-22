package com.shoppingapp.demo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
