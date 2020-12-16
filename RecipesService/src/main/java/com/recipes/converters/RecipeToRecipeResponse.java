package com.recipes.converters;

import org.springframework.stereotype.Component;

import com.recipes.model.Ingredient;
import com.recipes.model.Recipe;
import com.recipes.repository.IngredientResponse;
import com.recipes.responses.RecipeResponse;

/**
 * This is RecipeToRecipeRequest.
 * 
 * @author Uday
 */
@Component
public class RecipeToRecipeResponse {

	/**
	 * This method is used to convert to Recipe a RecipeRequest.
	 * 
	 * @param Recipe
	 * @return RecipeRequest returns RecipeRequest.
	 */

	public RecipeResponse convert(Recipe recipe) {
		if (recipe == null) {
			return null;
		}

		RecipeResponse recipeResponse = new RecipeResponse();
		recipeResponse.setId(recipe.getId());
		recipeResponse.setIsVeg(recipe.getIsVeg());
		recipeResponse.setNoOfPeopleSuitableFor(recipe.getNoOfPeopleSuitableFor());
		recipeResponse.setRecipeName(recipe.getRecipeName());
		recipeResponse.setCookingInstructions(recipe.getCookingInstructions());
		recipeResponse.setCreationDate(recipe.getCreationDate());

		if (recipe.getIngredients() != null && !(recipe.getIngredients().isEmpty())) {
			for (Ingredient ingredient : recipe.getIngredients()) {
				IngredientResponse ingTemp = new IngredientResponse();
				ingTemp.setId(ingredient.getId());
				ingTemp.setDescription(ingredient.getDescription());
				recipeResponse.getIngredients().add(ingTemp);
			}
		}
		return recipeResponse;
	}
}
