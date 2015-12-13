package starbuzz.beverages;

public class GreenTea extends Tea {

	//Creates a new Decaf Coffee
	//requires a size as an argument (default: small)
	public GreenTea(String newSize) {
		description = "green tea";
		setSizeFactor(newSize);
	}
	@Override
	public double cost() {
		return size.cost() + 1.0;
	}
}
