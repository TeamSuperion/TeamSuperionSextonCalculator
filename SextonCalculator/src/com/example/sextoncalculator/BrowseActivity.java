package com.example.sextoncalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
}
