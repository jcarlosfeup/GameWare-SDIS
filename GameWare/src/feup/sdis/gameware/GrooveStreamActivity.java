package feup.sdis.gameware;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import gameware.streaming.*;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GrooveStreamActivity extends Activity
{
	private MediaPlayer mediaPlayer;
	private String name;
	private String artist;
	private String album;
	private int ID;
	private int bitrate;
	private String streamLink;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_stream);
		
		Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        name = extras.getString("MUSIC_NAME");
        artist = extras.getString("ARTIST_NAME");
        album = extras.getString("ALBUM_NAME");
        ID = extras.getInt("ID_MUSIC");
        bitrate = extras.getInt("BITRATE");
        String url = extras.getString("GAME_IMAGE");
        
        
        GroovesharkStream stream = new GroovesharkStream(ID, bitrate);
        try {
			stream.performSearch();
		} catch (Exception e) {
			e.printStackTrace();
		}
        StreamResult res = stream.getResults();
        streamLink = res.getStreamLink();
        
        mediaPlayer = new MediaPlayer();
        try {
			mediaPlayer.setDataSource(streamLink);
			mediaPlayer.prepare(); 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		mediaPlayer.start();
        
		RelativeLayout stream_layout = (RelativeLayout) findViewById(R.id.music_playing);
		RelativeLayout.LayoutParams lp;
		
        // Add image
        ImageView tv_image = new ImageView(this);
        tv_image.setId(5);
		load_ImageView(tv_image, url);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 3);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.ALIGN_TOP);
		/*lp.topMargin=35;
		lp.leftMargin=15;*/
		lp.width=285;
		lp.height=435;
		tv_image.setLayoutParams(lp);
		
        TextView music_name = new TextView(this);
        music_name.setId(1);
        music_name.setText(name);
        music_name.setTextSize(25);
        music_name.setSingleLine(true);
        music_name.setFocusableInTouchMode(true);
        music_name.setEllipsize(TruncateAt.MARQUEE);
        music_name.setTypeface(null, Typeface.BOLD);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_TOP);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lp.topMargin = 25;
		lp.rightMargin=10;
		lp.leftMargin=15;
		music_name.setLayoutParams(lp);
		
        TextView artist_name = new TextView(this);
        artist_name.setId(2);
        artist_name.setText(artist);
        artist_name.setTextSize(18);
        artist_name.setSingleLine(true);
        artist_name.setFocusableInTouchMode(true);
        artist_name.setEllipsize(TruncateAt.MARQUEE);
        artist_name.setTypeface(null, Typeface.BOLD);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 1);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lp.rightMargin=10;
		lp.leftMargin=15;
		artist_name.setLayoutParams(lp);
		
        TextView album_name = new TextView(this);
        album_name.setId(3);
        album_name.setText(album);
        album_name.setTextSize(18);
        album_name.setSingleLine(true);
        album_name.setFocusableInTouchMode(true);
        album_name.setEllipsize(TruncateAt.MARQUEE);
        album_name.setTypeface(null, Typeface.BOLD);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 2);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lp.rightMargin=10;
		lp.leftMargin=15;
		album_name.setLayoutParams(lp);
		
		final Button b = new Button(this);
		b.setId(4);
		b.setWidth(225);
		b.setHeight(25);
        b.setText("Pause");
        b.setEllipsize(TruncateAt.MARQUEE);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 5);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		//lp.topMargin = 25;
		lp.rightMargin=10;
		lp.leftMargin=15;
		b.setLayoutParams(lp);
		b.setBackgroundResource(R.drawable.button_something);
		
		stream_layout.addView(music_name);
		stream_layout.addView(artist_name);
		stream_layout.addView(album_name);
		stream_layout.addView(tv_image);
		stream_layout.addView(b);
		
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (!mediaPlayer.isPlaying()) 
				{
					mediaPlayer.start();
					b.setText("Pause");
				} else {
					mediaPlayer.pause();
					b.setText("Play");
				}
			}
		});
	}
	
	@Override
	public void onBackPressed()
	{
		mediaPlayer.stop();
		mediaPlayer.release();
		this.finish();
	}
}
