package starbuzz.sizefactors;

import starbuzz.interfaces.SizeFactor;

/** Size to cost implementation of a Coffee-based Beverage */
public enum CoffeeBased implements SizeFactor {
	//3 sizes can be instantiated large, medium or small
	Large(1.0), Medium(0.7), Small(0.4);
	
	//Class member stores cost factor based of size
	private final double cost;
	
	//Constructor requires an argument of "small, medium or large"
	CoffeeBased(double c) {
		cost = c;
	}

	@Override
	public double cost() {
		return cost;
	}
}
