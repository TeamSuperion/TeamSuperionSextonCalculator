package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalorieActivity extends Activity implements OnClickListener {
	
	HeaderActivity header;
	protected Button homeButton, resetButton, checkoutButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calorie);
		
		homeButton = (Button) findViewById(R.id.home_button);
		homeButton.setOnClickListener(this);
		resetButton = (Button) findViewById(R.id.reset_button);
		resetButton.setOnClickListener(this);
		checkoutButton = (Button) findViewById(R.id.checkout_button);
		checkoutButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_calorie, menu);
		return true;
	}
	
	public void onClick(View v) {
		Intent intent;
		if (v == homeButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == resetButton) {
			//intent = new Intent(this, y.class);
			//startActivity(intent);
		} else if (v == checkoutButton) {
			intent = new Intent(this, CheckoutActivity.class);
			startActivity(intent);
		}
	}

}
