package gameware.info;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import feup.sdis.gameware.XMLHandler;

public class CharInfo 
{
	private String name;
	private String imageURL;
	private String firstAppearance; //Overview
	private String deck;
	private String birthday;
	private String aliases;
	private String link = "http://api.giantbomb.com/character/";
	private String devKey = "aa3a27f7b1d571ec93c3e3d11af0b681eb3eefaa";
	private int ID;
	
	public CharInfo(int id)
	{
		setID(id);	
		retrieveCharInfo();
	}
	
	private void retrieveCharInfo()
	{
		String innerLink = link;
		innerLink += ID;
		innerLink += "/?api_key=" + devKey;
		innerLink += "&format=xml";
		
		XMLHandler gameHandler = new XMLHandler(innerLink, "Giant Bomb");
		Element root = gameHandler.getRootElement();
		NodeList rootChildren = root.getChildNodes();		
		NodeList resultChildren = rootChildren.item(5).getChildNodes();
		
		for(int i = 0; i < resultChildren.getLength(); i++)
		{
			Node resultChild = resultChildren.item(i);
			String childName = resultChild.getNodeName();
			
			if(childName.equals("aliases")) setAliases(gameHandler.getElementValue(resultChild));
			else if(childName.equals("birthday")) setBirthday(gameHandler.getElementValue(resultChild));
			else if(childName.equals("deck")) setDeck(gameHandler.getElementValue(resultChild));
			else if(childName.equals("first_appeared_in_game"))
			{
				NodeList firstChildren = resultChild.getChildNodes();
				setFirstAppearance(gameHandler.getElementValue(firstChildren.item(2)));
			}
			else if(childName.equals("image"))
			{
				NodeList imageChildren = resultChild.getChildNodes();
				setImageURL(gameHandler.getElementValue(imageChildren.item(5)));
			}
			else if(childName.equals("name")) setName(gameHandler.getElementValue(resultChild));
		}
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID() {
		return ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setFirstAppearance(String firstAppearance) {
		this.firstAppearance = firstAppearance;
	}

	public String getFirstAppearance() {
		return firstAppearance;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public String getDeck() {
		return deck;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public String getAliases() {
		String[] aliasesName = aliases.split(" ");
		String result = "";
		for(int i = 0; i < aliasesName.length; i++)
		{
			if(i == aliasesName.length-1) result += aliasesName[i];
			else result += aliasesName[i] + " | ";
		}
		
		return result;
	}
}
