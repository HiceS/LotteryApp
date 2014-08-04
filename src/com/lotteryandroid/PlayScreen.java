package com.lotteryandroid;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.DigitalClock;
import android.widget.TextView;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class PlayScreen extends Activity {
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_screen);
		
		ParseUser current = ParseUser.getCurrentUser();
		Boolean isnew = current.isNew();
		String username = current.getUsername();
		int numberTicket = 0;
		
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("number_Ticket", numberTicket);
/*		try{
			int ticketnumber = getInt("number_Ticket");
		}
		catch(ParseException e){
			AlertDialog.Builder alert = new AlertDialog.Builder(
	    			PlayScreen.this);
			alert.setTitle("works");
			alert.setMessage("Cannot find ticket number!");
			alert.show();
		}
		*/
		TextView user = (TextView) findViewById(R.id.result);
		TextView ticket = (TextView) findViewById(R.id.result);
		DigitalClock result = (DigitalClock) findViewById(R.id.count_down);
		TextView cloud = (TextView) findViewById(R.id.result);
		
		
		
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
