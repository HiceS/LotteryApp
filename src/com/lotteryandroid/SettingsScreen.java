package com.lotteryandroid;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SettingsScreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);
		
		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB",
				"Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");
		ParseAnalytics.trackAppOpened(getIntent());
		
		if (ParseUser.getCurrentUser()  == null){
			
			Intent intent = new Intent(SettingsScreen.this, StartActivity.class);
		      startActivity(intent); 
		      
		}
		
		Button pass_change = (Button) findViewById(R.id.pass_change);
		Button logout = (Button) findViewById(R.id.logout);
		
		pass_change.setOnClickListener(onClickListener);
		logout.setOnClickListener(onClickListener);
		
	}
	
	
	public OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(final View v) {
			switch (v.getId()) {
			case R.id.pass_change:
			
			ParseUser current = ParseUser.getCurrentUser();
				
				try {
					
					ParseUser.requestPasswordReset(current.getEmail());
					AlertDialog.Builder alert = new AlertDialog.Builder(
							SettingsScreen.this);
					alert.setTitle("Success");
					alert.setMessage("Please check your email for instructions to reset your password.");
					alert.show();
					
				} catch (ParseException e) {
					
					// TODO Auto-generated catch block
					AlertDialog.Builder alert = new AlertDialog.Builder(
							SettingsScreen.this);
					alert.setTitle("Error");
					alert.setMessage("Could not change password at this time.");
					alert.show();
					e.printStackTrace();
					
				}
			
				break;
				
			case R.id.logout:
				
				ParseUser.logOut();
				
				Intent intent = new Intent(SettingsScreen.this, StartActivity.class);
				  startActivity(intent);
				  
				break;
			}
		}
	};
}
