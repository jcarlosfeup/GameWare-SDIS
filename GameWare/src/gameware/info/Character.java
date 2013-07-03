package gameware.info;

public class Character
{
	private String name;
	private int ID;
	
	public Character()
	{
		
	}
	
	public Character(String n, int id)
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
