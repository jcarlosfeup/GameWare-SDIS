package gameware.search;

import java.util.ArrayList;

public class Search 
{
	private String searchQuery;
	private ArrayList<Result> results;
	private String[] nameArray;
	private int[] idArray;
	private String[] devsArray;
	private String[] imagesArray;
	
	public Search(String srch, String service)
	{
		if(!service.equals("Grooveshark"))
			setSearchQuery(convertQuery(srch, service));
		else
			setSearchQuery(srch);
	}
	
	private String convertQuery(String query, String service)
	{
		if(service.equals("Giant Bomb"))
		{
			int size = query.length();
			String result = "";
			
			for(int i = 0; i < size; i++)
			{
				if(query.charAt(i) == ' ')
					result += "%20";
				else
					result += query.charAt(i);
			}
			
			return result;
		}

		int size = query.length();
		String result = "";
		
		for(int i = 0; i < size; i++)
		{
			if(query.charAt(i) == ' ')
				result += "+";
			else
				result += query.charAt(i);
		}
		
		return result;
	}
	
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	public ArrayList<Result> getResults() {
		return results;
	}
	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}

	public String[] getNameArray()
	{
		nameArray = new String[results.size()];
		for(int i = 0; i < results.size(); i++)
			nameArray[i] = results.get(i).getName();

		return nameArray;
	}

	public void setNameArray(String[] nameArray) {
		this.nameArray = nameArray;
	}

	public void setIdArray(int[] idArray) {
		this.idArray = idArray;
	}

	public int[] getIdArray() {
		idArray = new int[results.size()];
		for(int i = 0; i < results.size(); i++)
			idArray[i] = results.get(i).getID();
		
		return idArray;
	}

	public void setDevsArray(String[] devsArray) {
		this.devsArray = devsArray;
	}

	public String[] getDevsArray()
	{
		devsArray = new String[results.size()];
		for(int i = 0; i < results.size(); i++)
			devsArray[i] = results.get(i).getMaker();
		return devsArray;
	}

	public void setImagesArray(String[] imagesArray) {
		this.imagesArray = imagesArray;
	}

	public String[] getImagesArray() {
		imagesArray = new String[results.size()];
		for(int i = 0; i < results.size(); i++)
			imagesArray[i] = results.get(i).getImageURL();
		
		return imagesArray;
	}
	
}
