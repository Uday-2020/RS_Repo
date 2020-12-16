package com.recipes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "ingredient")
public class Ingredient {

	@ManyToOne
	private Recipe recipe;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "description")
	private String description;

	public Ingredient() {
	}

	public Ingredient(int id, String description) {
		this.id = id;
		this.description = description;
	}

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
