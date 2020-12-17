package com.recipes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.recipes.exception.RecipeNotFoundException;
import com.recipes.model.Recipe;
import com.recipes.repository.RecipeRepository;

/**
 * This is RecipesService.
 * 
 * @author Uday
 */

@Service
public class RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

	@Autowired
	RecipeRepository recipeRepository;

	/**
	 * This method returns all existing recipes.
	 * 
	 * @return List<Recipe> returns list of recipes.
	 */
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipesResponse = new ArrayList<>();
		recipeRepository.findAll().forEach(recipe -> recipesResponse.add(recipe));
		return recipesResponse;
	}

	/**
	 * This method returns recipe details for a particular id.
	 * 
	 * @param id
	 * @return Recipe returns recipe entity.
	 */
	public Recipe getRecipeById(int id) {
		Recipe recipeResponse = null;
		Optional<Recipe> result = recipeRepository.findById(id);
		if (result.isPresent()) {
			recipeResponse = result.get();
		} else {
			throw new RecipeNotFoundException("No Recipe Found :" + id);
		}
		return recipeResponse;
	}

	/**
	 * This method is used to create a recipe.
	 * 
	 * @param Recipe
	 * @return Recipe returns recipe entity.
	 */
	public Recipe createRecipe(Recipe recipe) {
		Recipe recipeResponse = null;
		recipeResponse = recipeRepository.save(recipe);
		return recipeResponse;
	}

	/**
	 * This method is used to update recipe details.
	 * 
	 * @param Recipe
	 * @return boolean returns true/false.
	 */
	public boolean updateRecipe(Recipe recipe) {
		boolean status = false;

		Optional<Recipe> result = recipeRepository.findById(recipe.getId());
		if (result.isPresent()) {
			recipeRepository.save(recipe);
			status = true;
		} else {
			throw new RecipeNotFoundException("No Recipe Found to update :" + recipe.getId());
		}
		return status;
	}

	/**
	 * This method is used to delete recipe details for a particular id.
	 * 
	 * @param id
	 * @return boolean returns true/false.
	 */
	public boolean deleteRecipeById(int id) {
		boolean status = false;

		try {
			recipeRepository.deleteById(id);
			status = true;
		} catch (EmptyResultDataAccessException ex) {
			logger.error(ex.getMessage());
			throw new RecipeNotFoundException("No Recipe Found to delete :" + id);
		}

		return status;
	}

}
