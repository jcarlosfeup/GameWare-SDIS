package feup.sdis.gameware;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LocationsPageActivity extends ListActivity{
	private static int[] ID_LOCATIONS;
	private static String[] LOCATIONS_NAMES;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	  super.onCreate(savedInstanceState);
	  
	  //Gets the Extra Arguments
	  Bundle extras = getIntent().getExtras();
	  ID_LOCATIONS = extras.getIntArray("LOCATIONS_ID_ARRAY");
	  LOCATIONS_NAMES = extras.getStringArray("LOCATIONS_STRING_ARRAY");
	  
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.locations_page, LOCATIONS_NAMES));

	  ListView lv = getListView();
	  lv.setBackgroundResource(R.drawable.fundo_400x800);
	  lv.setTextFilterEnabled(true);
	  
	  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {

		    	Intent myIntent = new Intent(LocationsPageActivity.this, LocationActivity.class);
		    	myIntent.putExtra("LOCATION_ID", ID_LOCATIONS[position]);
		    	startActivity(myIntent);
		    }

		  });
	}
}
