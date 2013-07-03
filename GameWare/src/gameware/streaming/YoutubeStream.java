package gameware.streaming;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import feup.sdis.gameware.XMLHandler;

public class YoutubeStream extends Stream
{
	//Sample Link -> https://gdata.youtube.com/feeds/api/videos/OSpBYvUfrro?v=2
	
	private String link = "https://gdata.youtube.com/feeds/api/videos/";
	private String videoID;
	
	public YoutubeStream(String id)
	{
		videoID = id;
	}
	
	public void performSearch()
	{
		String innerLink = link;
		innerLink += videoID;
		innerLink += "?v=2";
		
		XMLHandler xmlHandler = new XMLHandler(innerLink, "Youtube");
	
		
		Element root = xmlHandler.getRootElement();
		NodeList nodes = root.getElementsByTagName("content");
		Node node = nodes.item(0);
		NamedNodeMap attributes = node.getAttributes();
		String streamLink = "";
		
		for(int i = 0; i < attributes.getLength(); i++)
		{
			Node attribute = attributes.item(i);
			if(attribute.getNodeName().equals("src")) streamLink = attribute.getNodeValue();
		}

		StreamResult result = new StreamResult();
		result.setStreamLink(streamLink);
		
		this.setResults(result);
	}
}
