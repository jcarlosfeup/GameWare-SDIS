package feup.sdis.gameware;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.*;
import org.w3c.dom.Document;

import android.util.Log;

public class JSONHandler 
{
	private URL infoURL;
	private String link;
	private String jsonResult;
	private Document domDocument;
	private JSONObject jsonInfo;
	private JSONObject jsonFinal;
	private static String privateGroovesharkKey = "12c4808dd0e567e5d2f46e482a36d064";
	private String service;
	private String requestedMethod;
	private String arguments;
	
	public JSONHandler(String link, JSONObject json, String serv, String req, String args) throws Exception
	{
		setLink(link);
		setService(serv);
		setRequestedMethod(req);
		setArguments(args);
		jsonInfo = json;
	    getResponse();
	}
	
	public void getResponse() throws Exception
	{
		if(service.equals("Grooveshark")) link += getHmacMD5(jsonInfo.toString());
		
		if(requestedMethod.equals("GET")) infoURL = new URL(link+arguments);
		else infoURL = new URL(link);
		
		HttpURLConnection connection = (HttpURLConnection) infoURL.openConnection();
		connection.setRequestMethod(requestedMethod);
		connection.setRequestProperty("Accept-Charset", "UTF-8");

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);

        if(service.equals("Grooveshark"))
        {
        	//connection.setRequestProperty("Content-Type","application/json");
	        OutputStream output = connection.getOutputStream();
	        output.write(jsonInfo.toString().getBytes());
        }
        else if(requestedMethod.equals("POST") || requestedMethod.equals("PUT"))
        {
        	OutputStream output = connection.getOutputStream();
	        output.write(arguments.getBytes());
        }
        
		connection.connect();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()), 8*1024);
		StringBuilder sb = new StringBuilder();
		
		String line;
		while ((line = rd.readLine()) != null)
		{
			System.out.println(line);
			sb.append(line);
		}
		
		connection.disconnect();
		
		//System.out.println(sb.toString());
		
	    setJsonResult(sb.toString());
	    
	    jsonFinal = new JSONObject(jsonResult);
	    
	    Log.i("Resultado JSON", jsonResult);
	    
	    
	}

	public static String getHmacMD5(String input) throws Exception
	{
		byte[] keyBytes = privateGroovesharkKey.getBytes();
		Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacMD5"); 
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(key); 
		return byteArrayToHex(mac.doFinal(input.getBytes()));
	}
	
	protected static String byteArrayToHex(byte [] a) 
	{
		int hn, ln, cx;
		String hexDigitChars = "0123456789abcdef";
		StringBuffer buf = new StringBuffer(a.length * 2);
		for(cx = 0; cx < a.length; cx++) {
			hn = ((int)(a[cx]) & 0x00ff) / 16;
			ln = ((int)(a[cx]) & 0x000f);
			buf.append(hexDigitChars.charAt(hn));
			buf.append(hexDigitChars.charAt(ln));
		}
		return buf.toString();
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setDomDocument(Document domDocument) {
		this.domDocument = domDocument;
	}

	public Document getDomDocument() {
		return domDocument;
	}

	public void setJsonInfo(JSONObject jsonInfo) {
		this.jsonInfo = jsonInfo;
	}

	public JSONObject getJsonInfo() {
		return jsonInfo;
	}

	public void setJsonFinal(JSONObject jsonFinal) {
		this.jsonFinal = jsonFinal;
	}

	public JSONObject getJsonFinal() {
		return jsonFinal;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getRequestedMethod() {
		return requestedMethod;
	}

	public void setRequestedMethod(String requestedMethod) {
		this.requestedMethod = requestedMethod;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}
}