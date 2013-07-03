package gameware.search;

public class Result 
{
	private String name;
	private int ID;
	private String vidID;
	private String maker;
	private String albumName;
	private int isLowBitRateAvailable;
	private String imageURL;
	
	public Result()
	{
		
	}
	
	public Result(String _name, String _maker, int _ID)
	{
		name = _name;
		maker = _maker;
		ID = _ID;
	}
	
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setIsLowBitRateAvailable(int isLowBitRateAvailable) {
		this.isLowBitRateAvailable = isLowBitRateAvailable;
	}

	public int getIsLowBitRateAvailable() {
		return isLowBitRateAvailable;
	}

	public void setVidID(String vidID) {
		this.vidID = vidID;
	}

	public String getVidID() {
		return vidID;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}
	
	
}

