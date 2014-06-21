package com.parse.starter;

import java.util.Calendar;

import android.app.Application;
import android.provider.Settings.Secure;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB", "Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");
		
		//Data Forms section 
		
		//Install table information
		ParseObject install_data = new ParseObject("Install Data");
		ParseObject ticket_data = new ParseObject("Ticket Data");
		ParseObject user_table = new ParseObject("User Data");
		ParseObject system_table = new ParseObject("System Data");
		ParseObject drawing_table = new ParseObject("Drawing Data");
		Calendar install_date = Calendar.getInstance();
		
		//The getters for the data
		int date = install_date.DATE;
		String android_id = Secure.getString(getBaseContext().getContentResolver(),
                Secure.ANDROID_ID);
		double app_version = 1.0;
		String username_getter = "tester";
		int ticket_number = 1;
		int matchedNumbers = 0;
		double prize = 0.0;
		String account_email = "email";
		String facebook_info = "";
		String twitter_info = "";
		int jackpot_value = 12000000;
		int nextDrawingTime = 0;
		int nextSweepstakesTime = 0;
		int jackpotEntries = 0;
		int sweepstake_entries = 0;
		int winningNumbers = 0; //CHANGE TO ARRAY 
		int distribution_tickets = 0;
		int graded_tickets = 0;
		
		//Putting the data into the table 
		install_data.put("Date", date);
		install_data.put("Phone information", android_id);
		install_data.put("App version", app_version);
		install_data.put("Username", username_getter);
		install_data.saveInBackground();
		
		//Putting the data into the table 
		ticket_data.put("Ticket Number", ticket_number);
		ticket_data.put("Username", username_getter);
		ticket_data.put("Date", date);
		ticket_data.put("Matched Numbers", matchedNumbers);
		ticket_data.put("Prize", prize);
		ticket_data.saveInBackground();		
		
		//Putting the data into the table 
		user_table.put("Current Ticket Number", ticket_number);
		user_table.put("Username", username_getter);
		user_table.put("Created Date", date);
		user_table.put("Email", account_email);
		user_table.put("Facebook", facebook_info);
		user_table.put("Twitter", twitter_info);
		user_table.put("Picture", prize);
		user_table.saveInBackground();
		
		//Putting the data into the table 
		system_table.put("Current Jackpot Value", ticket_number);
		system_table.put("Next Drawing Time", nextDrawingTime);
		system_table.put("Next Sweepstakes Time", nextSweepstakesTime);
		system_table.put("Jackpot Entries", jackpotEntries);
		system_table.put("Sweepstakes Entries", sweepstake_entries);
		system_table.saveInBackground();
		
		//Putting the data into the table 
		drawing_table.put("Date of Drawing", date);
		drawing_table.put("Time of Drawing", date);
		drawing_table.put("Winning Numbers Generated", winningNumbers);
		drawing_table.put("Distribution of ticket results", distribution_tickets);
		drawing_table.put("Graded Tickets", graded_tickets);
		drawing_table.saveInBackground();
		
		//Parse user default area
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
