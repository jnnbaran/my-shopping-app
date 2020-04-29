package com.shoppingapp.demo.shared.repos;

import com.shoppingapp.demo.shared.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


}
