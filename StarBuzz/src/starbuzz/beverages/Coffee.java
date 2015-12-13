package starbuzz.beverages;

import starbuzz.interfaces.Beverage;
import starbuzz.interfaces.Recipe;
import starbuzz.recipes.CoffeeMilkRecipe;
import starbuzz.recipes.CoffeeRecipe;
import starbuzz.sizefactors.CoffeeBased;

public abstract class Coffee implements Beverage {

	//Class members hold beverage information
	protected String description;
	protected Recipe recipe = new CoffeeRecipe();
	protected CoffeeBased size;
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public abstract double cost();

	@Override
	public String prepare() {
		return recipe.prepare();
	}

	@Override
	public void setSizeFactor(String newSize) {
		if (newSize.equals("large")) {
			size = CoffeeBased.Large;
		}
		else if (newSize.equals("medium")) {
			size = CoffeeBased.Medium;
		}
		else {
			size = CoffeeBased.Small;
		}
	}
	
	@Override
	public void addMilk() {
		recipe = new CoffeeMilkRecipe();
	}
	
	@Override
	public String getType() {
		return "coffee";
	}
}
