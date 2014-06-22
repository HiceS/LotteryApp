package com.parse.starter;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignPage extends Activity {
	public EditText user = (EditText) findViewById(R.id.user);
	public EditText pass1 = (EditText) findViewById(R.id.pass);
	public EditText pass2 = (EditText) findViewById(R.id.pass2);
	public EditText email1 = (EditText) findViewById(R.id.email);
	
	Calendar c = Calendar.getInstance(); 
    int day = c.get(Calendar.DATE);
    int month = c.get(Calendar.MONTH);
    int year = c.get(Calendar.YEAR);
    String date = month + "-" + day + "-" + year;
    
	String android_id = Secure.getString(getBaseContext().getContentResolver(),
            Secure.ANDROID_ID);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_page);
		
		Button sign_up = (Button)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(onClickListener);
	}
	
	 private OnClickListener onClickListener = new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            switch(v.getId()){
	            case R.id.login:
	            	Intent play1 = new Intent(SignPage.this, PlayScreen.class);
	                startActivity(play1);
	            break;
	            case R.id.sign_up:
	            	String username = user.toString();
	            	String pass = pass1.toString();
	            	String pass1 = pass2.toString();
	            	String email = email1.toString();
	            	
	            	if(pass == pass1){
	            		String password = pass1;
		            	ParseUser user = new ParseUser();
		        		user.setUsername(username);
		        		user.setPassword(password);
		        		user.setEmail(email);
//		        		user.put("ticket_Number", ticket_number);
		        		user.put("created_Date", date);
//		        		user.put("facebook", facebook_info);
//		        		user.put("twitter", twitter_info);
//	add picture stuff	        		user.put("picture", prize);
		        		user.signUpInBackground(new SignUpCallback() {
		        			  public void done(ParseException e) {
		        			    if (e == null) {
		        			      // Hooray! Let them use the app now.
		        	            	Intent play = new Intent(SignPage.this, PlayScreen.class);
		        	                startActivity(play);
		        			    } else {
		        			      // Sign up didn't succeed. Look at the ParseException
		        			      // to figure out what went wrong
		        			    }
		        			  }

							@Override
							public void done(com.parse.ParseException e) {
								// TODO Auto-generated method stub
								
							}
		        			});
	            	}
	                
	            break;
	            }

	      }
	   };
}
