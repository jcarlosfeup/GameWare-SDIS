package gameware.server.bookmark;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import feup.sdis.gameware.JSONHandler;

//Check it out

public class Insert implements BookmarkAsset
{
	private int username;
	private int game;
	
	public Insert(int user, int g)
	{
		username = user;
		game = g;
	}
	
	public int perform()
	{
		String link = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/bookmark/?";
		String data = "";
		try
		{
			data = String.format("gameID=%s&userID=%s", 
				     URLEncoder.encode(game+"", "UTF-8"),
				     URLEncoder.encode(username+"", "UTF-8"));
		}
		catch(UnsupportedEncodingException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONHandler handler = null;
		try 
		{
			handler = new JSONHandler(link, null, "Service", "PUT", data);
		} 
		catch (Exception e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONObject result = handler.getJsonFinal();
		int res = 0;
		try 
		{
			res = result.getInt("insert");
		}
		catch (JSONException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		return res;
	}

}
