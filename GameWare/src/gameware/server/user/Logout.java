package gameware.server.user;

public class Logout implements UserAsset
{
	private boolean logged;
	
	public Logout(boolean b)
	{
		logged = b;
	}
	
	public int perform() 
	{
		if(logged) return 1;

		return 0;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isLogged() {
		return logged;
	}

}
