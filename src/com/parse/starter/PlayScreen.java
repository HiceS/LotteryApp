package com.parse.starter;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;



import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

public class PlayScreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_screen);
		
		ParseUser current = ParseUser.getCurrentUser();
		Boolean isnew = current.isNew();
		String username = current.getUsername();
		
		TextView result = (TextView) findViewById(R.id.result);
		
		ParseCloud.callFunctionInBackground("hello", new HashMap<String, Object>(), new FunctionCallback<String>() {
			  public void done(String result, ParseException e) {
			    if (e == null) {
			      // result is "Hello world!"
			    	AlertDialog.Builder alert = new AlertDialog.Builder(
			    			PlayScreen.this);
					alert.setTitle("works");
					alert.setMessage(result);
					alert.show();
			    	
			    }
			  }
			});
		
	}
}
