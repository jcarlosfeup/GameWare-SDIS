package gameware.info;

public class Location 
{
	private String name;
	private int ID;
	
	public Location()
	{
		
	}
	
	public Location(String n, int id)
	{
		setName(n);
		setID(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
