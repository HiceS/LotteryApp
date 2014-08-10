package com.lotteryandroid;

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

		Button change_pass = (Button) findViewById(R.id.numbers_selected);
		change_pass.setOnClickListener(onClickListener);

	}
	
	public OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(final View v) {
			AlertDialog.Builder alert = new AlertDialog.Builder(
					SettingsScreen.this);
			alert.setTitle("works");
			alert.setMessage("fo sure");
			alert.show();
		}
	};
}
