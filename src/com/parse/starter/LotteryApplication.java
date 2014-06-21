package com.parse.starter;

import java.util.Calendar;

import android.app.Application;
import android.provider.Settings.Secure;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class LotteryApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "6YAoAZ3JUXdtwI1RVMPdkH5J6AbjAweyTnUtzSPB", "Tb4jIuH4GpCuagvHtTQgxlknMwCw9Tdk1c1jrpvM");
		
		//Data Forms section 
		
		//The getters for the data
		Calendar c = Calendar.getInstance(); 
        	int day = c.get(Calendar.DATE);
        	int month = c.get(Calendar.MONTH);
        	int year = c.get(Calendar.YEAR);
        	String date = month + "-" + day + "-" + year;
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
		
		//Putting the data into the installation table 
		ParseObject install_data = new ParseObject("Installation");
		install_data.put("date", date);
		install_data.put("phoneID", android_id);
		install_data.put("app_version", app_version);
		install_data.put("username", username_getter);
		install_data.saveInBackground();
		
		//Putting the data into the ticket info table 
		ParseObject ticket_data = new ParseObject("Ticket");
		ticket_data.put("ticket_Number", ticket_number);
		ticket_data.put("username", username_getter);
		ticket_data.put("date", date);
		ticket_data.put("matched_Numbers", matchedNumbers);
		ticket_data.put("prize", prize);
		ticket_data.saveInBackground();		
		
		//Putting the data into the user info table 
		ParseObject user_table = new ParseObject("User");
		user_table.put("current_Ticket_Number", ticket_number);
		user_table.put("username", username_getter);
		user_table.put("created_Date", date);
		user_table.put("email", account_email);
		user_table.put("facebook", facebook_info);
		user_table.put("twitter", twitter_info);
		user_table.put("picture", prize);
		user_table.saveInBackground();
		
		//Putting the data into the system table 
		ParseObject system_table = new ParseObject("System");
		system_table.put("current_Jackpot_Value", jackpot_value);
		system_table.put("next_Drawing_Time", nextDrawingTime);
		system_table.put("next_Sweepstakes_Time", nextSweepstakesTime);
		system_table.put("jackpot_Entries", jackpotEntries);
		system_table.put("sweepstakes_Entries", sweepstake_entries);
		system_table.saveInBackground();
		
		//Putting the data into the drawing table 
		ParseObject drawing_table = new ParseObject("Drawing");
		drawing_table.put("date_of_Drawing", date);
		drawing_table.put("time_of_Drawing", date);
		drawing_table.put("winning_Numbers_Generated", winningNumbers);
		drawing_table.put("distribution_of_ticket_results", distribution_tickets);
		drawing_table.put("graded_Tickets", graded_tickets);
		drawing_table.saveInBackground();
		
		//Parse user default area
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
