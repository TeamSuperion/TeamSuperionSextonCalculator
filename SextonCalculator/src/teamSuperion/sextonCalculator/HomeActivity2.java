package teamSuperion.sextonCalculator;

import teamSuperion.sextonCalculator.R.layout;
import teamSuperion.sextonCalculator.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomeActivity2 extends Activity {

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
    	Intent intent = new Intent(this, PunchActivity2.class);
    	startActivity(intent);
    }
    
    public void browseActivity(View view) {
    	Intent intent = new Intent(this, BrowseActivity2.class);
    	startActivity(intent);
    }
    
    public void calorieActivity(View view) {
    	Intent intent = new Intent(this, CalorieActivity2.class);
    	startActivity(intent);
    }
}
