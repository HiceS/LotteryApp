package com.parse.starter;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignPage extends Activity {

	// String android_id =
	// Secure.getString(getBaseContext().getContentResolver(),
	// Secure.ANDROID_ID);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_page);

		Button sign_up = (Button) findViewById(R.id.sign_finish);
		sign_up.setOnClickListener(onClickListener);
	}

	public OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
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
					String password = passR;
					ParseUser user = new ParseUser();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					// user.put("ticket_Number", ticket_number);
					user.put("created_Date", date);
					// user.put("facebook", facebook_info);
					// user.put("twitter", twitter_info);
					// add picture stuff user.put("picture", prize);
					user.signUpInBackground(new SignUpCallback() {
						@Override
						public void done(com.parse.ParseException e) {
							// TODO Auto-generated method stub
							if (e == null) {
							      // Hooray! Let them use the app now.
							    } else {
							      // Sign up didn't succeed. Look at the ParseException
							      // to figure out what went wrong
							    }
						}
						});

					Intent sign = new Intent(SignPage.this, PlayScreen.class);
	                startActivity(sign);

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
