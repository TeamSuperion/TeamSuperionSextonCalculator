package com.example.sextoncalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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
	String punchCounter, punchCounterTemp;
	double punchValue = 5.5;
	ArrayList<FoodItem> foodList;

	String flexValue, flexValueTemp;
	String cashValue, cashValueTemp;
	double totalPriceInstance;
	double totalString;
	double currentFlex, currentCash;

	int punchIntCounterTemp, punchIntCounter;
	double flexDoubleValueTemp, flexDoubleValue;
	double cashDoubleValueTemp, cashDoubleValue;

	protected Button homeButton, resetButton, checkoutButton, punchButton,
			flexButton, cashButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setPunchIntCounter(0);
		setCurrentFlex(0.00);
		setCurrentCash(0.00);

		Intent myIntent = getIntent();
		extras = myIntent.getExtras();
		int flag = myIntent.getIntExtra("flag", 0);
		if (flag == 1) {

			// Intent intent = getIntent();
			// String[] foodListIntance =
			// intent.getStringArrayExtra("foodListIntance");
			setContentView(R.layout.activity_checkout);
			foodList = getIntent().getExtras().getParcelableArrayList(
					"foodList");
			ListView listView1 = (ListView) findViewById(R.id.food_listView);
			ArrayAdapter<FoodItem> adapter = new ArrayAdapter<FoodItem>(this,
					android.R.layout.simple_list_item_1, foodList);
			listView1.setAdapter(adapter);
			// extras = getIntent().getExtras();
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

		} else if (flag == 2) {
			// Intent intent = getIntent();
			// String[] foodListIntance =
			// intent.getStringArrayExtra("foodListIntance");
			setContentView(R.layout.activity_checkout);
			foodList = getIntent().getExtras().getParcelableArrayList(
					"foodList");
			ListView listView1 = (ListView) findViewById(R.id.food_listView);
			ArrayAdapter<FoodItem> adapter = new ArrayAdapter<FoodItem>(this,
					android.R.layout.simple_list_item_1, foodList);
			listView1.setAdapter(adapter);
			// extras = getIntent().getExtras();
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

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalText(double totalPrice) {
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedCurrentTotalString = dFormat.format(totalPrice);
		// EditText edit = (EditText)
		// findViewById(R.id.amountRemaining_textView);
		total.setText("$" + formattedCurrentTotalString);
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setCurrentFlex(double currentFlex) {
		this.currentFlex = currentFlex;
	}

	public double getCurrentFlex() {
		return currentFlex;
	}

	public void setFlexText(double currentFlex) {
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedCurrentFlexString = dFormat.format(currentFlex);
		EditText edit = (EditText) findViewById(R.id.flexPay_editText);
		// edit.setText("$" + formattedCurrentFlexString);
		edit.setText(formattedCurrentFlexString);
	}

	public void setCurrentCash(double currentCash) {
		this.currentCash = currentCash;
	}

	public double getCurrentCash() {
		return currentCash;
	}

	public void setCashText(double currentCash) {
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String formattedCurrentCashString = dFormat.format(currentCash);
		EditText edit = (EditText) findViewById(R.id.cashPay_editText);
		// edit.setText("$" + formattedCurrentCashString);
		edit.setText(formattedCurrentCashString);
	}

	public void setPunchIntCounterTemp(int punchIntCounterTemp) {
		this.punchIntCounterTemp = punchIntCounterTemp;
	}

	public int getPunchIntCounterTemp() {
		return punchIntCounterTemp;
	}

	public void setPunchIntCounter(int punchIntCounter) {
		this.punchIntCounter = punchIntCounter;
	}

	public int getPunchIntCounter() {
		return punchIntCounter;
	}

	public void punchActivity() {
		// final TextView newTotal = (TextView)
		// findViewById(R.id.amountRemaining_textView);
		// totalPrice = totalPrice - entrePrice - sidePrice - drinkPrice;
		int punchIntDifference;
		double punchDoubleCounterTemp;
		totalPriceInstance = getTotalPrice();

		EditText edit = (EditText) findViewById(R.id.punchPay_editText);
		punchCounterTemp = edit.getText().toString();
		punchDoubleCounterTemp = Double.parseDouble(punchCounterTemp);
		punchIntCounterTemp = (int)punchDoubleCounterTemp;
		if ((punchIntCounterTemp == punchIntCounter)
				&& (totalPriceInstance > 0.00)) {
			totalPriceInstance = totalPriceInstance - punchValue;
			setTotalPrice(totalPriceInstance);
			setTotalText(totalPriceInstance);
			punchIntCounter = getPunchIntCounter();
			punchIntCounter = punchIntCounter + 1;
			setPunchIntCounter(punchIntCounter);
			punchCounter = Integer.toString(punchIntCounter);
			edit.setText(punchCounter);
		} else if ((punchIntCounterTemp > punchIntCounter)
				&& (totalPriceInstance > 0.00)) {
			punchIntDifference = punchIntCounterTemp - punchIntCounter;
			totalPriceInstance = totalPriceInstance
					- (punchValue * punchIntDifference);
			if (totalPriceInstance > -5.50) {
				setTotalPrice(totalPriceInstance);
				setTotalText(totalPriceInstance);
				punchIntCounter = getPunchIntCounter();
				punchIntCounter = punchIntCounter + punchIntDifference;
				setPunchIntCounter(punchIntCounter);
				punchCounter = Integer.toString(punchIntCounter);
				edit.setText(punchCounter);
			} else {
				// throw exception telling user that they are using excessive
				// punches
				punchIntCounter = getPunchIntCounter();
				punchCounter = Integer.toString(punchIntCounter);
				edit.setText(punchCounter);
			}
		} else if (punchIntCounterTemp < punchIntCounter) {
			punchIntDifference = punchIntCounter - punchIntCounterTemp;
			totalPriceInstance = totalPriceInstance
					+ (punchValue * punchIntDifference);
			if (punchIntCounterTemp < 0) {
				// tell users that they can't input negative punches
			} else {
				setTotalPrice(totalPriceInstance);
				setTotalText(totalPriceInstance);
				punchIntCounter = getPunchIntCounter();
				punchIntCounter = punchIntCounter - punchIntDifference;
				setPunchIntCounter(punchIntCounter);
				punchCounter = Integer.toString(punchIntCounter);
				edit.setText(punchCounter);
			}
		}
		// TextView newTotal = (TextView)
		// findViewById(R.id.amountRemaining_textView);
		// newTotal.setText(Double.toString(getTotalPrice()));
	}

	public void flexActivity() {
		double flexDoubleDifference;
		totalPriceInstance = getTotalPrice();

		// if (totalPriceInstance > 0.00) {
		EditText edit = (EditText) findViewById(R.id.flexPay_editText);
		flexValueTemp = edit.getText().toString();
		flexDoubleValueTemp = Double.parseDouble(flexValueTemp);
		flexDoubleValue = getCurrentFlex();
		if (totalPriceInstance >= 0.00) {
			if (flexDoubleValueTemp == 0.00) {
			flexDoubleValue = flexDoubleValue + totalPriceInstance;
			setFlexText(flexDoubleValue);
			setCurrentFlex(flexDoubleValue);

			setTotalPrice(0.00);
			total.setText("$0.00");
		} else {
			if (flexDoubleValueTemp == flexDoubleValue) {
				// do nothing if there is no changes in the entered value
			} else if (flexDoubleValueTemp > flexDoubleValue) {
				flexDoubleDifference = flexDoubleValueTemp - flexDoubleValue;
				totalPriceInstance = totalPriceInstance
						- (flexDoubleDifference);
				if (totalPriceInstance >= 0.00) {
				setTotalPrice(totalPriceInstance);
				setTotalText(totalPriceInstance);
				// double temp = getCurrentFlex() + flexDoubleValue;
				flexDoubleValueTemp = getCurrentFlex() + flexDoubleDifference;
				setCurrentFlex(flexDoubleValueTemp);
				setFlexText(flexDoubleValueTemp);
				}
				else {
					//do nothing because the new totalPriceInstance is negative
					setCurrentFlex(flexDoubleValue);
					setFlexText(flexDoubleValue);
				}
			} else if (flexDoubleValueTemp < flexDoubleValue) {
				flexDoubleDifference = flexDoubleValue - flexDoubleValueTemp;
				totalPriceInstance = totalPriceInstance
						+ (flexDoubleDifference);
				setTotalPrice(totalPriceInstance);
				setTotalText(totalPriceInstance);
				// double temp = getCurrentFlex() + flexDoubleValue;
				flexDoubleValueTemp = getCurrentFlex() - flexDoubleDifference;
				setCurrentFlex(flexDoubleValueTemp);
				setFlexText(flexDoubleValueTemp);
				}
			}
	}
		else {
			//do nothing because the current totalPriceInstance is a negative value
		}

			// TextView newTotal = (TextView)
			// findViewById(R.id.amountRemaining_textView);
			// newTotal.setText(Double.toString(getTotalPrice()));
		// }
	}

	public void cashActivity() {
		double cashDoubleDifference;
		totalPriceInstance = getTotalPrice();
		// if (totalPriceInstance > 0.00) {
		EditText edit = (EditText) findViewById(R.id.cashPay_editText);
		cashValue = edit.getText().toString();
		cashDoubleValueTemp = Double.parseDouble(cashValue);
		cashDoubleValue = getCurrentCash();
		if (totalPriceInstance >= 0.00) {
			if (cashDoubleValueTemp == 0.00) {
			cashDoubleValue = cashDoubleValue + totalPriceInstance;
			setCashText(cashDoubleValue);
			setCurrentCash(cashDoubleValue);

			setTotalPrice(0.00);
			total.setText("$0.00");
		} else {
			if (cashDoubleValueTemp == cashDoubleValue) {
				// do nothing if there are no changes in the entered value
			} else if (cashDoubleValueTemp > cashDoubleValue) {
				cashDoubleDifference = cashDoubleValueTemp - cashDoubleValue;
				totalPriceInstance = totalPriceInstance - cashDoubleDifference;
				if (totalPriceInstance >= 0.00) {
				setTotalPrice(totalPriceInstance);
				setTotalText(totalPriceInstance);
				// Double temp = getCurrentCash() + cashDoubleValue;
				flexDoubleValueTemp = getCurrentCash() + cashDoubleDifference;
				setCurrentCash(flexDoubleValueTemp);
				setCashText(flexDoubleValueTemp);
				}
				else {
					//do nothing because the new totalPriceInstance is negative
					setCurrentCash(flexDoubleValue);
					setCashText(flexDoubleValue);
				}
			} else if (cashDoubleValueTemp < cashDoubleValue) {
				cashDoubleDifference = cashDoubleValue - cashDoubleValueTemp;
				totalPriceInstance = totalPriceInstance + cashDoubleDifference;
				setTotalPrice(totalPriceInstance);
				setTotalText(totalPriceInstance);
				// Double temp = getCurrentCash() + cashDoubleValue;
				flexDoubleValueTemp = getCurrentCash() - cashDoubleDifference;
				setCurrentCash(flexDoubleValueTemp);
				setCashText(flexDoubleValueTemp);
			}
		}
		}
		else {
			//do nothing because the current totalPriceInstance is a negative value
		}
		// TextView newTotal = (TextView)
		// findViewById(R.id.amountRemaining_textView);
		// newTotal.setText(Double.toString(getTotalPrice()));
		// }
	}

}
