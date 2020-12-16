package com.recipes.responses;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipes.repository.IngredientResponse;

/**
 * This is Recipe Response.
 * 
 * @author Uday
 */

public class RecipeResponse {
	
	
	private int id;

	private String recipeName;

	private boolean isVeg;

	private int noOfPeopleSuitableFor;

	private String cookingInstructions;

	@JsonFormat(pattern = "dd‐MM‐yyyy HH:mm")
	private Date creationDate;

	private Set<IngredientResponse> ingredients = new HashSet<>();

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

	public Set<IngredientResponse> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientResponse> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [id:" + id + ", recipeName:" + recipeName + ", isVeg:" + isVeg + ", noOfPeopleSuitableFor:"
				+ noOfPeopleSuitableFor + ", ingredients:" + ingredients + ", cookingInstructions:"
				+ cookingInstructions + ", creationDate :" + creationDate + "]";
	}

}
