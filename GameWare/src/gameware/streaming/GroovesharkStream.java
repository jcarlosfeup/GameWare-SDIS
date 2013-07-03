package gameware.streaming;

import feup.sdis.gameware.JSONHandler;
import org.json.*;

public class GroovesharkStream extends Stream
{
	private String sessionValue = "94e9697e890ba17a5d18e9ba73d63d5e"; //TODO: Mudar para sessões dinâmicas
	private String key = "uniofporto";
	private String link = "http://api.grooveshark.com/ws/3.0/?sig=";
	private String method = "getSubscriberStreamKey";
	private int songID;
	private int bitrateAvailable;
	
	public GroovesharkStream(int song, int bitrate)
	{
		songID = song;
		bitrateAvailable = bitrate;
	}
	
	public void performSearch() throws Exception
	{
		JSONObject toGetCountry = new JSONObject();
		JSONObject countryHeader = new JSONObject();
		countryHeader.put("wsKey", key);
		toGetCountry.put("method", "getCountry");
		toGetCountry.put("header", countryHeader);
		toGetCountry.put("parameters", new JSONArray());
		JSONHandler country = new JSONHandler(link, toGetCountry, "Grooveshark", "POST", "");
		
		System.out.println(toGetCountry.toString());
		
		JSONObject toGetStream = getStreamFormation((JSONObject)country.getJsonFinal().get("result"));
		
		JSONHandler streamKeyGen = new JSONHandler(link, toGetStream, "Grooveshark", "POST", "");
		JSONObject streamKeyJSON = streamKeyGen.getJsonFinal();
		JSONObject streamResults = (JSONObject) streamKeyJSON.get("result");
		
		String streamKey = streamResults.getString("StreamKey");
		String streamLink = streamResults.getString("url");
		StreamResult result = new StreamResult();
		
		result.setStreamKey(streamKey);
		result.setStreamLink(streamLink);
		
		this.setResults(result);
	}
	
	public JSONObject getStreamFormation(JSONObject resultCountry) throws JSONException
	{
		JSONObject result = new JSONObject();
		result.put("method", method);
		
		JSONObject headerStream = new JSONObject();
		headerStream.put("wsKey", key);
		headerStream.put("sessionID", sessionValue);
		
		JSONObject parameters = new JSONObject();
		parameters.put("songID", songID);
		parameters.put("country", resultCountry);
		
		if(bitrateAvailable == 1) parameters.put("lowBitrate", 1);
		else parameters.put("lowBitrate", "");
		
		parameters.put("uniqueID", "");
		
		result.put("header", headerStream);
		result.put("parameters", parameters);
		
		return result;
	}
}
