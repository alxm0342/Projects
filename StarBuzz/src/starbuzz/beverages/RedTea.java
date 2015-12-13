package starbuzz.beverages;

public class RedTea extends Tea {

	//Creates a new beverage, requires a size as an argument (default is small)
	public RedTea(String newSize) {
		description = "red tea";
		setSizeFactor(newSize);
	}
	@Override
	public double cost() {
		return size.cost() + 0.8;
	}
}
