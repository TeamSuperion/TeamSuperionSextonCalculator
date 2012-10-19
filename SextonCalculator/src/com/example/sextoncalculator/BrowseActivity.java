package com.example.sextoncalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BrowseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
