package my.app.entities;
/*
 * Beverage entity containing:
 * 1. Name of the Beverage
 * 2. Recipe to make the beverage
 */
public class Beverage {
	private String name;
	private Recipe recipe;
	public Beverage(String name) {
		this.name = name;
		recipe = new Recipe(this.name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
