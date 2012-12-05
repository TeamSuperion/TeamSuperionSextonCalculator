package com.example.sextoncalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
	double punchValue =5.5;
	ArrayList<FoodItem> foodList;

	String flexValue;
	String cashValue;
	double totalPriceInstance;
	double totalString;
	double currentFlex = 0.00, currentCash = 0.00;

	protected Button homeButton, resetButton, checkoutButton, punchButton,
	flexButton, cashButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent myIntent = getIntent();
		extras = myIntent.getExtras();
		int flag =  myIntent.getIntExtra("flag", 0);
		if (flag == 1) {
		
		//Intent intent = getIntent();
		//String[] foodListIntance = intent.getStringArrayExtra("foodListIntance");
		setContentView(R.layout.activity_checkout);	
		foodList = getIntent().getExtras().getParcelableArrayList("foodList");
		ListView listView1 = (ListView) findViewById(R.id.food_listView);
		ArrayAdapter<FoodItem> adapter = new ArrayAdapter<FoodItem>(this, android.R.layout.simple_list_item_1, foodList);
		listView1.setAdapter(adapter);
		//extras = getIntent().getExtras();
		totalPrice = extras.getDouble("totalString");
		// entrePrice = extras.getDouble("entrePrice");
		// sidePrice = extras.getDouble("sidePrice");
		// drinkPrice = extras.getDouble("drinkPrice");
		// totalPrice = entrePrice + sidePrice + drinkPrice;

		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedString = dFormat.format(totalPrice);
		total = (TextView) findViewById(R.id.amountRemaining_textView);
		total.setText("$" + formattedString);
		setTotalPrice(totalPrice);
		
		punchButton = (Button) findViewById(R.id.punch_button);
		punchButton.setOnClickListener(this);
		
		}
		else if (flag == 2) {
			//Intent intent = getIntent();
			//String[] foodListIntance = intent.getStringArrayExtra("foodListIntance");
			setContentView(R.layout.activity_checkout);	
			foodList = getIntent().getExtras().getParcelableArrayList("foodList");
			ListView listView1 = (ListView) findViewById(R.id.food_listView);
			ArrayAdapter<FoodItem> adapter = new ArrayAdapter<FoodItem>(this, android.R.layout.simple_list_item_1, foodList);
			listView1.setAdapter(adapter);
			//extras = getIntent().getExtras();
			totalPrice = extras.getDouble("totalString");
			// entrePrice = extras.getDouble("entrePrice");
			// sidePrice = extras.getDouble("sidePrice");
			// drinkPrice = extras.getDouble("drinkPrice");
			// totalPrice = entrePrice + sidePrice + drinkPrice;
			DecimalFormat dFormat = new DecimalFormat("0.00");
			String formattedString = dFormat.format(totalPrice);
			total = (TextView) findViewById(R.id.amountRemaining_textView);
			total.setText("$" + formattedString);
			setTotalPrice(totalPrice);
			
			punchButton = (Button) findViewById(R.id.punch_button);
			punchButton.setOnClickListener(this);
			flexButton = (Button) findViewById(R.id.flex_button);
			flexButton.setOnClickListener(this);
			cashButton = (Button) findViewById(R.id.cash_button);
			cashButton.setOnClickListener(this);
		}
		homeButton = (Button) findViewById(R.id.home_button);
		homeButton.setOnClickListener(this);
		resetButton = (Button) findViewById(R.id.reset_button);
		resetButton.setOnClickListener(this);

		checkoutButton = (Button) findViewById(R.id.checkout_button);
		checkoutButton.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_checkout, menu);
		return true;
	}

	public void onClick(View v) {
		Intent intent;
		if (v == homeButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == resetButton) {
			restartActivity();
		} else if (v == punchButton) {
			punchActivity();
		} else if (v == flexButton) {
			flexActivity();
		} else if (v == cashButton) {
			cashActivity();
		}
	}

	public void restartActivity() {
		//Intent intent = new Intent();
		//act.finish();
		//intent.setClass(act, act.getClass());
		//act.startActivity(intent);
		Intent intent = getIntent();
	    overridePendingTransition(0, 0);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    finish();

	    overridePendingTransition(0, 0);
	    startActivity(intent);
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setTotalText(double totalPrice){
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedCurrentTotalString = dFormat.format(totalPrice);
		//EditText edit = (EditText) findViewById(R.id.amountRemaining_textView);
		total.setText("$" + formattedCurrentTotalString);
	}	

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setCurrentFlex(double currentFlex) {
		this.currentFlex = currentFlex;
	}

	public double getCurrentFlex(){
		return currentFlex;
	}
	
	public void setFlexText(Double currentFlex){
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedCurrentFlexString = dFormat.format(currentFlex);
		EditText edit = (EditText) findViewById(R.id.flexPay_editText);
		edit.setText("$" + formattedCurrentFlexString);
	}	
	
	public void setCurrentCash(double currentCash) {
		this.currentCash = currentCash;
	}

	public double getCurrentCash(){
		return currentCash;
	}

	public void setCashText(Double currentCash){
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedCurrentCashString = dFormat.format(currentCash);
		EditText edit = (EditText) findViewById(R.id.cashPay_editText);
		edit.setText("$" + formattedCurrentCashString);
	}

	public void punchActivity() {
		//final TextView newTotal = (TextView) findViewById(R.id.amountRemaining_textView);
		// totalPrice = totalPrice - entrePrice - sidePrice - drinkPrice;
		totalPriceInstance = getTotalPrice();
		if (totalPriceInstance > 0.00) {
			totalPriceInstance = totalPriceInstance - punchValue;
			setTotalPrice(totalPriceInstance);
			EditText edit = (EditText) findViewById(R.id.punchPay_editText);
			punchCounter = edit.getText().toString();
			int punchIntCounter = Integer.parseInt(punchCounter);
			punchIntCounter = punchIntCounter + 1;
			punchCounter = Integer.toString(punchIntCounter);
			edit.setText(punchCounter);
		}
		TextView newTotal = (TextView) findViewById(R.id.amountRemaining_textView);
		newTotal.setText(Double.toString(getTotalPrice()));
	}

	public void flexActivity() {
		totalPriceInstance = getTotalPrice();
		if (totalPriceInstance > 0.00) {
			EditText edit = (EditText) findViewById(R.id.flexPay_editText);
			flexValue = edit.getText().toString();
			double flexDoubleValue = Double.parseDouble(flexValue);
			if (flexDoubleValue == 0.00){
				flexDoubleValue = flexDoubleValue + totalPriceInstance;
				setFlexText(flexDoubleValue);
				setCurrentFlex(flexDoubleValue);

				setTotalPrice(0.00);
				total.setText("$0.00");
			}
			else {
				totalPriceInstance = totalPriceInstance - flexDoubleValue;
				setTotalPrice(totalPriceInstance);
				Double temp = getCurrentFlex() + flexDoubleValue;
				setCurrentFlex(temp);
				setTotalText(totalPriceInstance);
				setFlexText(temp);
			}
			//TextView newTotal = (TextView) findViewById(R.id.amountRemaining_textView);
			//newTotal.setText(Double.toString(getTotalPrice()));
		}
	}

	public void cashActivity() {
		totalPriceInstance = getTotalPrice();
		if (totalPriceInstance > 0.00) {
			EditText edit = (EditText) findViewById(R.id.cashPay_editText);
			cashValue = edit.getText().toString();
			double cashDoubleValue = Double.parseDouble(cashValue);
			if (cashDoubleValue == 0.00){
				cashDoubleValue = cashDoubleValue + totalPriceInstance;
				setCashText(cashDoubleValue);
				setCurrentCash(cashDoubleValue);

				setTotalPrice(0.00);
				total.setText("$0.00");
			}
			else {
				totalPriceInstance = totalPriceInstance - cashDoubleValue;
				setTotalPrice(totalPriceInstance);
				Double temp = getCurrentCash() + cashDoubleValue;
				setCurrentCash(temp);
				setTotalText(totalPriceInstance);
				setCashText(temp);
			}
			//TextView newTotal = (TextView) findViewById(R.id.amountRemaining_textView);
			//newTotal.setText(Double.toString(getTotalPrice()));
		}
	}

}
