package starbuzz.beverages;

public class Decaf extends Coffee {

	//Creates a new Decaf Coffee
	//requires a size as an argument (default: small)
	public Decaf(String newSize) {
		description = "decaf coffee";
		setSizeFactor(newSize);
	}
	@Override
	public double cost() {
		return size.cost() + 0.5;
	}
}
