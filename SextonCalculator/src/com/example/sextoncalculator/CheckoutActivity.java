package com.example.sextoncalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CheckoutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Bundle extras = getIntent().getExtras();
        double entrePrice = extras.getDouble("entrePrice");
        double sidePrice = extras.getDouble("sidePrice");
        double drinkPrice = extras.getDouble("drinkPrice");
        TextView total = (TextView)findViewById(R.id.amountRemaining_textView);
        total.setText("$"+String.valueOf(entrePrice + sidePrice + drinkPrice));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_checkout, menu);
        return true;
    }
    
    public void homeActivity(View view) {
    	Intent intent = new Intent(this, HomeActivity.class);
    	startActivity(intent);
    }
    
    public void backActivity(View view) {
    	Intent intent = new Intent(this, HomeActivity.class);
    	startActivity(intent);
    }
}
