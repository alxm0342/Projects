package starbuzz.beverages;

public class Espresso extends Coffee {

	//Creates a new Espresso Coffee
	//requires a size as an argument (default: small)
	public Espresso(String newSize) {
		description = "espresso coffee";
		setSizeFactor(newSize);
	}
	@Override
	public double cost() {
		return size.cost() + 1.0;
	}
}
