package com.bubblechat.app;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class LoginActivity extends Activity {
    public static final String applicationID = "rhw07e98lJx7JA7T9tyZWwEbolL52vDsy1fpeOjK";
    public static final String clientKey = "9XPMjdNg0xjEkf7Yz5FTuxCs7GxBHoTgt1C1DZ3i";
    static Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	if (savedInstanceState == null) {
	    getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
	}
	con = this;

	Parse.initialize(con, applicationID, clientKey);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.login, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	if (id == R.id.action_settings)
	    return true;
	return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View rootView = inflater.inflate(R.layout.fragment_login, container, false);

	    final EditText usernameET = (EditText) rootView.findViewById(R.id.username_ET);
	    final EditText passwordET = (EditText) rootView.findViewById(R.id.password_ET);

	    ((Button) rootView.findViewById(R.id.login_BTN)).setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		    ParseUser.logInInBackground(usernameET.getText().toString(), passwordET.getText().toString(), new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
			    if(user != null) {
				Toast.makeText(con, "Logged in successfully.", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(con, MainActivity.class);
				startActivity(i);
			    }
			    else {
				Toast.makeText(con, "Unable to log in. Check internet connection and credentials", Toast.LENGTH_LONG).show();
			    }
			}

		    });
		}
	    });
	    return rootView;
	}
    }
}
