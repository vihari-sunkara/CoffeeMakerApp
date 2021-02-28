package my.app.entities;

import java.util.Map;

/*
 * Recipe of a beverage contains:
 * 1. Name of the recipe
 * 2. Requirement map which shows requirement of each relevant ingredient
 */
public class Recipe {
	private String name;
	private Map<String, Integer> requirement;

	public Recipe(String name) {
		this.name = name;
		this.requirement = RecipeMaker.getRecipe(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Integer> getRequirement() {
		return requirement;
	}

	public void setRequirement(Map<String, Integer> requirement) {
		this.requirement = requirement;
	}
}
