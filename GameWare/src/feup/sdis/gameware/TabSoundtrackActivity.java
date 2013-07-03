package feup.sdis.gameware;

import java.util.ArrayList;
import gameware.info.*;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TabSoundtrackActivity extends ListActivity
{
	private static Game game;
	private static String[] MUSIC_NAMES;
	private static int[] ID_RESULTS;
	private static String[] ARTIST_NAMES;
	private static String[] ALBUM_NAMES;
	private static int[] BITRATE;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	  super.onCreate(savedInstanceState);
	  
	  //Gets the Extra Arguments
	  Bundle extras = getIntent().getExtras();
	  game = (Game) extras.get("CHOSEN_GAME");
	  
  	  ArrayList<Music> musics = game.getSounds();
	  MUSIC_NAMES = new String[musics.size()];
	  ARTIST_NAMES = new String[musics.size()];
	  ALBUM_NAMES = new String[musics.size()];
	  ID_RESULTS = new int[musics.size()];
	  BITRATE = new int[musics.size()];
	  
	  for(int i = 0; i < musics.size(); i++)
	  {
		  Music m = musics.get(i);
		  MUSIC_NAMES[i] = m.getName();
		  ARTIST_NAMES[i] = m.getArtist();
		  ALBUM_NAMES[i] = m.getAlbum();
		  ID_RESULTS[i] = m.getID();
		  BITRATE[i] = m.getLowBitrate();
	  }
	  
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.characters_page, MUSIC_NAMES));

	  ListView lv = getListView();
	  lv.setBackgroundResource(R.drawable.fundo_400x800);
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	
		    	Intent myIntent = new Intent(TabSoundtrackActivity.this, GrooveStreamActivity.class);
		    	myIntent.putExtra("ID_MUSIC", ID_RESULTS[position]);
		    	myIntent.putExtra("MUSIC_NAME", MUSIC_NAMES[position]);
		    	myIntent.putExtra("ARTIST_NAME", ARTIST_NAMES[position]);
		    	myIntent.putExtra("ALBUM_NAME", ALBUM_NAMES[position]);
		    	myIntent.putExtra("BITRATE", BITRATE[position]);
		    	myIntent.putExtra("GAME_IMAGE", game.getSuperURL());
		    	startActivity(myIntent);
		    }

		  });
	}
}
