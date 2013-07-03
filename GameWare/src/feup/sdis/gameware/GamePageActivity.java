package feup.sdis.gameware;

import gameware.info.Game;
import org.json.JSONException;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class GamePageActivity extends TabActivity{
	private static int ID_GAME;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.game_page);
	    
	    Bundle extras = getIntent().getExtras();
		ID_GAME = extras.getInt("ID_GAME");
		Game chosenGame = new Game(ID_GAME);
		try {
			chosenGame.retrieveInfo();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    //Info Tab
	    intent = new Intent().setClass(GamePageActivity.this, TabInfoActivity.class);
	    intent.putExtra("CHOSEN_GAME", chosenGame);
	    intent.putExtra("ID_GAME", ID_GAME);
	    spec = tabHost.newTabSpec("info").setIndicator("Info",
	                      res.getDrawable(R.drawable.tab_info_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    //Videos Tab
	    intent = new Intent().setClass(GamePageActivity.this, TabVideosActivity.class);
	    intent.putExtra("CHOSEN_GAME", chosenGame);
	    spec = tabHost.newTabSpec("videos").setIndicator("Videos",
	                      res.getDrawable(R.drawable.tab_videos_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    //Soundtrack Tab
	    intent = new Intent().setClass(GamePageActivity.this, TabSoundtrackActivity.class);
	    intent.putExtra("CHOSEN_GAME", chosenGame);
	    spec = tabHost.newTabSpec("soundtrack").setIndicator("Soundtrack",
	                      res.getDrawable(R.drawable.tab_soundtrack_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    //Review Tab
	    intent = new Intent().setClass(GamePageActivity.this, TabReviewsActivity.class);
	    intent.putExtra("CHOSEN_GAME", chosenGame);
	    spec = tabHost.newTabSpec("reviews").setIndicator("Review",
	                      res.getDrawable(R.drawable.tab_review_icons))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}
}
