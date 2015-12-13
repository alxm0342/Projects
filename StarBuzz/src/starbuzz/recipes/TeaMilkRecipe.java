package starbuzz.recipes;

import starbuzz.interfaces.Recipe;

/**	Implementation of a recipe for a Milk-free tea Beverage */
public class TeaMilkRecipe implements Recipe {

	//Class member stores the recipe string
	String s;
	
	//Default constructor
	public TeaMilkRecipe() {
		s = "\tadd water\n"
				+ "\tadd tea bags\n"
				+ "\tadd other ingredients\n"
				+ "\tadd milk";
	}

	@Override
	public String prepare() {
		return s;
	}
}
