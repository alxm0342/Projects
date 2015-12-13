package starbuzz.ingredients;

import starbuzz.interfaces.Beverage;

public class Chocolate extends Ingredient {

	//Constructor requires that a beverage be passed as an argument
	public Chocolate(Beverage inBev) {
		setBeverage(inBev);
	}
	
	@Override
	public double cost() {
		return this.getBeverage().cost() + 0.3;
	}
}
