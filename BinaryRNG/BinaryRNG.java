/**
 * Name: Alexis Mendez
 * Date: 9/28/2013
 * Description: Generates a random integer from 0->N given only a binary RNG
 */

import java.util.Random;
import java.util.Scanner;

public class BinaryRNG
{
	public static void main(String[] args)
	{
		int max = 0;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter max: ");
		max = keyboard.nextInt();
		
		System.out.print("Random number: " + random(max));
	}
	
	private static int random(int max)
	{
		int randomInt = max + 1;
		char[] maxAsCharArray = Integer.toBinaryString(max).toCharArray();
		
		while (randomInt > max)
		{
			int tempRandomInt = 0;
			for (int arrayIndex = (maxAsCharArray.length - 1) ; arrayIndex >= 0; arrayIndex--)
			{
				tempRandomInt += Math.pow(2, arrayIndex) * randomBinary();
			}
			randomInt = tempRandomInt > max ? randomInt : tempRandomInt;
		}
		
		return randomInt;
	}

	private static int randomBinary()
	{
		final int BINARY_MAX = 2;
		Random rand = new Random();
		
		return rand.nextInt(BINARY_MAX);
	}
}
