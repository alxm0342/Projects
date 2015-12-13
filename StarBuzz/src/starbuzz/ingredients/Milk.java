package starbuzz.ingredients;

import starbuzz.interfaces.Beverage;

public class Milk extends Ingredient {

	//Constructor requires that a beverage be passed as an argument
	public Milk(Beverage inBev) {
		setBeverage(inBev);
		//change the recipe to add milk in the preparation
		inBev.addMilk();
	}
	
	@Override
	public double cost() {
		return this.getBeverage().cost() + 0.3;
	}
}
