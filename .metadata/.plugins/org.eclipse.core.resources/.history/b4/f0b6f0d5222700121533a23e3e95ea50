package teamSuperion.sextonCalculator;

import java.text.DecimalFormat;

import com.example.sextoncalculator.R;
import com.example.sextoncalculator.R.id;
import com.example.sextoncalculator.R.layout;
import com.example.sextoncalculator.R.menu;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("UseValueOf")
public class CheckoutActivity2 extends Activity {

	TextView total;
	double totalPrice;
	Bundle extras;
	double entrePrice;
	double sidePrice;
	double drinkPrice;
	String punchCounter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);
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

	public void homeActivity(View view) {
		Intent intent = new Intent(this, HomeActivity2.class);
		startActivity(intent);
	}

	public void backActivity(View view) {
		Intent intent = new Intent(this, PunchActivity2.class);
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

}
