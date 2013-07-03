package gameware.search;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import feup.sdis.gameware.XMLHandler;

public class YoutubeSearch extends Search
{
	private String link = "http://gdata.youtube.com/feeds/api/videos";
	private int limit;
	
	public YoutubeSearch(String srch, String service, int limit)
	{
		super(srch, service);
		this.limit = limit;
		performSearch();
	}

	public void performSearch()
	{
		link += "?q=" + getSearchQuery() + "+gameplay";
		link += "&orderby=relevance";
		link += "&max-results="+limit;
		link += "&v=2";
		
		XMLHandler xmlHandler = new XMLHandler(link, "Youtube");
		
		Element root = xmlHandler.getRootElement();
		if(root == null) return;
		
		NodeList rootChildren = root.getElementsByTagName("entry");
		
		ArrayList<Result> results = new ArrayList<Result>();
		
		for(int i = 0; i < rootChildren.getLength(); i++)
		{
			Node entry = rootChildren.item(i);
			NodeList entryInfo = entry.getChildNodes();
			String id = "";
			String name = "";
			for(int j = 0; j < entryInfo.getLength(); j++)
			{
				Node inner = entryInfo.item(j);
				if(inner.getNodeName().equals("id")) id = xmlHandler.getElementValue(inner);
				if(inner.getNodeName().equals("title")) name = xmlHandler.getElementValue(inner);
			}
			
			String[] getChunks = id.split(":");
			String vidID = getChunks[getChunks.length-1];
			
			Result r = new Result();
			r.setName(name);
			r.setVidID(vidID);
			results.add(r);
		}
		
		setResults(results);
	}
}