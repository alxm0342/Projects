package starbuzz.recipes;

import starbuzz.interfaces.Recipe;

/**	Implementation of a recipe for a Milk-free tea Beverage */
public class CoffeeMilkRecipe implements Recipe {

	//Class member stores the recipe string
	String s;
	
	//Default constructor
	public CoffeeMilkRecipe() {
		s = "\tsteam milk\n"
				+ "\tadd coffee\n"
				+ "\tmix steamed milk and coffee\n"
				+ "\tprocess other ingredients\n"
				+ "\tadd ingredients";
	}

	@Override
	public String prepare() {
		return s;
	}
}
