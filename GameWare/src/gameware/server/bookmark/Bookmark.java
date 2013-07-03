package gameware.server.bookmark;

public class Bookmark 
{
	public Bookmark()
	{
		
	}
	
	public int[] getAllBookmarkIDs(int username)
	{
		Select s = new Select(username);
		s.perform();
		return s.getGameIDs();
	}
	
	public boolean checkGame(int username, int bookmark)
	{
		Check c = new Check(username, bookmark);
		if(c.perform() == 1) return true;
		
		return false;
	}
	
	public boolean insert(int user, int game)
	{
		Insert i = new Insert(user, game);
		if(i.perform() == 1) return true;
		
		return false;
	}
	
	public int delete(int game, int user)
	{
		Delete d = new Delete(game, user);
		return d.perform();
	}	
}
