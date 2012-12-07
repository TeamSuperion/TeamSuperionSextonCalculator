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
 */
public class BrowseActivity extends ListActivity implements OnClickListener {
	// protected Spinner spinner;
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

		foodData = new FoodData(this);
		// spinner = (Spinner) findViewById(R.id.spinner);
		if (foodData.count() == 0) {
			foodData.load();
		}
		foodData.resetQuantity();
		Cursor cursor = foodData.all(this);
		// @SuppressWarnings("deprecation")
		foodCursorAdapter = new SimpleCursorAdapter(this,
				R.layout.activity_browse_row, cursor, new String[] {
						foodData.ID, foodData.NAME, foodData.PRICE,
						foodData.QUANTITY }, new int[] { R.id.itemId_textView,
						R.id.itemName_textView, R.id.itemPrice_textView,
						R.id.itemQuantity_textView });
		setListAdapter(foodCursorAdapter);
		// foodCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		setContentView(R.layout.activity_browse);

		homeButton = (Button) findViewById(R.id.home_button);
		homeButton.setOnClickListener(this);
		resetButton = (Button) findViewById(R.id.reset_button);
		resetButton.setOnClickListener(this);
		checkoutButton = (Button) findViewById(R.id.checkout_button);
		checkoutButton.setOnClickListener(this);
	}

	/** Called when interface is created. Sets up option menu. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_browse, menu);
		return true;
	}

	/** Sets actions for clicked buttons. */
	public void onClick(View v) {
		Intent intent;
		if (v == homeButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == resetButton) {
			restartActivity();
		} else if (v == checkoutButton) {
			if (foodList.isEmpty()) {
				// return message telling user that there is no item in the
				// foodList so there is nothing to checkout
			} else {
				intent = new Intent(this, CheckoutActivity.class);
				Collections.sort(foodList);
				intent.putParcelableArrayListExtra("foodList", foodList);
				intent.putExtra("totalString", getTotalString());
				intent.putExtra("flag", 2);
				// String[] foodListIntance = new String[foodList.size()];
				// for (int i=0; i<=foodList.size(); i++) {
				// foodListIntance[i]=foodList.get(i).toString();
				// }
				// intent.putExtra("foodListIntance", foodListIntance);
				startActivity(intent);
			}
		}
	}

	/** Gets the total price. */
	public double getTotalString() {
		return Double.parseDouble(this.totalString);
	}

	/** Sets the total price. */
	public void setTotalString(String totalString) {
		this.totalString = totalString;
	}

	/** Resets display to default interface prior to selections. */
	public void restartActivity() {
		foodData.resetQuantity();
		// Intent intent = new Intent();
		// act.finish();
		// intent.setClass(act, act.getClass());
		// act.startActivity(intent);
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
	 */
	public void increaseQuantity(View v) {
		RelativeLayout layout = (RelativeLayout) v.getParent();
		TextView itemId = (TextView) layout.findViewById(R.id.itemId_textView);
		int id = Integer.parseInt(itemId.getText().toString());
		TextView itemName = (TextView) layout
				.findViewById(R.id.itemName_textView);
		TextView itemPrice = (TextView) layout
				.findViewById(R.id.itemPrice_textView);
		TextView itemQuantity = (TextView) layout
				.findViewById(R.id.itemQuantity_textView);
		String name = itemName.getText().toString();
		double price = Double.parseDouble(itemPrice.getText().toString());
		int quantity = Integer.parseInt(itemQuantity.getText().toString());
		foodData.updateQuantity(id, quantity + 1);
		foodCursorAdapter.getCursor().requery();
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
	 */
	public void decreaseQuantity(View v) {
		RelativeLayout layout = (RelativeLayout) v.getParent();
		TextView itemId = (TextView) layout.findViewById(R.id.itemId_textView);
		int id = Integer.parseInt(itemId.getText().toString());
		TextView itemName = (TextView) layout
				.findViewById(R.id.itemName_textView);
		TextView itemPrice = (TextView) layout
				.findViewById(R.id.itemPrice_textView);
		TextView itemQuantity = (TextView) layout
				.findViewById(R.id.itemQuantity_textView);
		String name = itemName.getText().toString();
		double price = Double.parseDouble(itemPrice.getText().toString());
		int quantity = Integer.parseInt(itemQuantity.getText().toString());
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
		calculateTotal();
	}

	// method is not being used anymore
	/*
	 * public ArrayList<FoodItem> generateFoodList() { foodList = new
	 * ArrayList<FoodItem>(); TextView itemName; TextView itemPrice; TextView
	 * itemQuantity; String name; double price; int quantity; ListView list =
	 * getListView(); for (int i = 0; i < list.getChildCount(); i++) { itemName
	 * = (TextView) list.getChildAt(i).findViewById( R.id.itemName_textView);
	 * itemPrice = (TextView) list.getChildAt(i).findViewById(
	 * R.id.itemPrice_textView); itemQuantity = (TextView)
	 * list.getChildAt(i).findViewById( R.id.itemQuantity_textView); name =
	 * itemName.getText().toString(); price =
	 * Double.parseDouble(itemPrice.getText().toString()); quantity =
	 * Integer.parseInt(itemQuantity.getText().toString()); if (quantity > 0) {
	 * foodList.add(new FoodItem(name, price, quantity)); } } return foodList; }
	 */

	/**
	 * Calculates and displays total price. Uses food item array in shopping
	 * cart.
	 */
	private void calculateTotal() {
		// List<FoodItem> foodList = generateFoodList();
		TextView totalPrice = (TextView) findViewById(R.id.totalPrice_textView);
		double total = 0.00;
		for (int i = 0; i < foodList.size(); i++) {
			total += foodList.get(i).getPrice() * foodList.get(i).getQuantity();
		}

		if (total == 0.00) {
			totalPrice.setText("Total Price: $0.00");
		} else if (total > 0.00) {
			DecimalFormat df = new DecimalFormat("0.00");
			totalString = df.format(total);
			totalPrice.setText("Total Price: $" + totalString);
		}
		setTotalString(totalString);
	}

	/*
	 * public void checkoutActivity(View view) { Intent intent = new
	 * Intent(this, CheckoutActivity.class);
	 * 
	 * 
	 * RadioButton cheeseburgerButton = (RadioButton)
	 * findViewById(R.id.cheeseburger); RadioButton pizzaButton = (RadioButton)
	 * findViewById(R.id.pizza); RadioButton friesButton = (RadioButton)
	 * findViewById(R.id.fries); RadioButton saladButton = (RadioButton)
	 * findViewById(R.id.salad); RadioButton popButton = (RadioButton)
	 * findViewById(R.id.pop); RadioButton coffeeButton = (RadioButton)
	 * findViewById(R.id.coffee); if (cheeseburgerButton.isChecked()) {
	 * intent.putExtra("entrePrice", 3.30); } if (pizzaButton.isChecked()) {
	 * intent.putExtra("entrePrice", 2.15); } if (friesButton.isChecked()) {
	 * intent.putExtra("sidePrice", 1.49); } if (saladButton.isChecked()) {
	 * intent.putExtra("sidePrice", 3.00); } if (popButton.isChecked()) {
	 * intent.putExtra("drinkPrice", 1.49); } if (coffeeButton.isChecked()) {
	 * intent.putExtra("drinkPrice", 1.49); }
	 * 
	 * startActivity(intent); }
	 */

}
