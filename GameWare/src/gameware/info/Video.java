package gameware.info;

public class Video 
{
	private String title;
	private String ID;
	private String streamKey;
	private String streamURL;
	
	public Video()
	{
		
	}
	
	public Video(String tit, String id)
	{
		setTitle(tit);
		setID(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getStreamKey() {
		return streamKey;
	}

	public void setStreamKey(String streamKey) {
		this.streamKey = streamKey;
	}

	public String getStreamURL() {
		return streamURL;
	}

	public void setStreamURL(String streamURL) {
		this.streamURL = streamURL;
	}
}
