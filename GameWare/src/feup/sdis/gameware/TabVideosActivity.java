package feup.sdis.gameware;

import java.util.ArrayList;

import gameware.info.*;
import gameware.streaming.*;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TabVideosActivity extends ListActivity
{
	private static Game game;
	private static String[] VIDEO_NAMES;
	private static String[] ID_RESULTS;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	  super.onCreate(savedInstanceState);
	  
	  //Gets the Extra Arguments
	  Bundle extras = getIntent().getExtras();
	  game = (Game) extras.get("CHOSEN_GAME");
	  
	  ArrayList<Video> videos = game.getVideos();
	  VIDEO_NAMES = new String[videos.size()];
	  ID_RESULTS = new String[videos.size()];
	  
	  for(int i = 0; i < videos.size(); i++)
	  {
		  Video v = videos.get(i);
		  VIDEO_NAMES[i] = v.getTitle();
		  ID_RESULTS[i] = v.getID();
	  }
	  
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.characters_page, VIDEO_NAMES));

	  ListView lv = getListView();
	  lv.setBackgroundResource(R.drawable.fundo_400x800);
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    	
	    	YoutubeStream yStream = new YoutubeStream(ID_RESULTS[position]);
	    	yStream.performSearch();
	    	StreamResult res = yStream.getResults();
	    	String link = res.getStreamLink();
	    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
	    }

	  });
	}
}
