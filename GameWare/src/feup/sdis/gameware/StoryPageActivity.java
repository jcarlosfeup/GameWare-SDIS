package feup.sdis.gameware;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class StoryPageActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_page);
        
        Bundle extras = getIntent().getExtras();
        final String[] titles = extras.getStringArray("TITLE_ARRAY");
        final String[] descriptions = extras.getStringArray("CONTENT_ARRAY");
        
        RelativeLayout story_layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams lp;	
        //story_layout.setBackgroundDrawable(R.drawable.fundo_400x800);
        
        for(int i = 0; i < titles.length; i++)
        {
        	if(i == 0)
        	{
                //Add a Title
                TextView division_name = new TextView(this);
                division_name.setId(1);
                division_name.setText(titles[i]);
                division_name.setTextSize(18);
                division_name.setSingleLine(true);
                division_name.setFocusableInTouchMode(true);
                division_name.setEllipsize(TruncateAt.MARQUEE);
                division_name.setTypeface(null, Typeface.BOLD);
        		lp = new RelativeLayout.LayoutParams(
        				RelativeLayout.LayoutParams.WRAP_CONTENT, 
        				RelativeLayout.LayoutParams.WRAP_CONTENT);
        		lp.addRule(RelativeLayout.ALIGN_TOP);
        		lp.rightMargin=10;
        		lp.leftMargin=15;
        		division_name.setLayoutParams(lp);
        		
        		//Add respective label
        		TextView division_contents = new TextView(this);
        		division_contents.setId(2);
        		division_contents.setText(descriptions[i]);
        		division_contents.setGravity(Gravity.RIGHT);
        		division_contents.setGravity(Gravity.LEFT);
        		lp = new RelativeLayout.LayoutParams(
        				RelativeLayout.LayoutParams.WRAP_CONTENT, 
        				RelativeLayout.LayoutParams.WRAP_CONTENT);
        		lp.addRule(RelativeLayout.BELOW, 1);
        		lp.rightMargin=10;
        		lp.leftMargin=15;
        		lp.topMargin=10;
        		lp.bottomMargin=30;
        		division_contents.setLayoutParams(lp);
        		
        		story_layout.addView(division_name);
        		story_layout.addView(division_contents);
        	}
        	else
        	{
                //Add a Title
                TextView division_name = new TextView(this);
                int id = i*2+1;
                division_name.setId(id);
                division_name.setText(titles[i]);
                division_name.setTextSize(18);
                division_name.setSingleLine(true);
                division_name.setFocusableInTouchMode(true);
                division_name.setEllipsize(TruncateAt.MARQUEE);
                division_name.setTypeface(null, Typeface.BOLD);
        		lp = new RelativeLayout.LayoutParams(
        			RelativeLayout.LayoutParams.WRAP_CONTENT, 
        			RelativeLayout.LayoutParams.WRAP_CONTENT);
        		lp.addRule(RelativeLayout.BELOW, id-1);
        		lp.rightMargin=10;
        		lp.leftMargin=15;
        		division_name.setLayoutParams(lp);
        		
        		//Add respective label
        		TextView division_contents = new TextView(this);
        		int id2 = id+1;
        		division_contents.setId(id2);
        		division_contents.setText(descriptions[i]);
        		division_contents.setGravity(Gravity.RIGHT);
        		division_contents.setGravity(Gravity.LEFT);
        		lp = new RelativeLayout.LayoutParams(
        				RelativeLayout.LayoutParams.WRAP_CONTENT, 
        				RelativeLayout.LayoutParams.WRAP_CONTENT);
        		lp.addRule(RelativeLayout.BELOW, id);
        		lp.rightMargin=10;
        		lp.leftMargin=15;
        		lp.topMargin=10;
        		lp.bottomMargin=30;
        		division_contents.setLayoutParams(lp);
        		
        		story_layout.addView(division_name);
        		story_layout.addView(division_contents);
        	}
        }
        
        ScrollView s = (ScrollView) findViewById(R.id.descScroll);
        s.addView(story_layout);
	}
}
