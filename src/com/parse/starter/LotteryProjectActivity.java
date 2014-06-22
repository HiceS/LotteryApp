package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.ParseAnalytics;

public class LotteryProjectActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button login = (Button)findViewById(R.id.login);
		Button sign_up = (Button)findViewById(R.id.sign_up);
	
		login.setOnClickListener(onClickListener);
        sign_up.setOnClickListener(onClickListener);
	    
		ParseAnalytics.trackAppOpened(getIntent());
	}
	
/*	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/

/*    public void onClick(View v) {
    	switch(v.getId()){
        case R.id.login:
        	Intent play = new Intent(LotteryProjectActivity.this, PlayScreen.class);
            startActivity(play);
        break;
        case R.id.sign_up:
        	Intent sign = new Intent(LotteryProjectActivity.this, SignPage.class);
            startActivity(sign);
        break;
    	}
    }*/
    
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
            case R.id.login:
            	Intent play = new Intent(LotteryProjectActivity.this, PlayScreen.class);
                startActivity(play);
            break;
            case R.id.sign_up:
            	Intent sign = new Intent(LotteryProjectActivity.this, SignPage.class);
                startActivity(sign);
            break;
            }

      }
   };
	
}
