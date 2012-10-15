package com.example.sextoncalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
}
