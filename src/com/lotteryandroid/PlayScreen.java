package com.lotteryandroid;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.TextView;

import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PlayScreen extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		android.app.ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6699FF")));
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB",
				"Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");
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
		switch (item.getItemId()) {
	      // Something else
	    case R.id.action_settings:
	      Intent intent = new Intent(PlayScreen.this, SettingsScreen.class);
	      startActivity(intent);
	    default:
	      break;
	    }
		return super.onOptionsItemSelected(item);
	}

	public class PlaceholderFragment extends Fragment implements OnClickListener {
		
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.play_screen,
				container, false);
		
		
		ParseUser current = ParseUser.getCurrentUser();
		Boolean isnew = current.isNew();
		String username = current.getUsername();
	//	int ticket_Nummer = (int) current.get("ticket_Number");
		int numberTicket = 0;
		
		if(isnew == true){
			
			String android_id = Secure.getString(getBaseContext().getContentResolver(),
                    Secure.ANDROID_ID);
			
			ParseObject installation = new ParseObject("Installation");
			installation.put("username", current.getUsername());
			installation.put("phoneID", android_id);
			installation.put("app_version", 1.0);
			installation.saveInBackground();
		}
		
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
		TextView user = (TextView) rootView.findViewById(R.id.usernamedisplay);
		TextView ticket = (TextView) rootView.findViewById(R.id.result);
		DigitalClock result = (DigitalClock) rootView.findViewById(R.id.count_down);
		TextView cloud = (TextView) rootView.findViewById(R.id.result);
		TextView ticket_number = (TextView) rootView.findViewById(R.id.ticket_number);
		TextView ticket_ID = (TextView) rootView.findViewById(R.id.ticket_ID);
		
		user.setText(username);
	//	ticket_number.setText(ticket_Nummer);
	//	ticket_Number.setText(current.get("ticket_Number"));
		
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
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
}
