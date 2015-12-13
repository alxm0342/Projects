package starbuzz.client;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import starbuzz.beverages.*;
import starbuzz.ingredients.*;
import starbuzz.interfaces.*;

/**	An implementation of the StarBuzz drink ordering interface
 * 
 * 	@author	Alexis Mendez - CS 480
 * 				Bronco ID: 008801988
 *					Date: 3/18/2013
 */
public class StarBuzz {
	
	//Displays a list of available base drinks
	private static void displayDrinks() {
		System.out.println("Espresso, Decaf, HouseBlend, Mocha, Latte, Cappuccino, DecafMocha, " +
				"DecafLatte, DecafCappuccino, GreenTea, RedTea, WhiteTea, FlowerTea, GingerTea, TeaLatte");
	}
	
	//Displays a list of available base drinks
	private static void displayIngredients() {
		System.out.println("Chocolate, Milk, WhipCream, Jasmine, Ginger");
	}
	
	//Validates base drink name and size then returns a beverage of that type or null by default
	private static Beverage makeBase(String drink, String size) {
		//validate size
		if (!size.equals("large") && size.equals("medium") && size.equals("small")) {
			return null;
		}
		//validate and return the base drink
		drink = drink.toLowerCase();
		switch (drink) {
			case ("espresso"):
				return new Espresso(size);
			case ("decaf"):
				return new Decaf(size);
			case ("houseblend"):
				return new HouseBlend(size);
			case ("mocha"):
				Beverage mocha = new Espresso(size);
				mocha = new Chocolate(mocha);
				return mocha;
			case ("latte"):
				Beverage latte = new Espresso(size);
				latte = new Milk(latte);
				return latte;
			case ("cappuccino"):
				Beverage cappuccino = new Espresso(size);
				cappuccino = new WhipCream(cappuccino);
				return cappuccino;
			case ("decafmocha"):
				Beverage decafmocha = new Decaf(size);
				decafmocha = new Chocolate(decafmocha);
				return decafmocha;
			case ("decaflatte"):
				Beverage decaflatte = new Decaf(size);
				decaflatte = new Milk(decaflatte);
				return decaflatte;
			case ("decafcappuccino"):
				Beverage decafcappuccino = new Decaf(size);
				decafcappuccino = new WhipCream(decafcappuccino);
				return decafcappuccino;
			case ("greentea"):
				return new GreenTea(size);
			case ("redtea"):
				return new RedTea(size);
			case ("whitetea"):
				return new WhiteTea(size);
			case ("flowertea"):
				Beverage flowertea = new GreenTea(size);
				flowertea = new Jasmine(flowertea);
				return flowertea;
			case ("gingertea"):
				Beverage gingertea = new GreenTea(size);
				gingertea = new Ginger(gingertea);
				return gingertea;
			case ("tealatte"):
				Beverage tealatte = new RedTea(size);
				tealatte = new Milk(tealatte);
				return tealatte;
			default:
				return null;
		}
	}
	
	//Add additional ingredients to a base drink
	private static Beverage addIngredients(String toAdd, Beverage bev) {
		toAdd.toLowerCase();
		switch (toAdd) {
			case ("chocolate"):
				bev = new Chocolate(bev);
				return bev;
			case ("milk"):
				bev = new Milk(bev);
				return bev;
			case ("whipcream"):
				bev = new WhipCream(bev);
				return bev;
			case ("jasmine"):
				bev = new Jasmine(bev);
				return bev;
			case ("ginger"):
				bev = new Ginger(bev);
				return bev;
			default:
				return null;
		}
	}
	
	public static void main(String[] args) {
		//System input variables
		Scanner keyboard = new Scanner(System.in);
		
		//String holds drink order input
		boolean validOrder = false;
		StringTokenizer inTokens;
		int numTokens = 0;
		String drink = null, size = null, temp = null, cost = null;
		LinkedList<String> ingredients = new LinkedList<String>();
		Beverage beverage;
		
		//Greet user
		System.out.println("Welcome to the StarBuzz ordering system");
		
		while (!validOrder) {
			//display available drinks
			System.out.println("\nThe following drinks available (large, medium or small sizes only):");
			displayDrinks();
			//display available ingredients
			System.out.println("\nYou may also add any of the following ingredients:");
			displayIngredients();
			//retrieve input
			System.out.println("\nPlease enter your drink in the following format (or \"quit\" to exit) :");
			System.out.println("<beverage name> <size> <ingredient1> <ingredient2> ... <ingredientN>\n");
			temp = keyboard.nextLine();
			inTokens = new StringTokenizer(temp);
			numTokens = inTokens.countTokens();
			for (int i=0; i<numTokens; i++) {
				if (i==0) {
					drink = inTokens.nextToken();
				}
				else if (i==1) {
					size = inTokens.nextToken();
				}
				else {
					ingredients.add(inTokens.nextToken());
				}
			}
			if (drink.toLowerCase().equals("q") || drink.toLowerCase().equals("quit")) {
				break;
			}
			//validate base beverage
			beverage = makeBase(drink, size);
			if (beverage!=null) {
				//special case: no additional ingredients
				if (ingredients.size()==0) {
					validOrder = true;
				}
				//add ingredients to drink
				for (int i=0; i<ingredients.size(); i++) {
					beverage = addIngredients(ingredients.get(i), beverage);
					//check that all ingredients were valid
					if(beverage!=null) {
						if (i==ingredients.size()-1) {
							validOrder = true;
						}
					}
					//otherwise, order is invalid, flush variables and get new input
					else {
						numTokens = 0;
						drink = null;
						size = null;
						temp = null;
						ingredients.removeAll(ingredients);
						validOrder = false;
						break;
					}
				}
			}
			//if an order is valid display output
			if (validOrder) {
				cost = NumberFormat.getCurrencyInstance().format(beverage.cost());
				//cost.replaceAll("\\.00", "");
				System.out.println("\nThe total cost of your order is: " + cost);
				System.out.println("The recipe is prepared as follows: ");
				System.out.println(beverage.prepare());
			}
			//otherwise, prompt user that input is invalid and ask for new input
			else {
				System.out.println("\nInvalid input, please try again.");
			}
			//ask user if they would like to input another drink
			System.out.println("\nWould you like to enter another order? (enter 'y' for yes)");
			temp = keyboard.nextLine();
			if (temp.toLowerCase().equals("y") || temp.toLowerCase().equals("yes")) {
				numTokens = 0;
				drink = null;
				size = null;
				temp = null;
				ingredients.removeAll(ingredients);
				validOrder = false;
			}
		}
		System.out.println("\nGoodbye!");
	}
}
