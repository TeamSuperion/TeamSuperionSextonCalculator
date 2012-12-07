package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * HeaderActivity is used to give the buttons of the Header xml file some functionality
 * @author Adam Bachmeier, Justin Springer, Tsuehue Xiong, Jonathan Ly
 *
 */
public class HeaderActivity extends Activity implements OnClickListener {

	protected Button homeButton, resetButton, checkoutButton;

	/**
	 * Generic onCreate method
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_header);

		homeButton = (Button) findViewById(R.id.start_button);
		homeButton.setOnClickListener(this);
		resetButton = (Button) findViewById(R.id.start_button);
		resetButton.setOnClickListener(this);
		checkoutButton = (Button) findViewById(R.id.start_button);
		checkoutButton.setOnClickListener(this);
	}

	/**
	 * Generic onCreateOptionsMenu method
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_header, menu);
		return true;
	}

	/**
	 * Handles when a button is pressed/clicked
	 */
	public void onClick(View v) {
		Intent intent;
		if (v == homeButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == resetButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		} else if (v == checkoutButton) {
			intent = new Intent(this, CheckoutActivity.class);
			startActivity(intent);
		}
	}
}
