package com.shoppingapp.demo.shared.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe extends BaseEntity {

    @ManyToOne
    private User owner;

    @NotBlank
    private String name;

    @Column(columnDefinition = "TEXT")
    private String imagePath;

    private String description;

    @ElementCollection
    private List<Ingredient> ingredientsList;
}

