package gameware.search;
import java.io.IOException;
import java.util.*;
import org.w3c.dom.*;

import android.util.Log;
import feup.sdis.gameware.*;

public class GiantBombSearch extends Search
{
	private String devKey = "aa3a27f7b1d571ec93c3e3d11af0b681eb3eefaa";

	public GiantBombSearch(String srch, String service) 
	{
		super(srch, service);
		try {
			performSearch();
		} catch (IOException e) {
			Log.e("Error:", e.getMessage());
		}
	}
	
	public void performSearch() throws IOException
	{
		String link = "http://api.giantbomb.com/search/?api_key=";
		link += devKey;
		link += "&format=xml";
		link += "&query=" + this.getSearchQuery();
		link += "&resources=game";
		
		XMLHandler xmlHandler =  new XMLHandler(link, "Giant Bomb");
		
		int nResults = xmlHandler.getResult();

		if(nResults <= 20)
		{
			Element root = xmlHandler.getRootElement();
			NodeList rootChildren = root.getChildNodes();

			NodeList resultChildren = rootChildren.item(5).getChildNodes();
			
			ArrayList<Result> resultList = new ArrayList<Result>();
			
			for(int i = 0; i < resultChildren.getLength(); i++)
			{
				Node game = resultChildren.item(i);
				NodeList gameInfo = game.getChildNodes();
				String name = "";
				String maker = "";
				String image = "";
				int ID = 0;
				for(int j = 0; j < gameInfo.getLength(); j++)
				{
					Node inner = gameInfo.item(j);
					if(inner.getNodeName().equals("id")) ID = Integer.parseInt(xmlHandler.getElementValue(inner));
					else if(inner.getNodeName().equals("image"))
					{
						NodeList imageChildren = inner.getChildNodes();
						image = xmlHandler.getElementValue(imageChildren.item(6));
					}
					else if(inner.getNodeName().equals("name")) name = xmlHandler.getElementValue(inner);
				}
								
				Result r = new Result(name, maker, ID);
				r.setImageURL(image);
				resultList.add(r);
			}
			
			setResults(resultList);
		}
		else
		{
			ArrayList<Result> resultList = new ArrayList<Result>();
			ArrayList<Document> documents = xmlHandler.getDocuments();

			for(int b = 0; b < documents.size(); b++)
			{
				Element root = documents.get(b).getDocumentElement();
				NodeList rootChildren = root.getChildNodes();
				NodeList resultChildren = rootChildren.item(5).getChildNodes();
				
				for(int i = 0; i < resultChildren.getLength(); i++)
				{
					Node game = resultChildren.item(i);
					NodeList gameInfo = game.getChildNodes();
					String name = "";
					String maker = "";
					int ID = 0;
					for(int j = 0; j < gameInfo.getLength(); j++)
					{
						Node inner = gameInfo.item(j);
						if(inner.getNodeName().equals("id")) ID = Integer.parseInt(xmlHandler.getElementValue(inner));
						else if(inner.getNodeName().equals("name")) name = xmlHandler.getElementValue(inner);
					}


					Result r = new Result(name, maker, ID);
					resultList.add(r);
				}
			}
			
			setResults(resultList);
		}
	}
}