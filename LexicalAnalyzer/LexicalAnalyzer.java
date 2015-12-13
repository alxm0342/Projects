// Alexis Mendez
// CS311, Section 01 - Sang
// 11/15/12
// Project 2: A C-- Lexical Analyzer
// Instructions:  The project directory must contain LexicalAnalyzer.java, Node.java,
// 				  	SLList.java and input[x].txt. The [x] can be replaced with 1-5 to
//						use the different input files.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;


public class LexicalAnalyzer {
	static FileReader filereader;
	static BufferedReader bufferedreader;

	//---------------------------------------------------------------------------------
	public static SLList<String> readInput(String filepath) {
		SLList<String> inputList = new SLList<String>();
		try {
			//open input file
			filereader = new FileReader(filepath);
			bufferedreader = new BufferedReader(filereader);
			String inLine = "";
            
         //read first line of input text
         try{inLine = bufferedreader.readLine();}
         catch (IOException e){}
         //read input lines
         while (inLine!=null) {
         	//save a read line for lexical analysis
         	inputList.add(inLine);
            //read a second line of input
            try{inLine = bufferedreader.readLine();}
            catch (IOException e){}
         }
		}
		catch(FileNotFoundException e){
	        JOptionPane.showMessageDialog(null, "Error: File Not Found");
	   }
		return inputList;
	}
	//---------------------------------------------------------------------------------
	public static void addBuffered(SLList<Integer> tokenList, String identifier) {
		if (identifier.length() > 0) {
			switch (identifier) {
			//check for a keyword
			case "double": tokenList.add(0); break;
			case "else": tokenList.add(1); break;
			case "if": tokenList.add(2); break;
			case "int": tokenList.add(3); break;
			case "return": tokenList.add(4); break;
			case "void": tokenList.add(5); break;
			case "while": tokenList.add(6); break;
			//no keyword, buffered string is an identifier
			default:	
				boolean isNotNumber = false;
				for (int i=0; i<identifier.length(); i++) {
					switch (identifier.charAt(i)) {
					case '0':	case '1':	case '2':
					case '3':	case '4':	case '5':
					case '6':	case '7':	case '8':
					case '9':	break;
					default:
						isNotNumber = true; 
						break;
					}
				}
				if (isNotNumber) {
					tokenList.add(28);
				}
				else {
					tokenList.add(29);
				}	break;
			}
			identifier = "";
		}
	}
	//---------------------------------------------------------------------------------
	public static String matchString(int i) {
		String s = "";
		switch (i) {
			case 0: s="DOUBLE"; break;
			case 1: s="ELSE"; break;
			case 2: s="IF"; break;
			case 3: s="INT"; break;
			case 4: s="RETURN"; break;
			case 5: s="VOID"; break;
			case 6: s="WHILE"; break;
			case 7: s="plus"; break;
			case 8: s="minus"; break;
			case 9: s="multiplication"; break;
			case 10: s="division"; break;
			case 11: s="less"; break;
			case 12: s="lessequal"; break;
			case 13: s="greater"; break;
			case 14: s="greaterequal"; break;
			case 15: s="equal"; break;
			case 16: s="notequal"; break;
			case 17: s="assignop"; break;
			case 18: s="semicolon"; break;
			case 19: s="comma"; break;
			case 20: s="period"; break;
			case 21: s="doublequote"; break;
			case 22: s="leftparen"; break;
			case 23: s="rightparen"; break;
			case 24: s="leftbracket"; break;
			case 25: s="rightbracket"; break;
			case 26: s="leftbrace"; break;
			case 27: s="rightbrace"; break;
			case 28: s="id"; break;
			case 29: s="num"; break;
			case 30: s="stringliteral"; break;
		}
		return s;
	}
	//---------------------------------------------------------------------------------
	public static void main(String[] args) {
		SLList<String> lineList = readInput("input1.txt");
		SLList<Integer> tokenList;
		Queue<SLList<Integer>> integerQueue = new LinkedList<SLList<Integer>>();
		Queue<SLList<Integer>> stringQueue = new LinkedList<SLList<Integer>>();
		Node<String> cursor;
		StringTokenizer lineTokens;

		//display and process the input line-by-line
		System.out.println("------------------------");
		System.out.println("Input File:");
		System.out.println("------------------------");
		cursor = lineList.getHead().getNext();
		while (cursor != null) {
			tokenList = new SLList<Integer>();
			System.out.println(cursor.getValue());
			lineTokens = new StringTokenizer(cursor.getValue());
			while (lineTokens.hasMoreTokens()) {
				String inToken = lineTokens.nextToken();
				switch (inToken) {
					//keywords
					case "double": tokenList.add(0); break;
					case "else": tokenList.add(1); break;
					case "if": tokenList.add(2); break;
					case "int": tokenList.add(3); break;
					case "return": tokenList.add(4); break;
					case "void": tokenList.add(5); break;
					case "while": tokenList.add(6); break;
					default:
						//special characters & identifiers
						char inspectChar;
						String identifier = "";
						for (int i=0; i<inToken.length(); i++) {
							inspectChar = inToken.charAt(i);
							//IDs buffer until a special character, both are added
							switch (inspectChar) {
								case '+':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(7);		break;
								case '-':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(8);		break;
								case '*':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(9);		break;
								case '/':
									if (inToken.length() > 1) {
										if (inToken.charAt(i+1) == '/') {
											addBuffered(tokenList, identifier);
											identifier = "";
											while (lineTokens.hasMoreTokens()) {
												inToken = lineTokens.nextToken();
												i = inToken.length();
											}
										}
										else if (inToken.charAt(i+1) == '*') {
											addBuffered(tokenList, identifier);
											identifier = "";
											boolean commentEnd = false;
											i+=2;
											while (cursor != null && !commentEnd) {
												while (inToken != null && !commentEnd) {
													while (i<inToken.length()) {
														inspectChar = inToken.charAt(i);
														if (inspectChar == '*') {
															i++;
																if (i<inToken.length()) {
																	inspectChar = inToken.charAt(i);
																	if (inspectChar == '/') {
																		i = inToken.length();
																		commentEnd = true;
																	}
																}
														}
														i++;
													}
													if (!commentEnd) {
														if (lineTokens.hasMoreTokens()) {
															inToken = lineTokens.nextToken();
															i=0;
														}
														else {
															cursor = cursor.getNext();
															System.out.println(cursor.getValue());
															lineTokens = new StringTokenizer(cursor.getValue());
															inToken = lineTokens.nextToken();
															i=0;
														}
													}
												}
											}
										}
									}
									else {
										addBuffered(tokenList, identifier);
										identifier = "";
										tokenList.add(10);
									}	break;
								case '<':
									if (i+1 < inToken.length()) {
										if (inToken.charAt(i+1) != '=') {
											addBuffered(tokenList, identifier);
											identifier = "";
											tokenList.add(11);
										}
									}
									else {
										addBuffered(tokenList, identifier);
										identifier = "";
										tokenList.add(12);
									}	break;
								case '>':
									if (i+1 < inToken.length()) {
										if (inToken.charAt(i+1) != '=') {
											addBuffered(tokenList, identifier);
											identifier = "";
											tokenList.add(13);
										}
									}
									else {
										addBuffered(tokenList, identifier);
										identifier = "";
										tokenList.add(14);
									}	break;
								case '=':
									if (i+1 < inToken.length()) {
										if (inToken.charAt(i+1) == '=') {
											addBuffered(tokenList, identifier);
											identifier = "";
											tokenList.add(15);
										}
										else {
											addBuffered(tokenList, identifier);
											identifier = "";
											tokenList.add(17);
										}
									}
									else {
										addBuffered(tokenList, identifier);
										identifier = "";
										tokenList.add(17);
									}	break;
								case '!':
									if (inToken.charAt(i+1) == '=') {
										addBuffered(tokenList, identifier);
										identifier = "";
										tokenList.add(16);
										i++;
									} break;
								case ';':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(18);	break;
								case ',':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(19);	break;
								case '.':
									//check for a number
									boolean idBefore = false;
									boolean idAfter = false;
									char[] bufferedBefore = identifier.toCharArray();
									//check that characters in token before '.' are digits
									for (int j=0; j < bufferedBefore.length; j++) {
										switch (bufferedBefore[j]) {
											case '0':	case '1':	case '2':
											case '3':	case '4':	case '5':
											case '6':	case '7':	case '8':
											case '9':	break;
											default:
												idBefore = true; 
												addBuffered(tokenList, identifier);
												identifier = "";
												tokenList.add(20);
												j = bufferedBefore.length;
												break;
										}
									}
									//check that there is at least 1 digit after '.'
									switch (inToken.charAt(i+1)) {
										case '0':	case '1':	case '2':
										case '3':	case '4':	case '5':
										case '6':	case '7':	case '8':
										case '9':	
											identifier += inspectChar;
											break;
										default:
											//not satisfied, add id before '.' and '.' as tokens
											idAfter = true; 
											addBuffered(tokenList, identifier);
											identifier = "";
											tokenList.add(20);
											break;
									}
									//add digits to token to process as a number
									if (!(idBefore || idAfter)) {
										for (int j=i+1; j < inToken.length(); j++) {
											switch (inToken.charAt(j)) {
											case '0':	case '1':	case '2':
											case '3':	case '4':	case '5':
											case '6':	case '7':	case '8':
											case '9':
												identifier += inToken.charAt(j);
												break;
											default:
												tokenList.add(29);
												i=j-1;
												j = inToken.length();
												identifier = "";
												break;
											}
										}
									}
									break;
								case '"':
									addBuffered(tokenList, identifier);
									identifier = "";
									boolean isLiteral = false;
									//String literal = identifier.toString();
									for (int j=i+1; j < cursor.getValue().length(); j++) {
										if (j >= inToken.length()) {
											//literal += " ";
											inToken = lineTokens.nextToken();
											j=0;
										}
										if (inToken.charAt(j) == '"') {
											tokenList.add(30);
											isLiteral = true;
											i = j;
											j = cursor.getValue().length();
										}
										//if (!isLiteral) {
											//literal += inToken.charAt(j);
										//}
									}
									if (!isLiteral){
										tokenList.add(21);
										addBuffered(tokenList, identifier);
										identifier = "";
									}
									//literal = "";
									break;
								case '(':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(22);	break;
								case ')':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(23);	break;
								case '[':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(24);	break;
								case ']':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(25);	break;
								case '{':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(26);	break;
								case '}':
									addBuffered(tokenList, identifier);
									identifier = "";
									tokenList.add(27);	break;
								default:
									identifier += inspectChar;
									switch (identifier){
										//keywords
										case "double":
											tokenList.add(0);
											identifier = "";	break;
										case "else":
											tokenList.add(1);
											identifier = "";	break;
										case "if":
											tokenList.add(2);
											identifier = "";	break;
										case "int":
											tokenList.add(3);
											identifier = "";	break;
										case "return":
											tokenList.add(4);
											identifier = "";	break;
										case "void":
											tokenList.add(5);
											identifier = "";	break;
										case "while":
											tokenList.add(6);
											identifier = "";	break;
									}	break;
							}
						}
						if (identifier.length() > 0) {
							addBuffered(tokenList, identifier);
							identifier = "";
						}
				}
			}
			cursor = cursor.getNext();
			if (!tokenList.isEmpty()) {
				integerQueue.offer(tokenList);
				stringQueue.offer(tokenList);
			}
		}
		//display integer lexical analysis
		System.out.println("\n------------------------");
		System.out.println("Integer Lexical Analysis:");
		System.out.println("------------------------");
		while (!integerQueue.isEmpty()) {
			SLList<Integer> newLine = integerQueue.poll();
			Node<Integer> s = newLine.getHead();
			while (s.getNext() != null) {
				s = s.getNext();
				System.out.print(s.getValue() + " ");
			}
			System.out.print("\n");
		}
		//display string name lexical analysis
		System.out.println("\n------------------------");
		System.out.println("String Lexical Analysis:");
		System.out.println("------------------------");
		while (!stringQueue.isEmpty()) {
			SLList<Integer> newLine = stringQueue.poll();
			Node<Integer> s = newLine.getHead();
			while (s.getNext() != null) {
				s = s.getNext();
				String output = matchString(s.getValue());
				System.out.print(output + " ");
			}
			System.out.print("\n");
		}
	}
}
