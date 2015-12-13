// Alexis Mendez
// CS311, Section 01 - Sang
// 10/12/12
// Project 1: An implementation of universal finite state automata
// Instructions:  The project directory must contain UniversalFSA.java, Transition.java
// 				  and input.txt. The program can then be compiled and run normally.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class UniversalFSA {
	//---------------------------------------------------------------------------------
	//interpret the FSA machine parameters from the input file
	public static void readInput(String filePath) {
		int numStates;
		int finalNum;
		String inputLine = "";
		boolean[] finalStates;
		StringTokenizer inTokens;
		int charCount = 0;
		char alphabet[] = new char[50];
		String startState = "";
		char stateInput = ' ';
		String endState = "";
		LinkedList<Transition> transitions = new LinkedList<Transition>();
		boolean singleComplete = false;
		int machineCount = 1;
		
		//open and read input file
		try {
			FileReader filereader = new FileReader(filePath);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			//retrieve input of each machine until EOF
			while (inputLine != null) {
			//read first line, number of states
			try{inputLine = bufferedreader.readLine();}
			catch (IOException e){}
				//first line accepted, begin building FSA
				singleComplete = false;
				transitions = new LinkedList<Transition>();
				System.out.println("Finite State Automation #" + machineCount++ + ":");
				inTokens = new StringTokenizer(inputLine);
				numStates = Integer.parseInt(inTokens.nextToken());
				System.out.println("(1) number of states: " + numStates);
				
				//initialize all states to non-final
				finalStates = new boolean[numStates];
				for (int i=0; i<numStates; i++) {
					finalStates[i] = false;
				}
				
				//read second line, set of final states
				try{inputLine = bufferedreader.readLine();}
				catch (IOException e){}
				inTokens = new StringTokenizer(inputLine);
				System.out.print("(2) final states: ");
				while (inTokens.hasMoreTokens()) {
					finalNum = Integer.parseInt(inTokens.nextToken());
					//ensure final state in the set of all states
					if (finalNum < numStates && finalNum >=0) {
						finalStates[finalNum] = true;
						System.out.print(finalNum + " ");
					}
				}
				
				//read third line, alphabet
				try{inputLine = bufferedreader.readLine();}
				catch (IOException e){}
				inTokens = new StringTokenizer(inputLine);
				System.out.print("\n(3) alphabet: ");
				//retrieve count of alphabet symbols
				while (inTokens.hasMoreTokens()) {
					System.out.print(inTokens.nextToken());
					charCount++;
					if (inTokens.hasMoreTokens()) {
						System.out.print(", ");
					}
				}
				alphabet = new char[charCount];
				inTokens = new StringTokenizer(inputLine);
				while (inTokens.hasMoreTokens()) {
					alphabet[charCount-1] = inTokens.nextToken().charAt(0);
					charCount--;
				}
				
				//read fourth line, transition function
				try{inputLine = bufferedreader.readLine();}
				catch(IOException e){}
				System.out.println("\n(4) transitions:");
				while (inputLine.charAt(0)=='(') {
					startState = inputLine.charAt(1) + "";
					stateInput = inputLine.charAt(3);
					endState = inputLine.charAt(5) + "";
					Transition partTrans = new Transition(Integer.parseInt(startState),
							stateInput, Integer.parseInt(endState));
					transitions.add(partTrans);
					System.out.println("     (" + startState + " " + stateInput + " "
							+ endState + ")");
					try{inputLine = bufferedreader.readLine();}
					catch(IOException e){}
				}
				
				//evaluate each input
				System.out.println("(5) strings:");
				while (inputLine!=null && singleComplete==false) {
					if (inputLine.equals("")) {
						//exit loop
						singleComplete = true;
						System.out.println();
					}
					else {
						System.out.print("     " + inputLine);
						if (evaluateString(inputLine, transitions, finalStates)) {
							System.out.print("     Accept\n");
						}
						else {
							System.out.print("     Reject\n");
						}
						try{inputLine = bufferedreader.readLine();}
						catch(IOException e){}
					}
				}
			}
		}
		catch(IOException e){}
	}
	//---------------------------------------------------------------------------------
	//evaluate if an input string is accepted in the Language defined by FSA
	public static boolean evaluateString(String input, LinkedList<Transition> transitions,
			boolean finalStates[]) {
		Transition tempTrans = new Transition();
		int currentState = 0;
		int listCursor = 0;
		boolean trap = false;
		
		//for-loop cycles through all string symbols
		for (int i=0; i<input.length(); i++) {
			//reset cursor to beginning of transition list
			listCursor = 0;
			tempTrans = (Transition)transitions.get(listCursor);
			while (tempTrans!=null) {
				//if input is in alphabet and transition is found, move to next state
				//and accept symbol
				if ((currentState==tempTrans.getCurrentState()) &&
						(input.charAt(i)==tempTrans.getInput())) {
					//move to next state
					currentState = tempTrans.getNextState();
					//exit while loop to move to next symbol
					tempTrans=null;
				}
				//transition invalid, try next transition on list
				else {
					//advance list cursor
					listCursor ++;
					//if next transition on list exists, set to tempTrans
					if (listCursor <= transitions.lastIndexOf(transitions.getLast())) {
						tempTrans = (Transition)transitions.get(listCursor);
					}
					//end of transition list reached, no valid transition found
					else {
						//no transition specified for input,
						//exit loop and activate default flag
						tempTrans=null;
						trap=true;
					}
				}
			}
		}
		//return false if trap state reached, else return truth of end state being final
		return (trap?false:finalStates[currentState]);
	}
	//---------------------------------------------------------------------------------
	public static void main(String[] args) {
		//read a single input file with multiple machine
		readInput("input.txt");
	}
	//---------------------------------------------------------------------------------
}
