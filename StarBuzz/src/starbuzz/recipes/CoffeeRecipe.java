package starbuzz.recipes;

import starbuzz.interfaces.Recipe;

/**	Implementation of a recipe for a Milk-free tea Beverage */
public class CoffeeRecipe implements Recipe {

	//Class member stores the recipe string
	String s;
	
	//Default constructor
	public CoffeeRecipe() {
		s = "\tadd coffee\n"
				+ "\tadd water\n"
				+ "\tprocess ingredients\n"
				+ "\tadd ingredients";
	}

	@Override
	public String prepare() {
		return s;
	}
}
