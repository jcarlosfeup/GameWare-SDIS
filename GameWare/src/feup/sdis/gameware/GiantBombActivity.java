package feup.sdis.gameware;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class GiantBombActivity extends Activity{
	private String genericURL = "http://api.giantbomb.com/search/?api_key=aa3a27f7b1d571ec93c3e3d11af0b681eb3eefaa&format=xml";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView mainView = (TextView) findViewById(R.id.mainView);
        mainView.setText("Hello, Android");
        setContentView(R.layout.giantbombtest);
    }

	public void setGenericURL(String genericURL) {
		this.genericURL = genericURL;
	}

	public String getGenericURL() {
		return genericURL;
	}
}
