import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

//	Name: Alexis Mendez
//	Class: CS 420 - Homework 5
//	Date: 3/12/2013
//	Description:	This class demonstrates clausifying of well-formed formulas

public class ClausifyWWFs {
	
	//--------------------------------------------------------------------
	//Prints all relevant clauses from a given set of input tokens.  If an
	//invalid expression was entered, error message will print instead.
	//A string queue is used to sort and assemble the clause statements.
	//--------------------------------------------------------------------
	private static void printClauses(StringTokenizer inTokens) {
		//instantiate clauses queue with an empty string
		ConcurrentLinkedQueue<String> clauses = new ConcurrentLinkedQueue<String>();
		clauses.add("");
		//variables used for analyzing wffs
		int numTokens = inTokens.countTokens(), negations = 0, numClauses = 1;
		int numOperands = 0;
		String temp = "", tempToken = "";
		boolean demorgan = false;
		///iterate through tokenizer until all elements have been analyzed
		for (int i=0; i<numTokens; i++) {
			tempToken = inTokens.nextToken();
			switch (tempToken) {
				//case of 'and' add a copy of queue head to the queue
				case ("and"):
					temp = clauses.poll();
					//check for negations to move down
					if (temp.equals("not")) {
						negations+=2;
						clauses.add("or ");
					}
					//check if a negation will apply DeMorgan's law
					if (demorgan) {
						demorgan = false;
						if(negations==1) {
							negations++;
						}
						else {
							negations += 2;
						}
						temp = "or or ";
						clauses.add(temp);
						temp = "";
						break;
					}
					clauses.add(temp);
					clauses.add(temp);
					numClauses++;
					temp = "";
					break;
				//case of "imp", add "or" then "not" to queue head
				case ("imp"):
					temp = clauses.poll();
					if (temp.equals("not ") || negations > 0) {
						clauses.add("or ");
						negations--;
						demorgan = false;
						temp = "";
						break;
					}
					demorgan = true;
					negations++;
					temp += "or ";
					clauses.add(temp);
					temp = "";
					break;
				//case of "or", add "or to queue head
				case ("or"):
					temp = clauses.poll();
					//check for negations to move down
					if (temp.equals("not ")) {
						clauses.add(temp);
						clauses.add(temp);
						numClauses++;
						temp = "";
						break;
					}
					//check if a negation will apply DeMorgan's law
					if (demorgan) {
						demorgan = false;
						clauses.add(temp);
						clauses.add(temp);
						numClauses++;
						temp = "";
						break;
					}
					temp += "or ";
					clauses.add(temp);
					temp = "";
					break;
				case ("not"):
					temp = clauses.peek();
					if (temp.equals("not")) {
						negations--;
					}
					negations++;
					demorgan = true;
					//clauses.add(temp);
					break;
				//WFF token is not a keyword, it is considered an identifier
				default:
					temp = clauses.poll();
					if (negations>0) {
						temp += "not ";
						negations--;
					}
					temp = temp + tempToken + " ";
					clauses.add(temp);
					numOperands++;
					//check if a clause is complete
					if (numOperands==2) {
						numOperands = 0;
						numClauses--;
					}
					//if not, place it on top of the queue
					else {
						for (int j=1; j<numClauses-1; j++) {
							temp = clauses.poll();
							clauses.add(temp);
						}
					}
					demorgan = false;
					temp = "";
					break;
			}
		}
		//print the clauses
		System.out.println("\nClauses are:");
		while (clauses.size()>0) {
			System.out.println(clauses.poll());
		}
		return;
	}
	
	//--------------------------------------------------------------------
	//Main method prompts user for input
	//--------------------------------------------------------------------
	public static void main(String[] args) {
		//instantiate input variables
		String input = null;
		StringTokenizer inTokens = null;
		boolean quit = false;
		Scanner keyboard = new Scanner(System.in);
		
		//greet user
		System.out.println("--------------------------------");
		System.out.println("Clausifier, enter 'quit' to exit");
		System.out.println("--------------------------------");
		
		while (!quit) {
			//prompt for input
			System.out.print("\nEnter wff: ");
			input = keyboard.nextLine();
			input = input.toLowerCase();
			//check if user wants to quit
			if (input.equals("quit")) {
				quit = true;
				continue;
			}
			//tokenize input
			inTokens = new StringTokenizer(input);
			printClauses(inTokens);
		}
		
		//prompt exit
		System.out.println("\nGoodbye!");
	}
}
