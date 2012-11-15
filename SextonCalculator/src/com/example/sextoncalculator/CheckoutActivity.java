package com.example.sextoncalculator;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("UseValueOf")
public class CheckoutActivity extends Activity implements OnClickListener {

	TextView total;
	double totalPrice;
	Bundle extras;
	double entrePrice;
	double sidePrice;
	double drinkPrice;
	String punchCounter;
	Button homeButton, checkoutButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);

		homeButton = (Button) findViewById(R.id.home_button);
		homeButton.setOnClickListener(this);
		checkoutButton = (Button) findViewById(R.id.checkout_button);
		checkoutButton.setOnClickListener(this);

		
		extras = getIntent().getExtras();
		entrePrice = extras.getDouble("entrePrice");
		sidePrice = extras.getDouble("sidePrice");
		drinkPrice = extras.getDouble("drinkPrice");
		totalPrice = entrePrice + sidePrice + drinkPrice;
		DecimalFormat df = new DecimalFormat("#.##");
		totalPrice = Double.parseDouble(df.format(totalPrice));
		total = (TextView) findViewById(R.id.amountRemaining_textView);
		total.setText("$" + String.valueOf(totalPrice));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_checkout, menu);
		return true;
	}

	public void backActivity(View view) {
		Intent intent = new Intent(this, PunchActivity.class);
		startActivity(intent);
	}

	public void punchActivity(View view) {
		final TextView newTotal = (TextView) findViewById(R.id.amountRemaining_textView);
		totalPrice = totalPrice - entrePrice - sidePrice - drinkPrice;
		if (totalPrice > 0.00) {
			DecimalFormat df = new DecimalFormat("#.##");
			totalPrice = Double.parseDouble(df.format(totalPrice));
			newTotal.setText("$" + String.valueOf(totalPrice));
			if (totalPrice == 0.00) {
				newTotal.setText("$0.00");
			}
			EditText edit = (EditText) findViewById(R.id.cashPay_editText);
			punchCounter = edit.getText().toString();
			int aInt = Integer.parseInt(punchCounter);
			aInt = aInt + 1;
			punchCounter = Integer.toString(aInt);
			edit.setText(punchCounter);
		}
	}

	public void onClick(View v) {
		Intent intent;
		if (v == homeButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == checkoutButton) {
			intent = new Intent(this, CheckoutActivity.class);
			startActivity(intent);
		}
	}

}
