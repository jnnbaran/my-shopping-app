package com.shoppingapp.demo.shared.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @OneToMany(mappedBy = "owner")
    private List<Recipe> ownedRecipes;

    private String firstName;

    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
