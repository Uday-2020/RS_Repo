package com.recipes.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipes.model.Recipe;

public class IngredientResponse {
	@JsonIgnore
	private Recipe recipe;
	private int id;
	private String description;

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
