package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

public class PunchActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_punch);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_punch, menu);
		return true;
	}

	public void checkoutActivity(View view) {
		Intent intent = new Intent(this, CheckoutActivity.class);
		RadioButton cheeseburgerButton = (RadioButton) findViewById(R.id.cheeseburger);
		RadioButton pizzaButton = (RadioButton) findViewById(R.id.pizza);
		RadioButton friesButton = (RadioButton) findViewById(R.id.fries);
		RadioButton saladButton = (RadioButton) findViewById(R.id.salad);
		RadioButton popButton = (RadioButton) findViewById(R.id.pop);
		RadioButton coffeeButton = (RadioButton) findViewById(R.id.coffee);
		if (cheeseburgerButton.isChecked()) {
			intent.putExtra("entrePrice", 3.30);
		}
		if (pizzaButton.isChecked()) {
			intent.putExtra("entrePrice", 2.15);
		}
		if (friesButton.isChecked()) {
			intent.putExtra("sidePrice", 1.49);
		}
		if (saladButton.isChecked()) {
			intent.putExtra("sidePrice", 3.00);
		}
		if (popButton.isChecked()) {
			intent.putExtra("drinkPrice", 1.49);
		}
		if (coffeeButton.isChecked()) {
			intent.putExtra("drinkPrice", 1.49);
		}
		startActivity(intent);
	}

	public void homeActivity(View view) {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	public void punchActivity(View view) {
		Intent intent = new Intent(this, CheckoutActivity.class);
		startActivity(intent);
	}

}
