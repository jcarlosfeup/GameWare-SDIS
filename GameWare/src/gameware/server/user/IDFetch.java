package gameware.server.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import feup.sdis.gameware.JSONHandler;

public class IDFetch implements UserAsset
{
	private String username;
	private int ID;
	
	public IDFetch(String user)
	{
		username = user;
		setID(perform());
	}
	
	public int perform() 
	{
		String link = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/user/?";
		String data = "";
		try
		{
			data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
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
		int res = 0;
		try 
		{
			res = result.getInt("ID");
		}
		catch (JSONException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		return res;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID() {
		return ID;
	}

}
