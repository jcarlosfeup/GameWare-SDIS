package feup.sdis.gameware;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResultsActivity extends ListActivity {
	private static String[] SEARCH_RESULTS;
	private static int[] ID_RESULTS;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.search_page);
		// Gets the Extra Arguments
		Bundle extras = getIntent().getExtras();
		SEARCH_RESULTS = extras.getStringArray("Name Array");
		ID_RESULTS = extras.getIntArray("ID Array");

		if (SEARCH_RESULTS.length == 0) {
			String[] noResult = { "No results match your query!" };
			setListAdapter(new ArrayAdapter<String>(this,
					R.layout.search_results, noResult));
			ListView lv = getListView();
			lv.setBackgroundResource(R.drawable.fundo_400x800);
			lv.setTextFilterEnabled(true);
		} else {
			// Displays the Names of the Games according to the Search

			setListAdapter(new ArrayAdapter<String>(this,
					R.layout.search_results, SEARCH_RESULTS));

			ListView lv = getListView();
			lv.setBackgroundResource(R.drawable.fundo_400x800);
			lv.setTextFilterEnabled(true);

			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {
					final ProgressDialog progDialog = ProgressDialog.show(
							SearchResultsActivity.this, "",
							"Loading, please wait!", true);
					new Thread() {
						public void run() {
							Intent myIntent = new Intent(
									SearchResultsActivity.this,
									GamePageActivity.class);
							myIntent.putExtra("ID_GAME", ID_RESULTS[position]);
							startActivity(myIntent);
							progDialog.dismiss();
						}
					}.start();
				}

			});
		}
	}
}