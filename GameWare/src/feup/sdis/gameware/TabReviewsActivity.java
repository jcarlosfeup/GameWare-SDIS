package feup.sdis.gameware;

import gameware.info.Game;
import gameware.info.Review;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TabReviewsActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        final Game chosenGame = (Game) myIntent.getSerializableExtra("CHOSEN_GAME");
        Review review = chosenGame.getReview();
        RelativeLayout tab_reviews = new RelativeLayout(this);

        RelativeLayout.LayoutParams lp;	   
        
        TextView textview = new TextView(this);
        textview.setText("This is the Reviews tab");
        setContentView(textview);
        
        //ADD Rating
        RatingBar rate = new RatingBar(this);
        rate.setId(1);
        lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		/*lp.addRule(RelativeLayout.BELOW, 2);
		lp.addRule(RelativeLayout.RIGHT_OF, 1);
		lp.addRule(RelativeLayout.LEFT_OF);*/
		lp.rightMargin=5;
		lp.leftMargin= 5;
		lp.topMargin = 5;
		setContentView(rate);
        
        
        if(review != null)
        {
	        //add rating
	        RatingBar rating = new RatingBar(this);
	        rating.setId(1);
	        rating.setFocusable(false);
	        rating.setRating(review.getScore());
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			lp.addRule(RelativeLayout.CENTER_IN_PARENT);
			lp.topMargin=10;
			rating.setLayoutParams(lp);
			
			//Date
			TextView date_label = new TextView(this);
			date_label.setId(2);
			date_label.setText(review.getDate() + ", ");
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, 1);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lp.leftMargin=10;
			lp.topMargin = 15;
			date_label.setLayoutParams(lp);
	
			//Reviewer name
			TextView reviewer_label = new TextView(this);
			reviewer_label.setId(3);
			reviewer_label.setText(review.getReview());
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.RIGHT_OF, 2);
			lp.addRule(RelativeLayout.BELOW, 1);
			lp.topMargin = 15;
			reviewer_label.setLayoutParams(lp);
			
			//Plot label
			TextView plot_label = new TextView(this);
			plot_label.setId(4);
			plot_label.setText("Plot:");
			plot_label.setTypeface(null, Typeface.BOLD);
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lp.addRule(RelativeLayout.BELOW, 3);
			lp.topMargin = 15;
			lp.leftMargin=10;
			plot_label.setLayoutParams(lp);
			
			//Plot content
			TextView plot_content = new TextView(this);
			plot_content.setId(5);
			plot_content.setText(review.getDeck());
			plot_content.setSingleLine(false);
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, 4);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lp.rightMargin=10;
			lp.leftMargin=10;
			plot_content.setLayoutParams(lp);
			
			//Review label
			TextView review_label = new TextView(this);
			review_label.setId(6);
			review_label.setText("Review:");
			review_label.setTypeface(null, Typeface.BOLD);
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lp.addRule(RelativeLayout.BELOW, 5);
			lp.topMargin = 15;
			lp.leftMargin=10;
			review_label.setLayoutParams(lp);
			
			//Review contents
			String reviewString = review.getDescription();
			TextView review_content = new TextView(this);
			review_content.setId(7);
			review_content.setText(reviewString);
			review_content.setSingleLine(false);
			lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, 
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, 6);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lp.rightMargin=10;
			lp.leftMargin=10;
			review_content.setLayoutParams(lp);	
			
			System.out.println(review.getDescription());
			
			//Setting views on screen
			tab_reviews.addView(rating);
			tab_reviews.addView(date_label);
			tab_reviews.addView(reviewer_label);
			tab_reviews.addView(plot_label);
			tab_reviews.addView(plot_content);
			tab_reviews.addView(review_label);
			tab_reviews.addView(review_content);
			
			ScrollView s = new ScrollView(this);
			s.addView(tab_reviews);
			setContentView(s);
			
			rating.setOnTouchListener(new OnTouchListener() {
		        public boolean onTouch(View v, MotionEvent event) {
		            return true;
		        }
		    });
        }else{
    		TextView no_review_label = new TextView(this);
    		no_review_label.setId(1);
    		no_review_label.setText("No review available :(");
    		no_review_label.setTypeface(null, 1); //bold
    		no_review_label.setSingleLine(false);
    		lp = new RelativeLayout.LayoutParams(
    				RelativeLayout.LayoutParams.WRAP_CONTENT, 
    				RelativeLayout.LayoutParams.WRAP_CONTENT);
    		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
    		lp.addRule(RelativeLayout.CENTER_VERTICAL);
    		
    		no_review_label.setLayoutParams(lp);
    		
    		tab_reviews.addView(no_review_label);
    		setContentView(tab_reviews);
        }
    }
}
