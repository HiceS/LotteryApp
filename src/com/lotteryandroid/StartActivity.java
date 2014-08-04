package com.lotteryandroid;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class StartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	public class PlaceholderFragment extends Fragment implements OnClickListener {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start,
					container, false);
			

			Button login = (Button) rootView.findViewById(R.id.login);
			Button sign_up = (Button) rootView.findViewById(R.id.sign_up);
			
			login.setOnClickListener(this);
			sign_up.setOnClickListener(this);
			
			return rootView;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText user = (EditText) findViewById(R.id.username);
			EditText pass = (EditText) findViewById(R.id.password);
			
			String username = user.getText().toString();
			String password = pass.getText().toString();

			switch (v.getId()) {
			case R.id.login:
				if (username != "" && password != ""){
				ParseUser.logInInBackground(username, password,
						new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									// Hooray! The user is logged in.
									Intent play = new Intent(
											StartActivity.this,
											PlayScreen.class);
									startActivity(play);
								} else {
									String error = null;
									switch (e.getCode()) {
									case ParseException.USERNAME_MISSING:
										error = ("Sorry, you must supply a username");
										break;
									case ParseException.PASSWORD_MISSING:
										error = ("Sorry, you must supply a password");
										break;
									default:
										error = (e.getLocalizedMessage());
									}

									AlertDialog.Builder alert = new AlertDialog.Builder(
											StartActivity.this);
									alert.setTitle("Error");
									alert.setMessage(error);
									alert.show();
									// Signup failed. Look at the ParseException
									// to see what happened.
								}
							}
						});
				break;
				}
				else{
					AlertDialog.Builder alert = new AlertDialog.Builder(
							StartActivity.this);
					alert.setTitle("Error");
					alert.setMessage("You must enter a username and a password");
					alert.show();
				}
			case R.id.sign_up:
				Intent sign = new Intent(StartActivity.this,
						SignPage.class);
				startActivity(sign);
				break;
			}

		}
		}
		
	  
	

}
