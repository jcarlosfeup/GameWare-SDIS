package feup.sdis.gameware;

import gameware.info.Game;
import gameware.server.bookmark.Bookmark;
import gameware.server.user.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabInfoActivity extends Activity{
	
	public static void load_ImageView(ImageView image_elem,String url)
	{
		try
		{
			URL ulrn = new URL(url);
			HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
			InputStream is = con.getInputStream();
			Bitmap bmp = BitmapFactory.decodeStream(is);
		
			if (null != bmp)
			{
				image_elem.setImageBitmap(bmp);
				image_elem.forceLayout();
				image_elem.refreshDrawableState();
				image_elem.invalidate();
			}
		}
		catch(Exception e)
		{
			image_elem.setImageResource(R.drawable.ic_launcher);
		}
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //App State
        final GameWareApp gameWareState = ((GameWareApp)getApplicationContext());
        final User user = gameWareState.getUser();
        
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        final Game chosenGame = (Game) myIntent.getSerializableExtra("CHOSEN_GAME");
        final int idGame = extras.getInt("ID_GAME");
        RelativeLayout tab_info = new RelativeLayout(this);
        RelativeLayout.LayoutParams lp;	    
        
        // Add image
        ImageView tv_image = new ImageView(this);
        tv_image.setId(1);
		load_ImageView(tv_image, chosenGame.getCoverURL());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_TOP);
		lp.topMargin=15;
		lp.leftMargin=15;
		lp.width=190;
		lp.height=290;
		tv_image.setLayoutParams(lp);
        
        //Add image Border
        ImageView image_border = new ImageView(this);
        image_border.setId(4);
        image_border.setBackgroundResource(R.drawable.test_border);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.ALIGN_TOP);
		lp.topMargin=10;
		lp.leftMargin=10;
		lp.width=200;
		lp.height=300;
		image_border.setLayoutParams(lp);
		
        //Add game name
        TextView game_name = new TextView(this);
        game_name.setId(2);
        game_name.setText(chosenGame.getName());
        game_name.setTextSize(20);
        game_name.setSingleLine(true);
        game_name.setFocusableInTouchMode(true);
        game_name.setEllipsize(TruncateAt.MARQUEE);
		lp = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.WRAP_CONTENT, 
			RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_TOP);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		game_name.setLayoutParams(lp);
		
		//Add generic game info
		//Publisher(s) Label
		TextView game_publisher_label = new TextView(this);
		game_publisher_label.setId(3);
		game_publisher_label.setText("Publisher(s):");
		game_publisher_label.setTypeface(null, 1);
		game_publisher_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 2);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		lp.topMargin = 5;
		game_publisher_label.setLayoutParams(lp);
		
		//Plataform(s) Label
		TextView game_platform_label = new TextView(this);
		game_platform_label.setId(5);
		game_platform_label.setText("Platform(s):");
		game_platform_label.setTypeface(null, 1);
		game_platform_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 7);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		lp.topMargin = 5;
		game_platform_label.setLayoutParams(lp);
		
		//Genre(s) Label
		TextView game_genre_label = new TextView(this);
		game_genre_label.setId(6);
		game_genre_label.setText("Genre(s):");
		game_genre_label.setTypeface(null, 1);
		game_genre_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 8);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		lp.topMargin = 5;
		game_genre_label.setLayoutParams(lp);	
		
		//Publisher(s) contents
		TextView game_publisher_contents = new TextView(this);
		game_publisher_contents.setId(7);
		game_publisher_contents.setText(chosenGame.getPublishers());
		game_publisher_contents.setSingleLine(false);
		game_publisher_contents.setMaxLines(2);
		game_publisher_contents.setMovementMethod(new ScrollingMovementMethod());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 3);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		game_publisher_contents.setLayoutParams(lp);
		
		//Platform(s) contents
		TextView game_platforms_contents = new TextView(this);
		game_platforms_contents.setId(8);
		game_platforms_contents.setText(chosenGame.getPlatforms());
		game_platforms_contents.setSingleLine(false);
		game_platforms_contents.setMaxLines(2);
		game_platforms_contents.setMovementMethod(new ScrollingMovementMethod());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 5);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		game_platforms_contents.setLayoutParams(lp);
		
		//Genre(s) contents
		TextView game_genres_contents = new TextView(this);
		game_genres_contents.setId(9);
		game_genres_contents.setText(chosenGame.getTypes());
		game_genres_contents.setSingleLine(false);
		game_genres_contents.setMaxLines(2);
		game_genres_contents.setMovementMethod(new ScrollingMovementMethod());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 6);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		game_genres_contents.setLayoutParams(lp);
		
		//Locations Button
		Button locations_button = new Button(this);
		locations_button.setId(10);
		locations_button.setText("Locations");
		locations_button.setWidth(150);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 1);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.topMargin=20;
		lp.leftMargin=5;
		locations_button.setLayoutParams(lp);
		locations_button.setBackgroundResource(R.drawable.button_something);
		
