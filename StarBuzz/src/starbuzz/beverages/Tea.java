package starbuzz.beverages;

import starbuzz.interfaces.Beverage;
import starbuzz.interfaces.Recipe;
import starbuzz.recipes.TeaMilkRecipe;
import starbuzz.recipes.TeaRecipe;
import starbuzz.sizefactors.TeaBased;

public abstract class Tea implements Beverage {

	//Class members hold beverage information
	protected String description;
	protected Recipe recipe = new TeaRecipe();
	protected TeaBased size;
	
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
			size = TeaBased.Large;
		}
		else if (newSize.equals("medium")) {
			size = TeaBased.Medium;
		}
		else {
			size = TeaBased.Small;
		}
	}
	
	@Override
	public void addMilk() {
		recipe = new TeaMilkRecipe();
	}
	
	@Override
	public String getType() {
		return "tea";
	}
}
