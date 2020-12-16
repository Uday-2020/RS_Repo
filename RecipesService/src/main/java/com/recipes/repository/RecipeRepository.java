package com.recipes.repository;


/**
 * This is RecipesRepository Interface
 * @author Uday
 */
import org.springframework.data.repository.CrudRepository;

import com.recipes.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{
	
}