package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class PunchActivity extends Activity {
	protected Spinner entreSpinner, sideSpinner, drinkSpinner;
	protected FoodData foodData;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_punch);

        foodData = new FoodData(this);
		entreSpinner = (Spinner) findViewById(R.id.spinner1);
		//sideSpinner = (Spinner) findViewById(R.id.spinner2);
		//drinkSpinner = (Spinner) findViewById(R.id.spinner3);
		if(foodData.count() == 0){
			foodData.load();
		}
		Cursor cursor = foodData.all(this);
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter foodCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_punch_row, cursor, new String[] { foodData.NAME, foodData.PRICE }, new int[] { R.id.spinner_textView1, R.id.spinner_textView2 });
		entreSpinner.setAdapter(foodCursorAdapter);
		
		//foodCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_punch, menu);
		return true;
	}

	public void checkoutActivity(View view) {
		Intent intent = new Intent(this, CheckoutActivity.class);
		/*
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
		*/
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
