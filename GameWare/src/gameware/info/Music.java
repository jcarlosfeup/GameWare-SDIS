package gameware.info;

public class Music 
{
	private String name;
	private int ID;
	private String artist;
	private String album;
	private int lowBitrate;
	private String streamKey;
	private String streamURL;
	
	public Music()
	{
		
	}
	
	public Music(String n, int id, String art, String alb, int low)
	{
		setName(n);
		setID(id);
		setArtist(art);
		setAlbum(alb);
		setLowBitrate(low);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getLowBitrate() {
		return lowBitrate;
	}

	public void setLowBitrate(int lowBitrate) {
		this.lowBitrate = lowBitrate;
	}

	public String getStreamURL() {
		return streamURL;
	}

	public void setStreamURL(String streamURL) {
		this.streamURL = streamURL;
	}

	public String getStreamKey() {
		return streamKey;
	}

	public void setStreamKey(String streamKey) {
		this.streamKey = streamKey;
	}
	
	
}
