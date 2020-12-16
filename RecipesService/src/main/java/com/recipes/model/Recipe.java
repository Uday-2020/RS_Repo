package com.recipes.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This is Recipe model used to hold the data retrieved from database.
 * 
 * @author Uday
 */

@Entity
@Table(name = "recipe")
public class Recipe {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@Id
	@Column(name = "ID")

	private int id;

	@Column(name = "RECIPE_NAME")
	private String recipeName;

	@Column(name = "IS_VEG")
	private boolean isVeg;

	@Column(name = "NO_OF_PPL_SUITABLE_FOR")
	private int noOfPeopleSuitableFor;

	@Column(name = "COOKING_INSTRUCTIONS")
	private String cookingInstructions;

	@Column(name = "CREATION_DATE")
	@JsonFormat(pattern = "dd‐MM‐yyyy HH:mm")
	private Date creationDate;

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

	
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}

	

	@Override
	public String toString() {
		return "Recipe [id:" + id + ", recipeName:" + recipeName + ", isVeg:" + isVeg + ", noOfPeopleSuitableFor:"
				+ noOfPeopleSuitableFor + ", cookingInstructions:"
				+ cookingInstructions + ", creationDate :" + creationDate + ", ingredients :" + ingredients+ "]";
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
}
