package starbuzz.interfaces;

/**	Representation of a particular StarBuzz Beverage which
 *		may or may not include additional ingredients. */
public interface Beverage {
	/** Returns a string containing the description of the Beverage */
	public String getDescription();
	
	/**	Returns a the total cost of a beverage, factoring size and
	 *		additional ingredients. */
	public double cost();
	
	/**	Returns a line-by-line delineation of how to prepare
	 *		a beverage recipe. */
	public String prepare();
	
	/**	Set the size cost-factor of a beverage */
	public void setSizeFactor(String size);
	
	/**	Add milk to the beverage, changing the recipe */
	public void addMilk();
	
	/**	Return the type of beverage as a string */
	public String getType();
}
