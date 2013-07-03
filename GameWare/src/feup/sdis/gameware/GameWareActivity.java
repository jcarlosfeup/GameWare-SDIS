package feup.sdis.gameware;

import gameware.info.Game;
import gameware.search.GiantBombSearch;
import gameware.server.bookmark.*;
import gameware.server.user.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameWareActivity extends Activity {
	
	private GiantBombSearch searchGame;
	
	protected void alertbox(String title, String mymessage)
	{
	   new AlertDialog.Builder(this)
	      .setMessage(mymessage)
	      .setTitle(title)
	      .setCancelable(true)
	      .setNeutralButton(android.R.string.cancel,
	         new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton){}
	         })
	      .show();
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //App State
        final GameWareApp gameWareState = ((GameWareApp)getApplicationContext());
        final User user = gameWareState.getUser();

        //Register Button
        Button registerButton = (Button) findViewById(R.id.register_button);
        //Login Button
        Button loginButton = (Button) findViewById(R.id.login_button);
        //Saudation username label
        TextView UsernameLabel = (TextView) findViewById(R.id.username_label);
        //Bookmarks button
        Button bookmarksButton = (Button) findViewById(R.id.bookmarks_button);
        //Logout button
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        //Search Button
    	Button searchButton = (Button) findViewById(R.id.search_button);
    	//Search box
    	final EditText searchBox = (EditText) findViewById(R.id.search_edit);
        
        if(user.isLogged())
        {
        	registerButton.setVisibility(8); //GONE
        	loginButton.setVisibility(8); //GONE
        	UsernameLabel.setText(user.getUsername());
        	UsernameLabel.setVisibility(0); //VISIBLE
        	logoutButton.setVisibility(0); //VISIBLE
        	bookmarksButton.setVisibility(0); //VISIBLE
        	
        }else{
        	registerButton.setVisibility(0); //VISIBLE
        	loginButton.setVisibility(0); //VISIBLE
        	UsernameLabel.setVisibility(8); //GONE
        	UsernameLabel.setVisibility(8); //GONE
        	bookmarksButton.setVisibility(8); //GONE
        	logoutButton.setVisibility(8); //GONE
        }
    	
        searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final ProgressDialog progDialog = ProgressDialog.show(GameWareActivity.this, "", 
                        "Searching. Please wait...", true);
				new Thread() {
					public void run(){
				    	searchGame = new GiantBombSearch(searchBox.getText().toString(), "Giant Bomb");
						
						Intent myIntent = new Intent(GameWareActivity.this, SearchResultsActivity.class);
						
						Bundle extraArguments = new Bundle();
						
						extraArguments.putIntArray("ID Array", searchGame.getIdArray());
						extraArguments.putStringArray("Name Array", searchGame.getNameArray());
						extraArguments.putStringArray("Images Array", searchGame.getImagesArray());
						myIntent.putExtras(extraArguments);
		
						startActivity(myIntent);
						progDialog.dismiss();
					}
				}.start();
			}
		});
        
        loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(GameWareActivity.this, LoginActivity.class);
				startActivity(myIntent);
			}
		});	
        
        registerButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(GameWareActivity.this, RegisterActivity.class);
				startActivity(myIntent);
			}
		});	
        
        logoutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				user.setLogged(false);
				Intent myIntent = new Intent(GameWareActivity.this, GameWareActivity.class);
				startActivity(myIntent);
			}
		});	
        
        bookmarksButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final ProgressDialog progDialog = ProgressDialog.show(GameWareActivity.this, "", 
                        "Loading, please wait!", true);
				new Thread() {
					public void run(){
						Intent myIntent = new Intent(GameWareActivity.this, BookmarksActivity.class);
						Bundle extraArguments = new Bundle();
						Bookmark b = new Bookmark();
						Log.i("User ID", user.getID()+"");
						int[] id_array = b.getAllBookmarkIDs(user.getID());
						String[] name_array = new String[id_array.length];
		
						for(int i = 0; i < id_array.length; i++)
						{
							Game g =  new Game(id_array[i]);
							name_array[i] = g.retrieveName();
						}
						
						extraArguments.putStringArray("Name Array", name_array);
						extraArguments.putIntArray("ID Array", id_array);
						myIntent.putExtras(extraArguments);
						startActivity(myIntent);
						progDialog.dismiss();
					}
				}.start();
			}
		});	
    }
}