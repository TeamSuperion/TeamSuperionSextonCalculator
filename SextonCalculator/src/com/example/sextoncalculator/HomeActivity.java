package com.example.sextoncalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    
    public void punchActivity(View view) {
    	Intent intent = new Intent(this, PunchActivity.class);
    	startActivity(intent);
    }
    
    public void browseActivity(View view) {
    	Intent intent = new Intent(this, BrowseActivity.class);
    	startActivity(intent);
    }
    
    public void calorieActivity(View view) {
    	Intent intent = new Intent(this, CalorieActivity.class);
    	startActivity(intent);
    }
}
