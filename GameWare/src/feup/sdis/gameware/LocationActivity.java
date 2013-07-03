package feup.sdis.gameware;

import gameware.info.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocationActivity extends Activity
{
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
        setContentView(R.layout.character_page);
        
        RelativeLayout tab_info = (RelativeLayout) findViewById(R.id.charPageLayout);
        RelativeLayout.LayoutParams lp;	    
        
		Bundle extras = getIntent().getExtras();
		int idChar = extras.getInt("LOCATION_ID");
		
		LocationInfo info = new LocationInfo(idChar);
        
        // Add image
        ImageView tv_image = new ImageView(this);
        tv_image.setId(1);
		load_ImageView(tv_image, info.getImageURL());
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
		
		tab_info.addView(tv_image);
        
		//Char Name
        TextView char_name = new TextView(this);
        char_name.setId(2);
        char_name.setText(info.getName());
        char_name.setTextSize(20);
        char_name.setSingleLine(true);
        char_name.setFocusableInTouchMode(true);
        char_name.setEllipsize(TruncateAt.MARQUEE);
		lp = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.WRAP_CONTENT, 
			RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_TOP);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		char_name.setLayoutParams(lp);
		
		tab_info.addView(char_name);
		
		//Add generic char info
		//First Appearace Label
		TextView first_appearance_label = new TextView(this);
		first_appearance_label.setId(3);
		first_appearance_label.setText("First Appearance:");
		first_appearance_label.setTypeface(null, 1);
		first_appearance_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 2);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		lp.topMargin = 5;
		first_appearance_label.setLayoutParams(lp);
		
		tab_info.addView(first_appearance_label);
		
		//Aliases Label
		TextView aliases_label = new TextView(this);
		aliases_label.setId(6);
		aliases_label.setText("Aliases:");
		aliases_label.setTypeface(null, 1);
		aliases_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 7);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		lp.topMargin = 5;
		aliases_label.setLayoutParams(lp);
		
		tab_info.addView(aliases_label);
		
		//First Appearance contents
		TextView first_appearance_contents = new TextView(this);
		first_appearance_contents.setId(7);
		if(info.getAliases().equals("")) first_appearance_contents.setText("There was no first appearance");
		else first_appearance_contents.setText(info.getFirstAppearance());
		first_appearance_contents.setSingleLine(false);
		first_appearance_contents.setMaxLines(2);
		first_appearance_contents.setMovementMethod(new ScrollingMovementMethod());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 3);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		first_appearance_contents.setLayoutParams(lp);
		
		tab_info.addView(first_appearance_contents);
				
		//Aliases contents
		TextView aliases_contents = new TextView(this);
		aliases_contents.setId(9);
		if(info.getAliases().equals("")) aliases_contents.setText("This place is known for nothing.");
		else aliases_contents.setText(info.getAliases());
		aliases_contents.setSingleLine(false);
		aliases_contents.setMaxLines(2);
		aliases_contents.setMovementMethod(new ScrollingMovementMethod());
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 6);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);
		lp.rightMargin=10;
		lp.leftMargin=15;
		aliases_contents.setLayoutParams(lp);
		
		tab_info.addView(aliases_contents);
		
		//Description Label
		TextView description_label = new TextView(this);
		description_label.setId(13);
		description_label.setText("Description:");
		description_label.setTypeface(null, 1);
		description_label.setSingleLine(true);
		lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.BELOW, 1);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.rightMargin=10;
		lp.leftMargin=10;
		description_label.setLayoutParams(lp);
		
		tab_info.addView(description_label);
		
		//Description contents
		TextView description_contents = new TextView(this);
		description_contents.setId(14);
		description_contents.setText(info.getDeck());
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
		
		tab_info.addView(description_contents);
		
		//setContentView(tab_info);
	}
}
