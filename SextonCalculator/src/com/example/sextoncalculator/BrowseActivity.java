package com.example.sextoncalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * BrowseActivity creates interface to browse all food items available. Total
 * price is calculated when adding and removing food items to/from shopping
 * cart.
 * 
 * @author Johnathan Ly, Adam Bachmeier, Tsuehue Xiong, Justin Springer
 * 
 */
public class BrowseActivity extends ListActivity implements OnClickListener {
	protected FoodData foodData;
	protected Button homeButton, resetButton, checkoutButton;
	String totalString;
	ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
	protected SimpleCursorAdapter foodCursorAdapter;

	/**
	 * Called when interface is created. Sets up list view to display food item
	 * information. Sets up click listener for buttons.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// loads database if empty
		foodData = new FoodData(this);
		if (foodData.count() == 0) {
			foodData.load();
		}
		
		// reset quantity to 0 and display food data from database in list view
		foodData.resetQuantity();
		Cursor cursor = foodData.all(this);
		foodCursorAdapter = new SimpleCursorAdapter(this,
				R.layout.activity_browse_row, cursor, new String[] {
						foodData.ID, foodData.NAME, foodData.PRICE,
						foodData.QUANTITY }, new int[] { R.id.itemId_textView,
						R.id.itemName_textView, R.id.itemPrice_textView,
						R.id.itemQuantity_textView });
		setListAdapter(foodCursorAdapter);
		setContentView(R.layout.activity_browse);
		
		// find and set on click listener to all buttons
		homeButton = (Button) findViewById(R.id.home_button);
		homeButton.setOnClickListener(this);
		resetButton = (Button) findViewById(R.id.reset_button);
		resetButton.setOnClickListener(this);
		checkoutButton = (Button) findViewById(R.id.checkout_button);
		checkoutButton.setOnClickListener(this);
	}

	/**
	 * Called when interface is created. Sets up option menu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_browse, menu);
		return true;
	}

	/**
	 * Sets actions for clicked buttons.
	 */
	public void onClick(View v) {
		Intent intent;
		if (v == homeButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == resetButton) {
			restartActivity();
		} else if (v == checkoutButton) {
			if (foodList.isEmpty()) {
			} else {
				intent = new Intent(this, CheckoutActivity.class);
				Collections.sort(foodList);
				intent.putParcelableArrayListExtra("foodList", foodList);
				intent.putExtra("totalString", getTotalString());
				intent.putExtra("flag", 2);
				startActivity(intent);
			}
		}
	}

	/**
	 * Gets the total price.
	 * 
	 * @return totalString as a double value
	 */
	public double getTotalString() {
		return Double.parseDouble(this.totalString);
	}

	/**
	 * Sets the total price.
	 * 
	 * @param totalString
	 *            the variable to set it to
	 */
	public void setTotalString(String totalString) {
		this.totalString = totalString;
	}

	/**
	 * Resets display to default interface prior to selections.
	 */
	public void restartActivity() {
		foodData.resetQuantity();
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

	/**
	 * Increases selected food item quantity by one. Adds selected food item to
	 * shopping cart or updates quantity if food item already exists.
	 * 
	 * @param v
	 *            the view being used
	 */
	public void increaseQuantity(View v) {
		// get layout
		RelativeLayout layout = (RelativeLayout) v.getParent();
		
		// get text views from layout
		TextView itemId = (TextView) layout.findViewById(R.id.itemId_textView);
		TextView itemName = (TextView) layout
				.findViewById(R.id.itemName_textView);
		TextView itemPrice = (TextView) layout
				.findViewById(R.id.itemPrice_textView);
		TextView itemQuantity = (TextView) layout
				.findViewById(R.id.itemQuantity_textView);
		
		// get value from text views
		int id = Integer.parseInt(itemId.getText().toString());
		String name = itemName.getText().toString();
		double price = Double.parseDouble(itemPrice.getText().toString());
		int quantity = Integer.parseInt(itemQuantity.getText().toString());
		
		// update item quantity in database then refresh list view
		foodData.updateQuantity(id, quantity + 1);
		foodCursorAdapter.getCursor().requery();
		
		// update item quantity in food list
		FoodItem foodItem = new FoodItem(name, price, 1);
		if (foodList.indexOf(foodItem) == -1) {
			foodList.add(foodItem);
		} else {
			FoodItem oldFoodItem = foodList.get(foodList.indexOf(foodItem));
			int oldQuantity = oldFoodItem.getQuantity();
			oldFoodItem.setQuantity(oldQuantity + 1);
		}
		calculateTotal();
	}

	/**
	 * Decreases selected food item quantity by one. Updates quantity of food
	 * item in shopping cart or removes food item when quantity reaches zero
	 * after change.
	 * 
	 * @param v
	 *            the view being used
	 */
	public void decreaseQuantity(View v) {
		// get layout
		RelativeLayout layout = (RelativeLayout) v.getParent();
		
		// get text views from layout
		TextView itemId = (TextView) layout.findViewById(R.id.itemId_textView);
		TextView itemName = (TextView) layout
				.findViewById(R.id.itemName_textView);
		TextView itemPrice = (TextView) layout
				.findViewById(R.id.itemPrice_textView);
		TextView itemQuantity = (TextView) layout
				.findViewById(R.id.itemQuantity_textView);
		
		// get values from text views
		int id = Integer.parseInt(itemId.getText().toString());
		String name = itemName.getText().toString();
		double price = Double.parseDouble(itemPrice.getText().toString());
		int quantity = Integer.parseInt(itemQuantity.getText().toString());
		
		// update item quantity in database then refresh list view and update
		// item quantity in food list
		if (quantity > 1) {
			foodData.updateQuantity(id, quantity - 1);
			foodCursorAdapter.getCursor().requery();
			FoodItem foodItem = new FoodItem(name, price, quantity);
			FoodItem oldFoodItem = foodList.get(foodList.indexOf(foodItem));
			int oldQuantity = oldFoodItem.getQuantity();
			oldFoodItem.setQuantity(oldQuantity - 1);
		} else if (quantity == 1) {
			foodData.updateQuantity(id, quantity - 1);
			foodCursorAdapter.getCursor().requery();
			FoodItem foodItem = new FoodItem(name, price, quantity);
			FoodItem oldFoodItem = foodList.get(foodList.indexOf(foodItem));
			foodList.remove(oldFoodItem);
		}
		
		// update calculated total price
		calculateTotal();
	}

	/**
	 * Calculates and displays total price. Uses food item array in shopping
	 * cart.
	 */
	private void calculateTotal() {
		// loop through food list and calculate total price using item price and
		// quantity
		TextView totalPrice = (TextView) findViewById(R.id.totalPrice_textView);
		double total = 0.00;
		for (int i = 0; i < foodList.size(); i++) {
			total += foodList.get(i).getPrice() * foodList.get(i).getQuantity();
		}
		
		// set total price text to calculated total price
		if (total == 0.00) {
			totalPrice.setText("Total Price: $0.00");
		} else if (total > 0.00) {
			DecimalFormat df = new DecimalFormat("0.00");
			totalString = df.format(total);
			totalPrice.setText("Total Price: $" + totalString);
		}
		setTotalString(totalString);
	}
}
