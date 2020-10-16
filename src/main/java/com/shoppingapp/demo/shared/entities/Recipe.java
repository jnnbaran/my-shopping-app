package com.shoppingapp.demo.shared.entities;

import com.shoppingapp.demo.shared.model.Ingredient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe extends BaseEntity {

    @ManyToOne
    private User owner;

    private String name;

    private String description;

    @ElementCollection
    private List<Ingredient> ingredientsList;
}

