package feup.sdis.gameware;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BookmarksActivity extends ListActivity
{
	private static String[] GAME_NAMES;
	private static int[] ID_RESULTS;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	  super.onCreate(savedInstanceState);
	  
	  //Gets the Extra Arguments
	  Bundle extras = getIntent().getExtras();
	  GAME_NAMES = extras.getStringArray("Name Array");
	  ID_RESULTS = extras.getIntArray("ID Array");
	  
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.bookmarks_page, GAME_NAMES));

	  ListView lv = getListView();
	  lv.setBackgroundResource(R.drawable.fundo_400x800);
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {

	    	Intent myIntent = new Intent(BookmarksActivity.this, GamePageActivity.class);
	    	myIntent.putExtra("ID_GAME", ID_RESULTS[position]);
	    	startActivity(myIntent);
	    }

	  });
	}
}
