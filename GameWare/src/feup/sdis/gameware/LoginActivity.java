package feup.sdis.gameware;

import gameware.server.user.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        
        final GameWareApp gameWareState = ((GameWareApp)getApplicationContext());
        final User user = gameWareState.getUser();
        
		final Button login_button = (Button) findViewById(R.id.login_button);
		final EditText username_edit = (EditText) findViewById(R.id.login_edit);
		final EditText password_edit = (EditText) findViewById(R.id.password_edit);
		final TextView error_message = (TextView)findViewById(R.id.error_message);
		
		 login_button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String username = username_edit.getText().toString();
					String password = password_edit.getText().toString();
					if(username == "" || password == ""){
						error_message.setText(R.string.check_form);
					}
					user.setUsername(username);
					user.setPassword(password);
					int result = user.login();
					switch(result)
					{
					case 1:
						user.setLogged(true);
						gameWareState.setUser(user);
						Intent myIntent = new Intent(LoginActivity.this, GameWareActivity.class);
						startActivity(myIntent);
						break;
					case -2: //user inexistent
						error_message.setText(R.string.error_user_inexistent);
						break;
					}
				}
		 });		
	}
}
