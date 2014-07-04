package com.parse.starter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class NumberScreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.number_screen);

		Button sign_up = (Button) findViewById(R.id.numbers_selected);
		sign_up.setOnClickListener(onClickListener);

	}

	public OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(final View v) {

			EditText number1 = (EditText) findViewById(R.id.editText1);
			EditText number2 = (EditText) findViewById(R.id.editText2);
			EditText number3 = (EditText) findViewById(R.id.editText3);
			EditText number4 = (EditText) findViewById(R.id.editText4);
			EditText number5 = (EditText) findViewById(R.id.editText5);
			EditText number6 = (EditText) findViewById(R.id.editText6);

			if ((number1 != null || number2 != null || number3 != null
					|| number4 != null || number5 != null || number6 != null)) {

				int numTotal = 0;
				int num1 = 0;
				int num2 = 0;
				int num3 = 0;
				int num4 = 0;
				int num5 = 0;
				int num6 = 0;

				try {
					num1 = Integer.parseInt(number1.getText().toString());
					num2 = Integer.parseInt(number2.getText().toString());
					num3 = Integer.parseInt(number3.getText().toString());
					num4 = Integer.parseInt(number4.getText().toString());
					num5 = Integer.parseInt(number5.getText().toString());
					num6 = Integer.parseInt(number6.getText().toString());
					numTotal = num1 + num2 + num3 + num4 + num5 + num6;

				} catch (NumberFormatException e) {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							NumberScreen.this);
					alert.setTitle("Error");
					alert.setMessage("Please insert numbers between 1-99");
					alert.show();
				}

				if ((numTotal > 594 || numTotal < 6)
						|| (num1 == 0 || num2 == 0 || num3 == 0 || num4 == 0
								|| num5 == 0 || num6 == 0)) {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							NumberScreen.this);
					alert.setTitle("Error");
					alert.setMessage("Please insert numbers between 1-99");
					alert.show();
				} else {
					/*
					 * int[] numbers = new int[6]; numbers[0] = num1; numbers[1]
					 * = num2; numbers[2] = num3; numbers[3] = num4; numbers[4]
					 * = num5; numbers[5] = num6;
					 */

					String numbersFinal = num1 + "-" + num2 + "-" + num3 + "-"
							+ num4 + "-" + num5 + "-" + num6;

					ParseUser current = ParseUser.getCurrentUser();
					current.put("lottery_Numbers", numbersFinal);
					current.saveInBackground(null);

					Intent sign = new Intent(NumberScreen.this,
							PlayScreen.class);
					startActivity(sign);
					finish();

				}
			} else {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						NumberScreen.this);
				alert.setTitle("Error");
				alert.setMessage("Please insert numbers into all fields provided");
				alert.show();
			}
		}
	};
}
