package com.recipes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.converters.RecipeRequestToRecipe;
import com.recipes.converters.RecipeToRecipeResponse;
import com.recipes.exception.RecipeNotFoundException;
import com.recipes.model.Ingredient;
import com.recipes.model.Recipe;
import com.recipes.repository.IngredientResponse;
import com.recipes.requests.RecipeRequest;
import com.recipes.responses.RecipeResponse;
import com.recipes.service.RecipeService;

import io.swagger.annotations.ApiOperation;

/**
 * This is controller for RecipesService.
 * 
 * @author Uday
 */
@RestController
@RequestMapping("/api/v1/recipe")
@Validated
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeRequestToRecipe recipeRequestToRecipe;
	@Autowired
	RecipeToRecipeResponse recipeToRecipeResponse;

	
	/**
	 * This method returns the list of recipes.
	 * 
	 * @return List<Recipe> list of recipes.
	 */
	@ApiOperation(value = "Get list of recipes", response = Iterable.class, tags = "Retrieve all recipes")
	@GetMapping("/")
	public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
		List<Recipe> result = recipeService.getAllRecipes();

		if (result.isEmpty()) {
			throw new RecipeNotFoundException("No recipes found");
		}

		List<RecipeResponse> response = new ArrayList<>();
		for (Recipe tRecipe : result) {
			RecipeResponse tResponse = new RecipeResponse();
			tResponse.setId(tRecipe.getId());
			tResponse.setIsVeg(tRecipe.getIsVeg());
			tResponse.setNoOfPeopleSuitableFor(tRecipe.getNoOfPeopleSuitableFor());
			tResponse.setRecipeName(tRecipe.getRecipeName());
			tResponse.setCookingInstructions(tRecipe.getCookingInstructions());
			tResponse.setCreationDate(tRecipe.getCreationDate());

			if (tRecipe.getIngredients() != null && !(tRecipe.getIngredients().isEmpty()) ) {
				for (Ingredient ingredient : tRecipe.getIngredients()) {
					IngredientResponse ingTemp = new IngredientResponse();
					ingTemp.setId(ingredient.getId());
					ingTemp.setDescription(ingredient.getDescription());
					tResponse.getIngredients().add(ingTemp);

				}

			}
			response.add(tResponse);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method returns the recipe details of particular id.
	 * 
	 * @param id the recipe id.
	 * @return List<Recipe> list of recipes.
	 */
	@ApiOperation(value = "Get a recipe details by id", response = Recipe.class, tags = "Retrieve a recipe by id")
	@GetMapping("/{id}")
	public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable("id") int id) {
		Recipe response = recipeService.getRecipeById(id);
		return new ResponseEntity<>(recipeToRecipeResponse.convert(response), HttpStatus.OK);
	}

	/**
	 * This method adds new recipe and returns the same object when it is created
	 * successfully.
	 * 
	 * @param RecipeRequest recipe request.
	 * @return Recipe recipe details.
	 */
	@ApiOperation(value = "Create a recipe", response = Recipe.class, tags = "Add a new recipe")
	@PostMapping("/")
	public ResponseEntity<RecipeResponse> createRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {

		Recipe result = recipeService.createRecipe(recipeRequestToRecipe.convert(recipeRequest));

		return new ResponseEntity<>(recipeToRecipeResponse.convert(result), HttpStatus.CREATED);
	}

	/**
	 * This method updates recipe details and returns the same object when it is
	 * updated successfully.
	 * 
	 * @param RecipeRequest recipe request.
	 * @return boolean.
	 */
	@ApiOperation(value = "Update recipe details", response = Recipe.class, tags = "Update Recipe")
	@PutMapping("/")
	public ResponseEntity<Boolean> updateRecipe(@RequestBody RecipeRequest recipeRequest) {

		boolean result = recipeService.updateRecipe(recipeRequestToRecipe.convert(recipeRequest));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * This method deletes the recipe entity based on given id.
	 * 
	 * @param id recipe id.
	 * @return Boolean.
	 */
	@ApiOperation(value = "Delete a recipe by id", tags = "Delete Recipe")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteRecipe(@PathVariable("id") int id) {
		boolean status = recipeService.deleteRecipeById(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
