package com.shoppingapp.demo.shared.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends BaseEntity {

    private List<Recipe> ownedRecipes;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @OneToMany(mappedBy = "owner")
    public List<Recipe> getOwnedRecipes(){
        return ownedRecipes;
    }

    public void setOwnedRecipes(List<Recipe> ownedRecipes){
        this.ownedRecipes = ownedRecipes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
