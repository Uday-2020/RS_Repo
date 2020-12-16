package com.recipes.converters;

import org.springframework.stereotype.Component;

import com.recipes.model.Ingredient;
import com.recipes.model.Recipe;
import com.recipes.requests.RecipeRequest;


/**
 * This is RecipeRequestToRecipe.
 * 
 * @author Uday
 */
@Component
public class RecipeRequestToRecipe {
	
	/**
	 * This method is used to convert to RecipeRequest a Recipe.
	 * 
	 * @param RecipeRequest
	 * @return Recipe returns recipe entity.
	 */
	
	public Recipe convert(RecipeRequest recipeRequest) {
		if (recipeRequest == null) {
			return null;
		}

		Recipe recipe = new Recipe();
		recipe.setId(recipeRequest.getId());
		recipe.setIsVeg(recipeRequest.getIsVeg());
		recipe.setNoOfPeopleSuitableFor(recipeRequest.getNoOfPeopleSuitableFor());
		recipe.setRecipeName(recipeRequest.getRecipeName());
		recipe.setCookingInstructions(recipeRequest.getCookingInstructions());
		recipe.setCreationDate(recipeRequest.getCreationDate());

		if (recipeRequest.getIngredients() != null && !recipeRequest.getIngredients().isEmpty()) {
			for(Ingredient ingredient : recipeRequest.getIngredients()) {
				Ingredient ingTemp = new Ingredient();
				ingTemp.setId(ingredient.getId());
				ingTemp.setDescription(ingredient.getDescription());
				recipe.addIngredient(ingTemp);
			}
		}

		return recipe;
	}
}
