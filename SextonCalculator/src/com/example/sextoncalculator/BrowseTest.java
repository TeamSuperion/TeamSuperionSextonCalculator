package com.example.sextoncalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class BrowseTest extends Activity implements OnClickListener {

	Button entree, sides, drinks;
	ListView entreeList, sidesList, drinksList;
	FoodData fd;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_test);

		entree = (Button) findViewById(R.id.entree_button);
		sides = (Button) findViewById(R.id.sides_button);
		drinks = (Button) findViewById(R.id.drinks_button);
		entree.setOnClickListener(this);
		sides.setOnClickListener(this);
		drinks.setOnClickListener(this);

		entreeList = (ListView) findViewById(R.id.entree_list);
		sidesList = (ListView) findViewById(R.id.sides_list);
		drinksList = (ListView) findViewById(R.id.drinks_list);

		fd = new FoodData(null);

		// Put the database info into the corresponding ListView

		sidesList.setVisibility(View.GONE);
		drinksList.setVisibility(View.GONE);

	}

	public void onClick(View v) {

		if (v == entree) {
			entreeList.setVisibility(View.VISIBLE);
			sidesList.setVisibility(View.GONE);
			drinksList.setVisibility(View.GONE);
		} else if (v == sides) {
			sidesList.setVisibility(View.VISIBLE);
			entreeList.setVisibility(View.GONE);
			drinksList.setVisibility(View.GONE);
		} else if (v == drinks) {
			drinksList.setVisibility(View.VISIBLE);
			entreeList.setVisibility(View.GONE);
			sidesList.setVisibility(View.GONE);
		}

	}

}
