/*
 * Alexis Mendez
 * Learning project implementing an algorithm for displaying all possible substrings of a given string
 * 1/13/2015
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Substrings {	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ArrayList<String> substrings = new ArrayList<String>();
		String entry;
		int entryLength;
		int count;
		
		System.out.print("Enter a string to analyze: ");
		entry = keyboard.nextLine();
		entryLength = entry.length();		
		System.out.printf("\nInput string: %s\n\n", entry);
		
		//increment substring lengths by 1, starting at 1
		for (int subLength = 1; subLength <= entryLength; subLength++) {
			int subLengthCount = 0;
			System.out.printf("Length = %d: ", subLength);			
			//start at each character until substring length limit is reached (do not exceed original string length)
			for (int startIndex = 0; startIndex <= entryLength; startIndex++) {
				if ((startIndex + subLength) <= entryLength) {
					String substring = entry.substring(startIndex, startIndex + subLength);
					System.out.printf("| \"%s\" ", substring);
					substrings.add(substring);
					subLengthCount++;
				}
			}
			System.out.printf("| [%d] possible\n", subLengthCount);
		}
		
		count = substrings.size();
		System.out.printf("\nTotal possible substrings: %d\n", count);
		keyboard.close();
		return;
	}
}
