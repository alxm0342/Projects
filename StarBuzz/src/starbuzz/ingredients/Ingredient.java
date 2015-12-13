package starbuzz.ingredients;

import starbuzz.interfaces.Beverage;

public abstract class Ingredient implements Beverage {

	//Class member holds the base beverage
	private Beverage bev;
	
	@Override
	public String getDescription() {
		return bev.getDescription();
	}

	@Override
	public abstract double cost();

	@Override
	public String prepare() {
		return bev.prepare();
	}

	@Override
	public void setSizeFactor(String size) {
		bev.setSizeFactor(size);
	}
	
	@Override
	public void addMilk() {
		bev.addMilk();
	}

	@Override
	public String getType() {
		return bev.getType();
	}
	
	/** Returns the base drink of a particular beverage */
	public Beverage getBeverage() {
		return bev;
	}
	
	/** Sets the base drink of a particular beverage */
	public void setBeverage(Beverage inBev) {
		bev = inBev;
	}
}
