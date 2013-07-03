package gameware.search;

import java.util.ArrayList;
import org.json.*;
import android.util.Log;
import feup.sdis.gameware.JSONHandler;

/**
 * Class that performs a single simple search in the Grooveshark for music related
 * to the search query.
 * @author Gameware Group
 *
 */
public class GroovesharkSearch extends Search
{
	//private String sessionValue = "94e9697e890ba17a5d18e9ba73d63d5e";
	private String key = "uniofporto";
	private String link = "http://api.grooveshark.com/ws3.php?sig=";
	private String method = "getSongSearchResults";
	private int limit;
	
	public GroovesharkSearch(String srch, String service, int lim) 
	{
		super(srch, service);
		limit = lim;
		try {
			performSearch();
		} catch (Exception e) {
			Log.e("Error:", e.getMessage());
		}
	}
	
	public void performSearch() throws Exception
	{
		JSONObject search = new JSONObject();
		search.put("method", method);
		
		JSONObject header = new JSONObject();
		header.put("wsKey", key);
		
		JSONObject parameters = new JSONObject();
		parameters.put("query", getSearchQuery());
		parameters.put("country", "Portugal");
		parameters.put("limit", limit);
		parameters.put("offset", "");
		
		search.put("header", header);
		search.put("parameters", parameters);
		
		/*String hmacMD5 ="";
		try {
			hmacMD5 = JSONHandler.getHmacMD5(search.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String xmlLink = "http://gnomo.fe.up.pt/~ei09027/Gameware/api/groove/?signature=" + hmacMD5;
		xmlLink += "&json="+search.toString();
		
		String data = URLEncoder.encode(xmlLink, "UTF-8");
		
		System.out.println(xmlLink);
		
		XMLHandler xml = new XMLHandler(xmlLink, "Grooveshark");
		
		System.out.println(xml.getXmlResult());*/
		
		
		JSONHandler jsonHandler = new JSONHandler(link, search, "Grooveshark", "POST", "");
		
		JSONObject finalResult = jsonHandler.getJsonFinal();

		JSONArray songs = finalResult.getJSONObject("result").getJSONArray("songs");
		
		System.out.println(songs.length());
		
		ArrayList<Result> allResults = new ArrayList<Result>();
		
		for(int i = 0; i < songs.length(); i++)
		{
			JSONObject inner = (JSONObject) songs.get(i);
			String name = inner.getString("SongName");
			int ID = inner.getInt("SongID");
			String artist = inner.getString("ArtistName");
			Result res = new Result(name, artist, ID);
			res.setAlbumName(inner.getString("AlbumName"));
			
			boolean low = inner.getBoolean("IsLowBitrateAvailable");
			
			int bit = 0;
			if(low) bit = 1;
			else bit = 0;
			res.setIsLowBitRateAvailable(bit);
			
			allResults.add(res);
		}
		
		setResults(allResults);
	}
}
