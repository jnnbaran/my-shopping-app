package com.shoppingapp.demo.shared.repos;

import com.shoppingapp.demo.shared.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByOwner_Id(Long OwnerId);
    void deleteAllByOwner_Id(Long OwnerId);
}
