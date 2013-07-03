package gameware.streaming;

public class Stream 
{
	private int mediaID;
	private String mediaName;
	private int mediaDurationSec;
	private String streamLink;
	private StreamResult result;
	
	public Stream()
	{
		
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaDurationSec(int mediaDurationSec) {
		this.mediaDurationSec = mediaDurationSec;
	}

	public int getMediaDurationSec() {
		return mediaDurationSec;
	}

	public void setStreamLink(String streamLink) {
		this.streamLink = streamLink;
	}

	public String getStreamLink() {
		return streamLink;
	}

	public void setResults(StreamResult results) {
		this.result = results;
	}

	public StreamResult getResults() {
		return result;
	}
}
