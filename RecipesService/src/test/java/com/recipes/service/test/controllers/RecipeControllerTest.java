package com.recipes.service.test.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.recipes.controller.RecipeController;
import com.recipes.converters.RecipeRequestToRecipe;
import com.recipes.converters.RecipeToRecipeResponse;
import com.recipes.exception.RecipeNotFoundException;
import com.recipes.model.Ingredient;
import com.recipes.model.Recipe;
import com.recipes.service.RecipeService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RecipeController.class)
@WithMockUser
class RecipeControllerTest {

	private String apiContext = "/api/v1/recipe";

	@MockBean
	RecipeService mockRecipeService;
	@MockBean
	RecipeRequestToRecipe recipeRequestToRecipe;
	@MockBean
	RecipeToRecipeResponse recipeToRecipeRequest;
    
	RecipeController recipeController;

	@Autowired
	MockMvc mockMvc;

	@Test
	void GivenIdWhenFoundThenRetunResponseWithOK() throws Exception {

		// Given
		Recipe recipe = new Recipe();
		recipe.setId(101);
		recipe.setRecipeName("CB");
		recipe.setNoOfPeopleSuitableFor(4);
		recipe.setCookingInstructions("medium spicy");
		Ingredient ingredient = new Ingredient(1,"onion");
		recipe.addIngredient(ingredient);
		recipe.setIsVeg(true);

		// When
		Mockito.when(mockRecipeService.getRecipeById(Mockito.anyInt())).thenReturn(recipe);

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.get(apiContext + "/" + "101")
				.accept(MediaType.APPLICATION_JSON);

		// verify
		ResultMatcher expected = status().isOk();
		mockMvc.perform(getRequestBuilder).andExpect(expected);
	}

	@Test
	void GivenIdWhenNotFoundThenReturnException() throws Exception {

		// Given
		int id = 101;
		// When
		Mockito.when(mockRecipeService.getRecipeById(Mockito.anyInt())).thenThrow(RecipeNotFoundException.class);

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.get(apiContext + "/" + id)
				.accept(MediaType.APPLICATION_JSON);

		// verify
		ResultMatcher expected = status().isNotFound();
		mockMvc.perform(getRequestBuilder).andExpect(expected);
	}

	@Test
	void GivenIdWhenFoundRecordThenDelete() throws Exception {

		// Given
		int id = 101;

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.delete(apiContext + "/" + id);

		// verify
		ResultMatcher expected = status().isOk();
		mockMvc.perform(getRequestBuilder).andExpect(expected);

		verify(mockRecipeService, times(1)).deleteRecipeById(Mockito.anyInt());

	}

	@Test
	void GivenInputWhenAddedThenReturnOK() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 2);
		input.put("recipeName", "Chicken Beriyani");
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor", 1);
		input.put("cookingInstructions", "spicy");
		input.put("creationDate", "14‐12‐2020 18:30");
		JSONArray array = new JSONArray();
		JSONObject ingredient = new JSONObject();
		ingredient.put("id", 4);
		ingredient.put("description", "Onions");
		array.put(ingredient);
		input.put("ingredients", array);
				
		// Given
		String contentToBeCreated = input.toString();
System.out.println("contentToBeCreated : "+contentToBeCreated);
		//When
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		//Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext+"/")
				.accept(MediaType.APPLICATION_JSON)
				.content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);
		
		// verify
		ResultMatcher expected = status().isCreated(); 
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}

	@Test
	void GivenInvalidRequesWhenTriedToAddThenReturnBadRequest() throws Exception {

		// Given
		// Did not send content

		// When
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		// Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		// verify
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}

	@Test
	void GivenInputWhenUpdatedThenReturnOK() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 102);
		input.put("recipeName", "ABC");
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor", 1);
		input.put("cookingInstructions", "spicy");
		input.put("creationDate", "14‐12‐2020 18:30");
		
		JSONArray array = new JSONArray();
		array.put(new JSONObject());
		
		input.put("ingredients", array);
				
		// Given
		String contentToBeCreated = input.toString();
		
		// When
		Mockito.when(mockRecipeService.updateRecipe(Mockito.any())).thenReturn(true);

		// Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.put(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		// verify
		ResultMatcher expected = status().isOk();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}

	@Test
	void GivenInvalidRequestThenTriedToUpdateThenReturnBadRequest() throws Exception {

		// Given
		// Did not send content

		// When
		Mockito.when(mockRecipeService.updateRecipe(Mockito.any())).thenReturn(false);

		// Then
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.put(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		// verify
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}

	@Test
	void GivenInvalidInputWhenTriedToDeleteThenReturnBadRequest() throws Exception {

		// Given
		// Tried with invalid request

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.delete(apiContext + "/");

		// verify
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(getRequestBuilder).andExpect(expected);
	}

	@Test
	void GivenInvalidIdWhenTriedToDeleteThenReturnFalse() throws Exception {

		// Given
		int id = 101;
		boolean status = false;

		// When
		Mockito.when(mockRecipeService.deleteRecipeById(id)).thenReturn(status);

		// Then
		RequestBuilder getRequestBuilder = MockMvcRequestBuilders.delete(apiContext + "/" + id);

		// verify
		ResultMatcher expected = status().isOk();
		// mockMvc.perform(getRequestBuilder).andReturn().getResponse().getContentAsString()
		mockMvc.perform(getRequestBuilder).andExpect(expected);

		verify(mockRecipeService, times(1)).deleteRecipeById(Mockito.anyInt());

	}

	
}
