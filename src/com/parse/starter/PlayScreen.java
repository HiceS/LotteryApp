package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseUser;

public class PlayScreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_screen);
		
		ParseUser current = ParseUser.getCurrentUser();
		Boolean isnew = current.isNew();
		String username = current.getUsername();
		
	}
}
