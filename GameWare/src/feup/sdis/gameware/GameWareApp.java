package feup.sdis.gameware;

import gameware.server.user.User;
import android.app.Application;
import android.media.MediaPlayer;

public class GameWareApp extends Application{
	private User user;
	private MediaPlayer mediaPlayer;
	
	public GameWareApp(){
		user = new User();
		setMediaPlayer(new MediaPlayer());
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User u){
		user = u;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}
