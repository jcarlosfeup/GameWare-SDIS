package gameware.server.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import feup.sdis.gameware.JSONHandler;

public class Login implements UserAsset
{
	private String username;
	private String password;
	
	public Login(String user, String pass)
	{
		setUsername(user);
		setPassword(pass);
	}
	
	public int perform() 
	{
		String link = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/user/";
		String data = "";
		try
		{
			data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
		    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
		int res = 0;
		try 
		{
			res = result.getInt("Login");
		}
		catch (JSONException e)
		{
			Log.e("Error", e.getMessage());
		}
		
		return res;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}
