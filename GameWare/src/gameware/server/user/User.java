package gameware.server.user;

public class User 
{
	private String username;
	private String password;
	private boolean isLogged;
	private int userID;
	
	public User()
	{
		isLogged = false;
		setUserID(-1);
	}
	
	public int register()
	{
		Register reg = new Register(username, password);
		return reg.perform();
	}
	
	public int login()
	{
		Login login = new Login(username, password);
		int res = login.perform();
		
		if(res == 1) isLogged = true;
		
		return res;
	}

	public int logout()
	{
		Logout logout = new Logout(isLogged);
		return logout.perform();
	}
	
	public int getID()
	{
		IDFetch fetch = new IDFetch(username);
		userID = fetch.getID();
		
		System.out.println(userID);
		
		return fetch.getID();
	}
	
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String user)
	{
		username = user;
	}
	
	public void setPassword(String pass)
	{
		password = pass;
	}
}
