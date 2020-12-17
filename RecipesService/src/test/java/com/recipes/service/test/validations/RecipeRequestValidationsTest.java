package com.recipes.service.test.validations;

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
import com.recipes.model.Recipe;
import com.recipes.service.RecipeService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RecipeController.class)
@WithMockUser
class RecipeRequestValidationsTest {

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
	void GivenInvalidRequestWhenFoundThenReturnBadRequest() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 2);
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor", 1);
		input.put("ingredients", "chicken,rice");
		input.put("cookingInstructions", "spicy");
		input.put("creationDate", "14‐12‐2020 18:30");
		JSONArray array = new JSONArray();
		array.put(new JSONObject());
		
		input.put("ingredients", array);
		
		String contentToBeCreated = input.toString();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}

	@Test
	void GivenInvalidNoOfPPLValueWhenInvokedThenReturnBadRequest() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 2);
		input.put("recipeName", "Chicken Beriyani");
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor",0); // Must be more than or equal to 1
		input.put("ingredients", "chicken,rice");
		input.put("cookingInstructions", "spicy");
		input.put("creationDate", "14‐12‐2020 18:30");
		JSONArray array = new JSONArray();
		array.put(new JSONObject());
		
		input.put("ingredients", array);
		
		String contentToBeCreated = input.toString();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}
	@Test
	void GivenInvalidIngedientsValueWhenFoundThenReturnBadRequest() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 101);
		input.put("recipeName", "Chicken Beriyani");
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor", 1);
		input.put("cookingInstructions", "spicy");
		input.put("creationDate", "14‐12‐2020 18:30");
		input.put("ingredients", "[]");
		
		
		String contentToBeCreated = input.toString();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}
	
	@Test
	void GivenInvalidCookingInstructionsThenReturnBadRequest() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 101);
		input.put("recipeName", "Chicken Beriyani");
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor", 1);
		input.put("ingredients", "chicken,rice");
		input.put("creationDate", "14‐12‐2020 18:30");
		JSONArray array = new JSONArray();
		array.put(new JSONObject());
		
		input.put("ingredients", array);
		
		String contentToBeCreated = input.toString();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}
	@Test
	void GivenInvalidCreationDateThenReturnBadRequest() throws Exception {

		JSONObject input = new JSONObject();
		input.put("id", 101);
		input.put("recipeName", "Chicken Beriyani");
		input.put("isVeg", false);
		input.put("noOfPeopleSuitableFor", 1);
		input.put("ingredients", "chicken,rice");
		input.put("cookingInstructions", "spicy");
		JSONArray array = new JSONArray();
		array.put(new JSONObject());
		
		input.put("ingredients", array);
		
		String contentToBeCreated = input.toString();
		
		Mockito.when(mockRecipeService.createRecipe(Mockito.any())).thenReturn(new Recipe());

		
		RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post(apiContext + "/")
				.accept(MediaType.APPLICATION_JSON).content(contentToBeCreated).contentType(MediaType.APPLICATION_JSON);

		
		ResultMatcher expected = status().isBadRequest();
		mockMvc.perform(postRequestBuilder).andExpect(expected);

	}
}
