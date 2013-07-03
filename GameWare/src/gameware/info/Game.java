package gameware.info;

import java.io.Serializable;
import java.util.*;
import org.json.JSONException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import feup.sdis.gameware.XMLHandler;
import gameware.search.*;

public class Game implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private ArrayList<String> publishers;
	private ArrayList<String> platforms;
	private Review review;
	private String coverURL;
	private String superURL;
	private ArrayList<String> types;
	private ArrayList<Location> locations;
	private ArrayList<Character> characters;
	private ArrayList<Music> sounds;
	private ArrayList<Video> videos;
	private String description; //Overview
	private String deck;
	private ArrayList<String> images;
	private String link = "http://api.giantbomb.com/game/";
	private String devKey = "aa3a27f7b1d571ec93c3e3d11af0b681eb3eefaa";
	private int gameID;
	private String[] specTitles;
	private String[] specContents;
	
	public Game(int id)
	{
		characters = new ArrayList<Character>();
		locations = new ArrayList<Location>();
		types = new ArrayList<String>();
		images = new ArrayList<String>();
		publishers = new ArrayList<String>();
		platforms = new ArrayList<String>();
		setSounds(new ArrayList<Music>());
		setVideos(new ArrayList<Video>());
		description = "";
		
		gameID = id;
	}
	
	private void retrieveGameInfo()
	{
		String innerLink = link;
		innerLink += gameID;
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
			
			if(childName.equals("characters"))
			{
				NodeList charChildren = resultChild.getChildNodes();
				
				for(int j = 0; j < charChildren.getLength(); j++)
				{
					Node character = charChildren.item(j);
					NodeList charAtts = character.getChildNodes();

					String _name = gameHandler.getElementValue(charAtts.item(2));
					int id = Integer.parseInt(gameHandler.getElementValue(charAtts.item(1)));
					characters.add(new Character(_name, id));
				}
			}
			else if(childName.equals("deck")) deck = gameHandler.getElementValue(resultChild);
			else if(childName.equals("description")) description += gameHandler.getElementValue(resultChild);
			else if(childName.equals("genres"))
			{
				NodeList genreChildren = resultChild.getChildNodes();
				for(int j = 0; j < genreChildren.getLength(); j++)
				{
					Node genre = genreChildren.item(j);
					types.add(gameHandler.getElementValue(genre.getChildNodes().item(2)));
				}				
			}
			else if(childName.equals("image"))
			{
				NodeList imageChildren = resultChild.getChildNodes();
				superURL = gameHandler.getElementValue(imageChildren.item(4));
				coverURL = gameHandler.getElementValue(imageChildren.item(5));
			}
			else if(childName.equals("images"))
			{
				NodeList imageChildren = resultChild.getChildNodes();
				
				for(int j = 0; j < imageChildren.getLength(); j++)
				{
					Node image = imageChildren.item(j);
					images.add(gameHandler.getElementValue(image.getChildNodes().item(5)));
				}
			}
			else if(childName.equals("locations"))
			{
				NodeList locationChildren = resultChild.getChildNodes();
				for(int j = 0; j < locationChildren.getLength(); j++)
				{
					Node location = locationChildren.item(j);
					String name = gameHandler.getElementValue(location.getChildNodes().item(2));
					int id = Integer.parseInt(gameHandler.getElementValue(location.getChildNodes().item(1)));
					locations.add(new Location(name, id));
				}				
			}
			else if(childName.equals("name")) name = gameHandler.getElementValue(resultChild);
			else if(childName.equals("platforms"))
			{
				NodeList platChildren = resultChild.getChildNodes();
				
				for(int j = 0; j < platChildren.getLength(); j++)
				{
					Node platform = platChildren.item(j);
					platforms.add(gameHandler.getElementValue(platform.getChildNodes().item(2)));
				}				
			}
			else if(childName.equals("publishers"))
			{
				NodeList pubChildren = resultChild.getChildNodes();
				
				for(int j = 0; j < pubChildren.getLength(); j++)
				{
					Node publisher = pubChildren.item(j);
					publishers.add(gameHandler.getElementValue(publisher.getChildNodes().item(2)));
				}					
			}
			else if(childName.equals("reviews"))
			{
				NodeList reviews = resultChild.getChildNodes();
				
				if(reviews.getLength() == 0) continue;
				
				Node rev = reviews.item(0);
				NodeList revAtts = rev.getChildNodes();
				
				String revDeck = gameHandler.getElementValue(revAtts.item(0));
				String revDescription = cleanString(gameHandler.getElementValue(revAtts.item(1)));
				String date = gameHandler.getElementValue(revAtts.item(2));
				String reviewer = gameHandler.getElementValue(revAtts.item(3));
				int score = Integer.parseInt(gameHandler.getElementValue(revAtts.item(4)));
				
				review = new Review(revDeck, revDescription, date, score, reviewer);
			}
		}	
	}
	
	private void retrieveGameSoundtrack() throws JSONException
	{
		GroovesharkSearch search = new GroovesharkSearch(name, "Grooveshark", 25);
		ArrayList<Result> results = search.getResults();
		if(results == null) return;
		for(int i = 0; i < results.size(); i++)
		{
			Result result = results.get(i);
			int ID = result.getID();
			String name = result.getName();
			String album  = result.getAlbumName();
			String artist = result.getMaker();
			int lowBitrate = result.getIsLowBitRateAvailable();

			sounds.add(new Music(name, ID, artist, album, lowBitrate));
		}
	}
	
	private void retrieveGameVideos()
	{
		YoutubeSearch search = new YoutubeSearch(name, "Youtube", 15);
		ArrayList<Result> results = search.getResults();
		if(results == null) return;
		for(int i = 0; i < results.size(); i++)
		{
			Result result = results.get(i);
			String ID = result.getVidID();
			String name = result.getName();

			videos.add(new Video(name, ID));
		}
	}
	
	//Getters and Setters
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPublishers() 
	{
		String result = "";
		
		for(int i = 0; i < publishers.size(); i++)
		{
			if(i == publishers.size()-1) result += publishers.get(i);
			else result += publishers.get(i) + " | ";
		}
		return result;
	}
	
	public void setPublishers(ArrayList<String> publishers) {
		this.publishers = publishers;
	}
	
	public String getPlatforms() 
	{
		String result = "";
		
		for(int i = 0; i < platforms.size(); i++)
		{
			if(i == platforms.size()-1) result += platforms.get(i);
			else result += platforms.get(i) + " | ";
		}
		return result;
	}
	
	public void setPlatforms(ArrayList<String> platforms) {
		this.platforms = platforms;
	}
	
	public String getCoverURL() {
		return coverURL;
	}
	
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	
	public String getTypes() 
	{
		String result = "";
		
		for(int i = 0; i < types.size(); i++)
		{
			if(i == types.size()-1) result += types.get(i);
			else result += types.get(i) + " | ";
		}
		return result;
	}
	
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	
	public ArrayList<Location> getLocations() {
		return locations;
	}
	
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
	
	public ArrayList<Character> getCharacters() {
		return characters;
	}
	
	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<String> getImages() {
		return images;
	}
	
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public String getDeck() {
		return deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public ArrayList<Music> getSounds() {
		return sounds;
	}

	public void setSounds(ArrayList<Music> sounds) {
		this.sounds = sounds;
	}

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}
	
	public String[] getLocationNames()
	{
		String[] array = new String[locations.size()];
		
		for(int i = 0; i < locations.size(); i++)
			array[i] = locations.get(i).getName();
		
		return array;
	}
	
	public int[] getLocationIDs()
	{
		int[] array = new int[locations.size()];
		
		for(int i = 0; i < locations.size(); i++)
			array[i] = locations.get(i).getID();
		
		return array;
	}
	
	public String[] getCharacterNames()
	{
		String[] array = new String[characters.size()];
		
		for(int i = 0; i < characters.size(); i++)
			array[i] = characters.get(i).getName();
		
		return array;
	}
	
	public int[] getChracterIDs()
	{
		int[] array = new int[characters.size()];
		
		for(int i = 0; i < characters.size(); i++)
			array[i] = characters.get(i).getID();
		
		return array;
	}
	
	//Parse Descritpion
	
	public void parseDescription()
	{
		//System.out.println(description);
		String[] divisions = description.split("<h2>");
		specTitles = new String[divisions.length];
		specContents = new String[divisions.length];
		
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> _contents = new ArrayList<String>();
		
		for(int i = 0; i < divisions.length; i++)
		{
			String division = divisions[i];
			String[] innerDiv = division.split("<\\/h2>");
			
			if(innerDiv.length > 1)
			{
				String title = innerDiv[0];
				if(!title.equals("")) title = cleanString(title);
				titles.add(title);
				
				String content = innerDiv[1];
				content = cleanString(content);
				
				//Log.i("Content:" , title + " " + content);
				
				_contents.add(content);
			}
		}
		
		int index = 0;
		for(int i = 0; i < titles.size(); i++)
		{
			if(!titles.get(i).equals("Characters"))
			{
				specTitles[index] = titles.get(i);
				specContents[index] = _contents.get(i);
				index++;
			}
		}
	}

	private String cleanString(String title)
	{
		if(title.charAt(0) == '<')
		{
			boolean canErase = false;
			String result = "";
			for(int j = 0; j < title.length(); j++)
			{
				if(canErase)
				{
					if(title.charAt(j) == '>')
					{
						canErase = false;
						continue;
					}
					
					continue;
				}
				else
				{
					if(title.charAt(j) == '<')
					{
						canErase = true;
						continue;
					}
					else result += title.charAt(j);
				}
			}
			return result;
		}
		
		return title;
	}
	
	public void setSpecTitles(String[] specTitles) {
		this.specTitles = specTitles;
	}

	public String[] getSpecTitles() {
		return specTitles;
	}

	public void setSpecContents(String[] specContents) {
		this.specContents = specContents;
	}

	public String[] getSpecContents() {
		return specContents;
	}
	
	public void retrieveInfo() throws JSONException
	{
		retrieveGameInfo();
		retrieveGameSoundtrack();
		retrieveGameVideos();
		parseDescription();
	}
	
	public String retrieveName()
	{
		String innerLink = link;
		innerLink += gameID;
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
			
			if(childName.equals("name")) name = gameHandler.getElementValue(resultChild);
		}
		
		return name;
	}

	public void setSuperURL(String superURL) {
		this.superURL = superURL;
	}

	public String getSuperURL() {
		return superURL;
	}
}
