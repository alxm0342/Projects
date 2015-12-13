package starbuzz.sizefactors;

import starbuzz.interfaces.SizeFactor;

/** Size to cost implementation of a Tea-based Beverage */
public enum TeaBased implements SizeFactor {
	//3 sizes can be instantiated large, medium or small
	Large(0.7), Medium(0.5), Small(0.2);
		
	//Class member stores cost factor based of size
	private final double cost;
	
	//Constructor requires an argument of "small, medium or large"
	//if an invalid size is passed, defaults to small
	TeaBased(double c) {
		cost = c;
	}

	@Override
	public double cost() {
		return cost;
	}
}
