package com.parse.starter;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.parse.CountCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignPage extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_page);

		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB",
				"Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");
		ParseAnalytics.trackAppOpened(getIntent());

		Button sign_up = (Button) findViewById(R.id.sign_finish);
		sign_up.setOnClickListener(onClickListener);
	}

	public OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(final View v) {
			switch (v.getId()) {
			case R.id.sign_finish:
				Calendar c = Calendar.getInstance();
				int day = c.get(Calendar.DATE);
				int month = c.get(Calendar.MONTH);
				int year = c.get(Calendar.YEAR);
				String date = month + "-" + day + "-" + year;

				EditText user1 = (EditText) findViewById(R.id.user);
				EditText pass = (EditText) findViewById(R.id.pass);
				EditText pass2 = (EditText) findViewById(R.id.pass2);
				EditText email1 = (EditText) findViewById(R.id.email);

				String username = user1.getText().toString();
				String passL = pass.getText().toString();
				String passR = pass2.getText().toString();
				String email = email1.getText().toString();
				
				

				if (passR.equals(passL)) {
					String password = passL;

					ParseACL defaultACL = new ParseACL();
					defaultACL.setPublicReadAccess(true);
					ParseACL.setDefaultACL(defaultACL, true);
					
					final ParseUser user = new ParseUser();
					ParseQuery<ParseUser> query = ParseQuery.getQuery("userNumber");
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					query.countInBackground(new CountCallback() {
						  public void done(int count, ParseException e) {
						    if (e == null) {
						      // The count request succeeded. Log the count
						    	Log.d("Ticket", "Ticket Number: " + count);
						    	user.put("ticket_Number", count);
						    } else {
						      // The request failed
						    	AlertDialog.Builder alert = new AlertDialog.Builder(
										SignPage.this);
								alert.setTitle("Error");
								alert.setMessage("Error finidng ticket number");
								alert.show();
						    }
						  }
						});
					user.put("created_Date", date);
					// user.put("facebook", facebook_info);
					// user.put("twitter", twitter_info);
					// user.put("picture", prize);					ADD PICTURE STUFF
					user.signUpInBackground(new SignUpCallback() {
						public void done(com.parse.ParseException e) {
							if (e == null) {
								Intent sign = new Intent(SignPage.this,
										PlayScreen.class);
								startActivity(sign);
								finish();
							} else {
								String error = null;
								switch(e.getCode()){
								case ParseException.USERNAME_TAKEN:
								  error = ("Sorry, this username has already been taken.");
								  break;
								case ParseException.USERNAME_MISSING:
								  error = ("Sorry, you must supply a username to register.");
								  break;
								case ParseException.PASSWORD_MISSING:
								  error = ("Sorry, you must supply a password to register.");
								  break;
								default:
								   error  = (e.getLocalizedMessage());
								}
								
								AlertDialog.Builder alert = new AlertDialog.Builder(
										SignPage.this);
								alert.setTitle("Error");
								alert.setMessage(error);
								alert.show();
								
								v.setEnabled(true);
							}
						}
					});
					

				} else {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							SignPage.this);
					alert.setTitle("Error");
					alert.setMessage("Your Passwords do not match");
					alert.show();
				}

				break;
			}

		}
	};
}
