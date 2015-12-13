package starbuzz.ingredients;

import starbuzz.interfaces.Beverage;

public class Jasmine extends Ingredient {

	//Constructor requires that a beverage be passed as an argument
	public Jasmine(Beverage inBev) {
		setBeverage(inBev);
	}
	
	@Override
	public double cost() {
		return this.getBeverage().cost() + 0.5;
	}
}
