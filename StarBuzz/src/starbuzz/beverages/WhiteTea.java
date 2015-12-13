package starbuzz.beverages;

public class WhiteTea extends Tea {

	//Creates a new beverage, requires a size as an argument (default is small)
	public WhiteTea(String newSize) {
		description = "white tea";
		setSizeFactor(newSize);
	}
	@Override
	public double cost() {
		return size.cost() + 1.0;
	}
}
