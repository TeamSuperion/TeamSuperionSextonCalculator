package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Handles the events brought up by the Main xml file
 * 
 * @author Johnathan Ly, Justin Springer, Tsuehue Xiong, Adam Bachmeier
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	protected Button startButton;

	/**
	 * Generic onCreate method
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(this);
	}

	/**
	 * Generic onCreateOptionsMenu method
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Handles the events brought up by a button being pressed/clicked
	 */
	public void onClick(View v) {
		Intent intent;
		if (v == startButton) {
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
	}

}
