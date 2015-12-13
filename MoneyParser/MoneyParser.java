/**
 * Name: Alexis Mendez
 * Date: 1/10/2014
 * Description: Generates a string literal given a valid dollar value
 */
import java.util.Scanner;

public class MoneyParser
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		final String dollarPattern = "[0-9]+\\.[0-9]{2}";
		Scanner keyboard = new Scanner(System.in);
		String dollarsIn = "";
		
		//check input with a regex
		while (!dollarsIn.toString().matches(dollarPattern))
		{
			System.out.print("Enter a valid dollar amount: ");
			dollarsIn = keyboard.nextLine();
		}
		
		System.out.println(parseToLetters(Double.parseDouble(dollarsIn)));
	}

	private static String parseToLetters(Double dollarsIn)
	{
		//maximum power this function will parse
		final int MAX_POWER = 15;
		
		StringBuilder output = new StringBuilder();
		
		if (!(dollarsIn / Math.pow(10, MAX_POWER) < 1))
		{
			System.out.println("Cannot parse amount that large");
			return output.toString();
		}
		
		output.append("Converted: ");
		
		//handle special case of only cents
		if (dollarsIn < 1.0)
		{
			output.append(parseThousandGroup((int) (dollarsIn.doubleValue() * 100)));
			output.append("cents");
			return output.toString();
		}
		
		//determine how many 3-digit sets to parse
		for (int thousandGroup = determineThousandGroups(dollarsIn); thousandGroup > 0; thousandGroup--)
		{
			int thousandGroupValue = (int) (dollarsIn / (Math.pow(10, 3 * (thousandGroup - 1))));
			output.append(parseThousandGroup(thousandGroupValue));
			switch (thousandGroup)
			{
				case 2:
					output.append("thousand ");
					break;
				case 3:
					output.append("million ");
					break;
				case 4:
					output.append("billion ");
					break;
				case 5:
					output.append("trillion ");
					break;
			}
			dollarsIn = dollarsIn - (thousandGroupValue * (Math.pow(10, 3 * (thousandGroup-1))));
			if (dollarsIn < 1.0)
			{
				output.append("dollars and ");
				output.append(parseThousandGroup((int) (dollarsIn.doubleValue() * 100)));
				output.append("cents");
			}
		}
		
		return output.toString();
	}

	private static String parseThousandGroup(int dollarsIn)
	{
		StringBuilder thousandGroupOutput = new StringBuilder();
		int hundreds = 0;
		int tens = 0;
		int remainder = dollarsIn;
		
		//parse hundreds
		hundreds = (int) (remainder / 100);
		remainder = remainder - (hundreds * 100);
		switch (hundreds)
		{
			case 0:
				break;
			case 1:
				thousandGroupOutput.append("one hundred ");
				break;
			case 2:
				thousandGroupOutput.append("two hundred ");
				break;
			case 3:
				thousandGroupOutput.append("three hundred ");
				break;
			case 4:
				thousandGroupOutput.append("four hundred ");
				break;
			case 5:
				thousandGroupOutput.append("five hundred ");
				break;
			case 6:
				thousandGroupOutput.append("six hundred ");
				break;
			case 7:
				thousandGroupOutput.append("seven hundred ");
				break;
			case 8:
				thousandGroupOutput.append("eight hundred ");
				break;
			case 9:
				thousandGroupOutput.append("nine hundred ");
				break;
		}
		
		//parse tens
		tens = (int) (remainder / 10);
		remainder = remainder - (tens * 10);
		switch (tens)
		{
			case 0:
				break;
			case 1:
				switch (remainder)
				{
					case 0 :
						thousandGroupOutput.append("ten ");
						break;
					case 1:
						thousandGroupOutput.append("eleven ");
						break;
					case 2:
						thousandGroupOutput.append("twelve ");
						break;
					case 3:
						thousandGroupOutput.append("thirteen ");
						break;
					case 4:
						thousandGroupOutput.append("fourteen ");
						break;
					case 5:
						thousandGroupOutput.append("fifteen ");
						break;
					case 6:
						thousandGroupOutput.append("sixteen ");
						break;
					case 7:
						thousandGroupOutput.append("seventeen ");
						break;
					case 8:
						thousandGroupOutput.append("eighteen ");
						break;
					case 9:
						thousandGroupOutput.append("nineteen ");
						break;
				}
				return thousandGroupOutput.toString();
			case 2:
				thousandGroupOutput.append("twenty ");
				break;
			case 3:
				thousandGroupOutput.append("thirty ");
				break;
			case 4:
				thousandGroupOutput.append("fourty ");
				break;
			case 5:
				thousandGroupOutput.append("fifty ");
				break;
			case 6:
				thousandGroupOutput.append("sixty ");
				break;
			case 7:
				thousandGroupOutput.append("seventy ");
				break;
			case 8:
				thousandGroupOutput.append("eighty ");
				break;
			case 9:
				thousandGroupOutput.append("ninety ");
				break;
		}
		
		switch (remainder)
		{
			case 1:
				thousandGroupOutput.append("one ");
				break;
			case 2:
				thousandGroupOutput.append("two ");
				break;
			case 3:
				thousandGroupOutput.append("three ");
				break;
			case 4:
				thousandGroupOutput.append("four ");
				break;
			case 5:
				thousandGroupOutput.append("five ");
				break;
			case 6:
				thousandGroupOutput.append("six ");
				break;
			case 7:
				thousandGroupOutput.append("seven ");
				break;
			case 8:
				thousandGroupOutput.append("eight ");
				break;
			case 9:
				thousandGroupOutput.append("nine ");
				break;
		}
		
		return thousandGroupOutput.toString();
	}

	private static int determineThousandGroups(Double dollarsIn)
	{
		int thousandGroups = 0;
		
		while (dollarsIn >= 1.0)
		{
			thousandGroups++;
			dollarsIn = (dollarsIn / 1000);
		}
		
		return thousandGroups;
	}

}
