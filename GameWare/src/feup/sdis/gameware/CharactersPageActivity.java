package feup.sdis.gameware;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ListActivity;
import android.content.Intent;

public class CharactersPageActivity extends ListActivity{
	
	private static int[] ID_CHARACTERS;
	private static String[] CHARACTERS_NAMES;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	  super.onCreate(savedInstanceState);
	  
	  //Gets the Extra Arguments
	  Bundle extras = getIntent().getExtras();
	  ID_CHARACTERS = extras.getIntArray("CHARACTERS_ID_ARRAY");
	  CHARACTERS_NAMES = extras.getStringArray("CHARACTERS_STRING_ARRAY");
	  
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.characters_page, CHARACTERS_NAMES));

	  ListView lv = getListView();
	  lv.setBackgroundResource(R.drawable.fundo_400x800);
	  lv.setTextFilterEnabled(true);
	  
	  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {

		    	Intent myIntent = new Intent(CharactersPageActivity.this, CharacterPageActivity.class);
		    	myIntent.putExtra("CHAR_ID", ID_CHARACTERS[position]);
		    	startActivity(myIntent);
		    }

		  });
	}
}
