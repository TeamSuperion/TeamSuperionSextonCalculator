package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {

	protected Button punchButton, 
	//calorieButton, 
	browseButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		punchButton = (Button) findViewById(R.id.punch_button);
		punchButton.setOnClickListener(this);
//		calorieButton = (Button) findViewById(R.id.calorie_button);
//		calorieButton.setOnClickListener(this);
		browseButton = (Button) findViewById(R.id.browse_button);
		browseButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public void onClick(View v) {
		Intent intent;
		if (v == punchButton) {
			intent = new Intent(this, PunchActivity.class);
			startActivity(intent);

		} else if (v == browseButton) {
			intent = new Intent(this, BrowseActivity.class);
			startActivity(intent);
		}
	}
}
