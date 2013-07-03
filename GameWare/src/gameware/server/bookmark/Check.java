package gameware.server.bookmark;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import feup.sdis.gameware.JSONHandler;

public class Check implements BookmarkAsset
{
	private int userID;
	private int gameID;
	
	public Check(int id, int game)
	{
		setUserID(id);
		setGameID(game);
	}
	
	public int perform() 
	{
		String link = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/bookmark/?";
		String data = "";
		try
		{
			data = String.format("gameID=%s&userID=%s", 
				     URLEncoder.encode(gameID+"", "UTF-8"),
				     URLEncoder.encode(userID+"", "UTF-8"));
		}
		catch(UnsupportedEncodingException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONHandler handler = null;
		try 
		{
			handler = new JSONHandler(link, null, "Service", "POST", data);
		} 
		catch (Exception e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONObject result = handler.getJsonFinal();

		try {
			return result.getInt("Check");
		} catch (JSONException e) {
			Log.e("Error", e.getMessage());
		}
		
		return -1;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getGameID() {
		return gameID;
	}
	
}
