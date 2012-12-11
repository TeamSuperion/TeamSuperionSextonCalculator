package com.example.sextoncalculator.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.example.sextoncalculator.*;
import com.example.sextoncalculator.R;

public class BrowseActivityTest extends
		ActivityInstrumentationTestCase2<BrowseActivity> {

	private Button homeButton, resetButton, checkoutButton;
	private Activity bActivity;
	
	public BrowseActivityTest() {
		super("com.example.sextoncalculator", BrowseActivity.class);
	}
	
	protected void setup() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(false);
		
		bActivity = getActivity();

		homeButton = (Button) bActivity.findViewById(com.example.sextoncalculator.R.id.home_button);
		resetButton = (Button) bActivity.findViewById(com.example.sextoncalculator.R.id.reset_button);
		checkoutButton = (Button) bActivity.findViewById(com.example.sextoncalculator.R.id.checkout_button);
	}
	
	
	
	
	
	

}
