package com.example.sextoncalculator;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class BrowseActivity extends ListActivity {
	//protected Spinner spinner;
	protected FoodData foodData;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
        foodData = new FoodData(this);
		//spinner = (Spinner) findViewById(R.id.spinner);
		if(foodData.count() == 0){
			foodData.load();
		}
		Cursor cursor = foodData.all(this);
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter foodCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_browse_row, cursor, new String[] { foodData.NAME, foodData.PRICE }, new int[] { R.id.foodItem_textView, R.id.price_textView });
		setListAdapter(foodCursorAdapter);
		//foodCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		setContentView(R.layout.activity_browse);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_browse, menu);
		return true;
	}

	public void homeActivity(View view) {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	public void checkoutActivity(View view) {
		Intent intent = new Intent(this, CheckoutActivity.class);
		startActivity(intent);
	}
}
