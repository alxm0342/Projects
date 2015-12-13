// Forwarder.java
//
//Angela Mendez
//11332686

//Kellin Fitzpatrick
//70891164
// ICS 22 / CSE 22 Spring 2012
// Project #2: You Won't Find Me There

import java.util.Scanner;


public class Forwarder
{
	// You can use this scanner to read input from the console, one line
	// at a time.
	private Scanner input;

	// You'll probably find that you need to add other fields here
	LinkedList<ForwardingEntry> list;
	LinkedList<ForwardingEntry>.Iterator iterator;
	private String command;
	private String name;
	private String oldAddress;
	private String newAddress;
	private ForwardingEntry entry;
	private ForwardingEntry checker;


	public Forwarder()
	{
		input = new Scanner(System.in);
		list = new LinkedList<ForwardingEntry>();
		iterator = new LinkedList<ForwardingEntry>().iterator();
	}


	public void go()
	{
		command = input.nextLine();
		
		while (!(command.equalsIgnoreCase("quit")))
		{	
			if (command.equalsIgnoreCase("add"))
			{
				name = input.nextLine();
				oldAddress = input.nextLine();
				newAddress = input.nextLine();
				add(name, oldAddress, newAddress);
				command = input.nextLine();
				
			}
			else if (command.equalsIgnoreCase("remove"))
			{
				name = input.nextLine();
				oldAddress = input.nextLine();
				newAddress = input.nextLine();
				remove(name, oldAddress, newAddress);
				command = input.nextLine();
			}
			else if (command.equalsIgnoreCase("mail"))
			{	
				name = input.nextLine();
				oldAddress = input.nextLine();
				mail(name, oldAddress);
				command = input.nextLine();
			}
		
		}
		list.clear();
		System.out.println("Goodbye.");
	}
	
	public void add(String name, String oldAddress, String newAddress)
	{
		this.iterator = list.iterator();
		this.entry = new ForwardingEntry (name, oldAddress, newAddress);
		

			while(iterator.hasNext())
			{
				checker = iterator.next();
				if((entry.sameContentAs(checker)))
				{
					System.out.println("Entry already exists.");
					return;
				}
				else if(entry.getName().equalsIgnoreCase(checker.getName())&& (!(entry.getNewAddress().equalsIgnoreCase(checker.getNewAddress()))))
				{
					String address = entry.getNewAddress();
					entry.updateAddress(address);
					
				}
			}
	
			list.addToFront(entry);
			System.out.println("Added");
		
	}
	
	
	public void remove(String name, String oldAddress, String newAddress)
	{
		LinkedList<ForwardingEntry>.Iterator iterator = list.iterator();
		entry = new ForwardingEntry (name, oldAddress, newAddress);
		

			while(iterator.hasNext())
			{
				checker = iterator.next();
				if(entry.sameContentAs(checker))
				{
					list.remove(checker);
					System.out.println("Removed");
					return;
				}
			}
	
			System.out.println("No such entry exists.");
		
	}
	
	public void mail(String name, String address)
	{
		String updatedInfo = oldAddress;
		this.iterator = list.iterator();
		while(iterator.hasNext())
		{
			checker = iterator.next();
			if(checker.getName().equalsIgnoreCase(name) && !(checker.getNewAddress().equalsIgnoreCase(address)))
			{
				updatedInfo = entry.getNewAddress();
			}
		}
		System.out.println("Send to: " + updatedInfo);
	}
}
