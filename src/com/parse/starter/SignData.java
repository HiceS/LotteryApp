package com.parse.starter;

import android.app.Activity;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignData extends Activity {
	
	public SignData(String username , String password , String email, String date) {

		// Add your initialization code here
		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB", "Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");
		ParseAnalytics.trackAppOpened(getIntent());
		
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
			public void done(com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
				      // Hooray! Let them use the app now.
					Intent sign = new Intent(SignData.this, PlayScreen.class);
	                startActivity(sign);
				    } else {
				      // Sign up didn't succeed. Look at the ParseException
				      // to figure out what went wrong
//				    Intent sign = new Intent(SignData.this, PlayScreen.class);
//		            startActivity(sign);
				    }
			}
			});
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
		
		
	}
}
