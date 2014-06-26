package com.parse.starter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LotteryProjectActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button login = (Button) findViewById(R.id.login);
		Button sign_up = (Button) findViewById(R.id.sign_up);

		login.setOnClickListener(onClickListener);
		sign_up.setOnClickListener(onClickListener);
		
		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB", "Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");

		ParseAnalytics.trackAppOpened(getIntent());
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */

	/*
	 * public void onClick(View v) { switch(v.getId()){ case R.id.login: Intent
	 * play = new Intent(LotteryProjectActivity.this, PlayScreen.class);
	 * startActivity(play); break; case R.id.sign_up: Intent sign = new
	 * Intent(LotteryProjectActivity.this, SignPage.class); startActivity(sign);
	 * break; } }
	 */

	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

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
											LotteryProjectActivity.this,
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
											LotteryProjectActivity.this);
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
							LotteryProjectActivity.this);
					alert.setTitle("Error");
					alert.setMessage("You must enter a username and a password");
					alert.show();
				}
			case R.id.sign_up:
				Intent sign = new Intent(LotteryProjectActivity.this,
						SignPage.class);
				startActivity(sign);
				break;
			}

		}
	};

}
