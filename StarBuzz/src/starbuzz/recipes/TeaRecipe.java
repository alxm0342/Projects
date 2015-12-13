package starbuzz.recipes;

import starbuzz.interfaces.Recipe;

/**	Implementation of a recipe for a Milk-free tea Beverage */
public class TeaRecipe implements Recipe {

	//Class member stores the recipe string
	String s;
	
	//Default constructor
	public TeaRecipe() {
		s = "\tadd water\n"
				+ "\tadd tea bags\n"
				+ "\tadd other ingredients";
	}

	@Override
	public String prepare() {
		return s;
	}
}
