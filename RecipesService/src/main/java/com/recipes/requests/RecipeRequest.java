package com.recipes.requests;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipes.model.Ingredient;

/**
 * This is Recipe Request.
 * 
 * @author Uday
 */

public class RecipeRequest {
	private int id;

	@NotBlank(message = "Recipe Name is required")
	@Size(message = "Recipe Name can't be more than 50 characters.", min = 1, max = 50)
	private String recipeName;

	private boolean isVeg;

	@NotNull
	@Min(1)
	private int noOfPeopleSuitableFor;

	@NotBlank(message = "Recipe cooking instructions is required")
	private String cookingInstructions;

	@NotNull
	@JsonFormat(pattern = "dd‐MM‐yyyy HH:mm")
	private Date creationDate;

	@NotNull
	private Set<Ingredient> ingredients = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public boolean getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(boolean isVeg) {
		this.isVeg = isVeg;
	}

	public int getNoOfPeopleSuitableFor() {
		return noOfPeopleSuitableFor;
	}

	public void setNoOfPeopleSuitableFor(int noOfPeopleSuitableFor) {
		this.noOfPeopleSuitableFor = noOfPeopleSuitableFor;
	}

	public String getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [id:" + id + ", recipeName:" + recipeName + ", isVeg:" + isVeg + ", noOfPeopleSuitableFor:"
				+ noOfPeopleSuitableFor + ", ingredients:" + ingredients + ", cookingInstructions:"
				+ cookingInstructions + ", creationDate :" + creationDate + "]";
	}

}
