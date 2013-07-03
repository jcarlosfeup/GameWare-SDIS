package gameware.server.bookmark;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import feup.sdis.gameware.JSONHandler;

public class Select implements BookmarkAsset
{
	private int userID;
	private int[] gameIDs;
	
	public Select(int id)
	{
		setUserID(id);
	}
	
	public int perform()
	{
		String link = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/bookmark/?";
		String data = "";
		try
		{
			data = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(userID+"", "UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONHandler handler = null;
		try 
		{
			handler = new JSONHandler(link, null, "Service", "GET", data);
		} 
		catch (Exception e)
		{
			Log.e("Error", e.getMessage());
		}
		
		JSONObject result = handler.getJsonFinal();
		JSONObject bookmarks = null;
		try {
			bookmarks = (JSONObject) result.get("Bookmarks");
		} catch (JSONException e) {
			Log.e("Error", e.getMessage());
		}
		
		try {
			if(bookmarks.getString("None") == "") 
			{
				gameIDs = new int[0];
				return 0;
			}
		} catch (JSONException e1) {
			Log.e("Error", e1.getMessage());
		}
		
		gameIDs = new int[bookmarks.names().length()];
		
		for(int i = 0; i < bookmarks.names().length(); i++)
		{
			try {
				gameIDs[i] = bookmarks.getInt(bookmarks.names().getString(i));
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
			}
		}
		
		return 1;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setGameIDs(int[] gameIDs) {
		this.gameIDs = gameIDs;
	}

	public int[] getGameIDs() {
		return gameIDs;
	}
}
