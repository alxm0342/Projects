//Angela Mendez
//11332686

//Kellin Fitzpatrick
//70891164
public class ForwardingEntry
{
	private String name;
	private String oldAddress;
	private String newAddress;
	
	//Each forwarding entry consists of a name, an old address, and a new address
	
	public ForwardingEntry(String name, String oldAddress, String newAddress)
	{
		this.name = name;
		this.oldAddress = oldAddress;
		this.newAddress = newAddress;
	}
	
	//returns name
	public String getName()
	{
		return name;
	}
	
	//returns old address
	public String getOldAddress()
	{
		return oldAddress;
	}
	
	//returns new address
	public String getNewAddress()
	{
		return newAddress;
	}
	
	//determines whether or not one forwarding address has the same name, old address, and new address as
	//the entry passed as the parameter
	public boolean sameContentAs(ForwardingEntry e)
	{
		
		if(e.getName().equals(name) && e.getNewAddress().equals(newAddress) && e.getOldAddress().equals(oldAddress))
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
	
	//this method replaces the new address with the string passed as its parameter
	public void updateAddress(String newInfo)
	{
		this.newAddress = newInfo;
		
	}
	
	
}
