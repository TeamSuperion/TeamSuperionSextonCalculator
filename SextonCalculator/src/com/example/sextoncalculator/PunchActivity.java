package com.example.sextoncalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class PunchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_punch, menu);
        return true;
    }
    
    public void checkoutActivity(View view) {
    	Intent intent = new Intent(this, CheckoutActivity.class);
    	startActivity(intent);
    }
    
    public void homeActivity(View view) {
    	Intent intent = new Intent(this, HomeActivity.class);
    	startActivity(intent);
    }
    
    public void punchActivity(View view) {
    	Intent intent = new Intent(this, CheckoutActivity.class);
    	startActivity(intent);
    }
}
