package starbuzz.ingredients;

import starbuzz.interfaces.Beverage;

public class WhipCream extends Ingredient {

	public WhipCream(Beverage inBev) {
		setBeverage(inBev);
	}
	
	@Override
	public double cost() {
		return this.getBeverage().cost() + 0.3;
	}
}
