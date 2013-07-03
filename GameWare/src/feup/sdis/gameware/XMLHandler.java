package feup.sdis.gameware;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import android.util.Log;

public class XMLHandler 
{
	private URL infoURL;
	private String link;
	private String xmlResult;
	private ArrayList<String> variousXMLs;
	private Document domDocument;
	private ArrayList<Document> domDocuments;
	private int result;

	public XMLHandler(String link, String service)
	{
		this.link = link;
		System.out.println(this.link);
		try {
			infoURL = new URL(link);
		} catch (MalformedURLException e) {
			Log.e("Error: ", e.getMessage());
		}
	    try {
			getResponse();
		} catch (IOException e) {
			Log.e("ErrorCons: ", e.getMessage(), e);
		}
	    domDocument = getDomElement(xmlResult);
	    variousXMLs = new ArrayList<String>();
	    domDocuments = new ArrayList<Document>();
	    if(service.equals("Giant Bomb")) checkForMultiple();
	}
	
	public void getResponse() throws IOException
	{
		HttpURLConnection conn = (HttpURLConnection) infoURL.openConnection();
		
		conn.connect();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		
		String line;
		while ((line = rd.readLine()) != null) 
			sb.append(line).append('\n');
	   
	    setXmlResult(sb.toString());
	    conn.disconnect();
	}
	
	public void getResponseMultiple(String provisory) throws IOException
	{
		URL generic = new URL(provisory);
		HttpURLConnection conn = (HttpURLConnection) generic.openConnection();
		
		conn.connect();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		
		String line;
		while ((line = rd.readLine()) != null) 
			sb.append(line).append('\n');
		
	    variousXMLs.add(sb.toString());
	    domDocuments.add(getDomElement(sb.toString()));
	    
	    conn.disconnect();
	}
	
	private void checkForMultiple()
	{
		String value = getValue(domDocument.getDocumentElement(), "number_of_total_results");
		int nResults = Integer.parseInt(value);
		result = nResults;
		if(nResults > 20)
		{
			variousXMLs.add(xmlResult);
			domDocuments.add(domDocument);
			for(int i = 20; i < nResults; i+=20)
			{
				String provisoryLink = link;
				provisoryLink += "&offset="+i;
				try {
					getResponseMultiple(provisoryLink);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Document getDomElement(String xml)
	{
		Document dom = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                dom = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
                Log.e("ErrorParse: ", e.getMessage());
            } catch (SAXException e) {
                Log.e("ErrorSAX: ", e.getMessage());
            } catch (IOException e) {
                Log.e("ErrorIO: ", e.getMessage());
            }
        
        return dom;
    }
	
	public String getValue(Element item, String str)
	{
	    NodeList n = item.getElementsByTagName(str);
	    return this.getElementValue(n.item(0));
	}
	 
	public final String getElementValue(Node elem)
	{
         Node child;
         if(elem != null)
         {
             if (elem.hasChildNodes())
             {
                 for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() )
                 {
                     if(child.getNodeType() == Node.TEXT_NODE )
                         return child.getNodeValue();
                     else if(child.getNodeType() == Node.CDATA_SECTION_NODE)
                     {
                    	 CharacterData cd = (CharacterData) child;
                    	 return cd.getData();
                     }
                 }
             }
         }
         return "";
	  }

	public void setInfoURL(URL infoURL) {
		this.infoURL = infoURL;
	}


	public URL getInfoURL() {
		return infoURL;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getLink() {
		return link;
	}

	public void setXmlResult(String xmlResult) {
		this.xmlResult = xmlResult;
	}

	public String getXmlResult() {
		return xmlResult;
	}

	public ArrayList<String> getVariousXMLs() {
		return variousXMLs;
	}

	public void setVariousXMLs(ArrayList<String> variousXMLs) {
		this.variousXMLs = variousXMLs;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public ArrayList<Document> getDocuments()
	{
		return domDocuments;
	}

	public void getDocuments(ArrayList<Document> docs)
	{
		domDocuments = docs;;
	}
	
	
	public Element getRootElement()
	{
		if(domDocument == null) return null;
		return domDocument.getDocumentElement();
	}
}
