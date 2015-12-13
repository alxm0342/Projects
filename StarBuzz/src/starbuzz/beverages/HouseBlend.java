package starbuzz.beverages;

public class HouseBlend extends Coffee {

	//Creates a new Decaf Coffee
	//requires a size as an argument (default: small)
	public HouseBlend(String newSize) {
		description = "houseblend coffee";
		setSizeFactor(newSize);
	}
	@Override
	public double cost() {
		return size.cost() + 0.8;
	}
}
