/**
 * Name: Alexis Mendez
 * Date: 5/3/2014
 * Description: Generates a match of words from a dictionary based on an input string
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary
{	
	public static void main(String[] args)
	{
		String lineIn = null;
		String tileInputLine = null;
		char[] tiles;
		ArrayList<String> dictionary = new ArrayList<String>();
		ArrayList<String> matches = new ArrayList<String>();
		Scanner keyboard = new Scanner(System.in);
		boolean[] isTileUsed;
		int wildCardCount = 0;
		
		System.out.println("Enter the tiles followed by the dictionary: ");
		
		// load the tile input
		tileInputLine = keyboard.nextLine();
		
		// load the dictionary
		lineIn = keyboard.nextLine();
		while (!lineIn.equals(""))
		{	
			dictionary.add(lineIn);
			lineIn = keyboard.nextLine();
		}
		
		tiles = tileInputLine.toCharArray();
		isTileUsed = new boolean[tiles.length];
		for (int index = 0; index < tiles.length; index++)
		{
			if (tiles[index] == '*')
			{
				wildCardCount++;
			}
		}
		
		// check each entry in the dictionary
		for (String testEntry : dictionary)
		{
			boolean isEntryValid = true;
			int wildCardCountCopy = wildCardCount;
			
			// mark all tiles unused
			for (int index = 0; index < isTileUsed.length; index++)
			{
				isTileUsed[index] = false;
			}
			
			// test if each letter of the entry
			NEXT_LETTER:
			for (char testEntryTestLetter : testEntry.toCharArray())
			{
				
				// see if there is a direct tile match
				for (int index = 0; index < tiles.length; index++)
				{
					if ((tiles[index] == testEntryTestLetter) && (isTileUsed[index] == false))
					{
						isTileUsed[index] = true;
						continue NEXT_LETTER;
					}
				}
				
				// if we get here, try to use a wildcard
				if (wildCardCountCopy > 0)
				{
					wildCardCountCopy--;
					continue;
				}
				
				// if we get to this point, no matching tile was found, entry is unusable
				isEntryValid = false;
			}
			
			if (isEntryValid)
			{
				matches.add(testEntry);
			}
		}

		// display output
		System.out.println("Tile Input: " + tileInputLine);
		System.out.println("\nMatches found: ");
		for (String matchedEntry : matches)
		{
			System.out.println("\t- " + matchedEntry);
		}
	}
}