//		//Gallery Button
//		Button gallery_button = new Button(this);
//		gallery_button.setId(10);
//		gallery_button.setText("Gallery");
//		gallery_button.setWidth(150);
//		lp = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT, 
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		lp.addRule(RelativeLayout.BELOW, 1);
//		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//		lp.leftMargin=5;
//		lp.topMargin=20;
//		gallery_button.setLayoutParams(lp);
		//gallery_button.setBackgroundResource(R.drawable.button);
		
		//Characters Button
		Button characters_button = new Button(this);
		characters_button.setId(11);
		characters_button.setText("Characters");
		
		characters_button.setWidth(150);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 1);
		lp.addRule(RelativeLayout.RIGHT_OF, 10);
		lp.topMargin=20;
		lp.leftMargin=-11;
		characters_button.setLayoutParams(lp);
		characters_button.setBackgroundResource(R.drawable.button_something);
		
		
		//Description Button
		Button descriptions_button = new Button(this);
		descriptions_button.setId(69);
		descriptions_button.setText("Descriptions");
		descriptions_button.setWidth(150);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 1);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.topMargin=20;
		lp.rightMargin=5;
		descriptions_button.setLayoutParams(lp);
		descriptions_button.setBackgroundResource(R.drawable.button_something);
		
		//Description Label
		TextView description_label = new TextView(this);
		description_label.setId(13);
		description_label.setText("Description:");
		description_label.setTypeface(null, 1);
		description_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 10);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.rightMargin=10;
		lp.leftMargin=10;
		description_label.setLayoutParams(lp);
		
		//Description contents
		TextView description_contents = new TextView(this);
		description_contents.setId(14);
		description_contents.setText(chosenGame.getDeck());
		description_contents.setSingleLine(false);
		description_contents.setMaxLines(6);
		description_contents.setMovementMethod(new ScrollingMovementMethod());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 13);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.rightMargin=10;
		lp.leftMargin=10;
		description_contents.setLayoutParams(lp);
		
		//Bookmark Button
		final Bookmark b = new Bookmark();
		final CheckBox bookmark_check = new CheckBox(this);
		bookmark_check.setId(15);
		//Log.i("Check Result", b.checkGame(user.getUserID(), idGame));
		System.out.println(idGame);
		
		if(user.isLogged())
		{
			if(b.checkGame(user.getID(), idGame))
			{
				bookmark_check.setText("Bookmarked");
				bookmark_check.setChecked(true);
			}else{
				bookmark_check.setText("Bookmark");
				bookmark_check.setChecked(false);
			}
		}
		bookmark_check.setWidth(200);
		//bookmark_check.setHeight(15);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		lp.bottomMargin=10;
		bookmark_check.setLayoutParams(lp);
			
			
		//Setting views on screen
		tab_info.addView(image_border);
		tab_info.addView(tv_image);
		tab_info.addView(game_name);
		tab_info.addView(game_publisher_label);
		tab_info.addView(game_platform_label);
		tab_info.addView(game_genre_label);
		tab_info.addView(game_publisher_contents);
		tab_info.addView(game_platforms_contents);
		tab_info.addView(game_genres_contents);
		//tab_info.addView(gallery_button);
		tab_info.addView(characters_button);
		tab_info.addView(locations_button);
		tab_info.addView(descriptions_button);
		tab_info.addView(description_label);
		tab_info.addView(description_contents);
		if(user.isLogged())
		{
			tab_info.addView(bookmark_check);
		}
        setContentView(tab_info);
        
        characters_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
						Intent myIntent = new Intent(TabInfoActivity.this, CharactersPageActivity.class);
						
						myIntent.putExtra("CHARACTERS_ID_ARRAY", chosenGame.getChracterIDs());
						myIntent.putExtra("CHARACTERS_STRING_ARRAY", chosenGame.getCharacterNames());
		
						startActivity(myIntent);
			}
        });
        
        locations_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
						Intent myIntent = new Intent(TabInfoActivity.this, LocationsPageActivity.class);
						
						myIntent.putExtra("LOCATIONS_ID_ARRAY", chosenGame.getLocationIDs());
						myIntent.putExtra("LOCATIONS_STRING_ARRAY", chosenGame.getLocationNames());
		
						startActivity(myIntent);
			}
        });

        descriptions_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
						Intent myIntent = new Intent(TabInfoActivity.this, StoryPageActivity.class);
						
						myIntent.putExtra("TITLE_ARRAY", chosenGame.getSpecTitles());
						myIntent.putExtra("CONTENT_ARRAY", chosenGame.getSpecContents());
		
						startActivity(myIntent);
			}
        });
        
        bookmark_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(bookmark_check.isChecked())
				{
					b.insert(user.getUserID(), idGame);
				}else{
					b.delete(idGame, user.getUserID());
				}
			}
		});
    }
}
