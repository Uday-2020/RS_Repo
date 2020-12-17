package com.recipes.service.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipes.model.Ingredient;
import com.recipes.model.Recipe;
import com.recipes.repository.RecipeRepository;
import com.recipes.service.RecipeService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class RecipeServiceTest {

	@MockBean
	RecipeRepository mockRecipeRepository;

	@InjectMocks
	RecipeService recipeService;

	@Test
	void GivenValidIdWhenFoudnThenReturnDetails() throws Exception {
		
		Optional<Recipe> recipe = Optional.of(new Recipe());
		recipe.get().setId(101);
		recipe.get().setRecipeName("French Onion Tart");
		recipe.get().setNoOfPeopleSuitableFor(4);
		recipe.get().setCookingInstructions("medium spicy");
		Ingredient ingredient = new Ingredient(1,"onion");
		recipe.get().addIngredient(ingredient);
		recipe.get().setIsVeg(true);

		
		Mockito.when(mockRecipeRepository.findById(Mockito.anyInt())).thenReturn(recipe);

		
		Recipe result = recipeService.getRecipeById(101);

		
		assertEquals("French Onion Tart", result.getRecipeName());
		verify(mockRecipeRepository, times(1)).findById(Mockito.anyInt());
		verify(mockRecipeRepository, never()).findAll();

	}

	@Test
	void WhenFoundThenReturnAllRecipeDetails() throws Exception {
		
		Recipe recipe = new Recipe();
		recipe.setId(101);
		recipe.setRecipeName("French Onion Tart");
		recipe.setNoOfPeopleSuitableFor(4);
		recipe.setCookingInstructions("medium spicy");
		Ingredient ingredient = new Ingredient(1,"onion");
		recipe.addIngredient(ingredient);
		recipe.setIsVeg(true);
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(recipe);

		
		Mockito.when(mockRecipeRepository.findAll()).thenReturn(recipes);

		
		List<Recipe> result = recipeService.getAllRecipes();

		
		assertEquals(1,result.size());
		verify(mockRecipeRepository, times(1)).findAll();
		verify(mockRecipeRepository, never()).findById(Mockito.anyInt());

	}

	@Test
	void GivenValidInputThenAddRecipe() {
		
		Recipe recipe = new Recipe();
		recipe.setId(102);
		recipe.setRecipeName("French Onion Tart");
		recipe.setNoOfPeopleSuitableFor(4);
		recipe.setCookingInstructions("spicy");
		Ingredient ingredient = new Ingredient(1,"onion");
		recipe.addIngredient(ingredient);
		recipe.setIsVeg(true);

		
		Mockito.when(mockRecipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe);

		
		Recipe actual = recipeService.createRecipe(recipe);

		assertEquals(recipe, actual);
		verify(mockRecipeRepository, times(1)).save(Mockito.any());

	}

	@Test
	void GivenValidInputWhenFoundThenUpdateTheDetails() {
		
		Recipe recipe = new Recipe();
		recipe.setId(1);
		recipe.setRecipeName("Pizza");
		recipe.setNoOfPeopleSuitableFor(4);
		recipe.setCookingInstructions("spicy");
		Ingredient ingredient = new Ingredient(1,"onion");
		recipe.addIngredient(ingredient);
		recipe.setIsVeg(true);

		Mockito.when(mockRecipeRepository.findById((Mockito.anyInt()))).thenReturn(Optional.of(recipe));

		Mockito.when(mockRecipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe);

		
		boolean actual = recipeService.updateRecipe(recipe);

		assertEquals(true, actual);
		verify(mockRecipeRepository, times(1)).save(Mockito.any());

	}

	@Test
	void GivenIdWhenFoundThenDelete() {
		
		
		int id = 4;
		Optional<Recipe> recipe = Optional.of(new Recipe());
		recipe.get().setId(4);
		recipe.get().setRecipeName("French Onion Tart");
		recipe.get().setNoOfPeopleSuitableFor(4);
		recipe.get().setCookingInstructions("medium spicy");
		Ingredient ingredient = new Ingredient(1,"onion");
		recipe.get().addIngredient(ingredient);
		recipe.get().setIsVeg(true);

		
		Mockito.when(mockRecipeRepository.findById(Mockito.anyInt())).thenReturn(recipe);

		
		boolean status = recipeService.deleteRecipeById(4);

		
		assertEquals(true, status);
		verify(mockRecipeRepository, times(1)).deleteById(id);
	}
}
