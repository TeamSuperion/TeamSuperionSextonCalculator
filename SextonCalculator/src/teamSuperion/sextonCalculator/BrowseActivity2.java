package teamSuperion.sextonCalculator;


import com.example.sextoncalculator.R;
import com.example.sextoncalculator.R.id;
import com.example.sextoncalculator.R.layout;
import com.example.sextoncalculator.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class BrowseActivity2 extends Activity {
	protected Spinner spinner;
	protected FoodData foodData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        foodData = new FoodData(this);
		spinner = (Spinner) findViewById(R.id.spinner);
		if(foodData.count() == 0){
			loadData();
		}
		Cursor cursor = foodData.all(this);
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter foodCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, new String[] { foodData.NAME }, new int[] { android.R.id.text1 });
		spinner.setAdapter(foodCursorAdapter);
		foodCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_browse, menu);
        return true;
    }
    
    public void homeActivity(View view) {
    	Intent intent = new Intent(this, HomeActivity2.class);
    	startActivity(intent);
    }
    
    public void checkoutActivity(View view) {
    	Intent intent = new Intent(this, CheckoutActivity2.class);
    	startActivity(intent);
    }
    
	public void loadData(){
	foodData.insert("Apple", 1 , 1);
	foodData.insert("Banana", 1 , 1);
	foodData.insert("Orange", 1 , 1);
	}
}
