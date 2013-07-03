package gameware.server.bookmark;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import feup.sdis.gameware.JSONHandler;

public class Delete implements BookmarkAsset
{
	private int game;
	private int user;
	
	public Delete(int g, int u)
	{
		game = g;
		setUser(u);
	}

	public int perform()
	{
		String link = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/bookmark/";
		String data = "";
		try
		{
			data = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(user+"", "UTF-8");
			data += URLEncoder.encode("gameID", "UTF-8") + "=" + URLEncoder.encode(game+"", "UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONHandler handler = null;
		try 
		{
			handler = new JSONHandler(link, null, "Service", "DELETE", data);
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

	public void setUser(int user) {
		this.user = user;
	}

	public int getUser() {
		return user;
	}

}
