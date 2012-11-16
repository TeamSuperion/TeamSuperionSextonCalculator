package com.example.sextoncalculator;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class FoodData extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "food.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "food";
	public static final String ID = BaseColumns._ID;
	public static final String NAME = "name";
	public String PRICE = "price";
	public static final String CALORIES = "calories";
	public static final String CATEGORY = "category";
	
	public FoodData(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//String createSql = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + PRICE + " REAL NOT NULL, " + CALORIES + " INTEGER NOT NULL);"; 
		//db.execSQL(createSql);
		String createSql = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + CALORIES + " INTEGER NOT NULL, " + PRICE + " TEXT NOT NULL, " + CATEGORY + " INTEGER NOT NULL);"; 
		db.execSQL(createSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropSql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(dropSql);
		onCreate(db);
	}

	public void insert(String name, int calories, String price, int category){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(CALORIES, calories);
		values.put(PRICE, price);
		values.put(CATEGORY, category);
		db.insertOrThrow(TABLE_NAME, null, values);
	}
	
	public Cursor all(Activity activity){
		String[] from = { ID, NAME, CALORIES, PRICE, CATEGORY };
		String order = NAME;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, from, null, null, null, null, order);
		activity.startManagingCursor(cursor);
		return cursor;
	}
	
	public long count(){
		SQLiteDatabase db = getReadableDatabase();
		return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
	}
	
	public void load(){
		//insert("Apple", .50 , 1);
		//insert("Banana", .75 , 1);
		//insert("Orange", 1.00 , 1);
		
		insert("Apple",110,"0.70",2);
		insert("Orange",86,"0.70",2);
		insert("Banana",105,"0.70",2);
		insert("Cut Fresh Fruit",10,"2.75",2);
		insert("Chef Salad w/cheese & meat",200,"3.00",2);
		insert("Raw Veggies",50,"2.00",2);
		insert("Tuna Sandwich",220,"2.75",1);
		insert("Egg Salad Sandwich",230,"2.75",1);
		insert("Chicken Salad Sandwich",175,"3.00",1);
		insert("White Sub 1/3",125,"2.75",5);
		insert("Wheat Sub 1/3",134,"2.75",5);
		insert("White Sub 1/2",190,"4.10",1);
		insert("Wheat Sub 1/2",200,"4.10",1);
		insert("White Sub Full",380,"8.20",4);
		insert("Wheat Sub Full",400,"8.20",4);
		insert("Wrap",180,"4.10",1);
		insert("Crossiant",210,"4.50",5);
		insert("Jumbo Muffin",390,"1.10",5);
		insert("Cinnamon Roll",600,"1.00",5);
		insert("Scone",460,"1.25",5);
		insert("Bagel",250,"1.10",5);
		insert("Monster Cookie",340,"0.75",5);
		insert("Peanut Butter Cookie",300,"0.75",5);
		insert("Chocolate Chip Cookie",300,"0.75",5);
		insert("French Fries",450,"1.49",2);
		insert("Onion Rings",470,"2.25",2);
		insert("Curly Fries",445,"1.49",2);
		insert("Tater Tots",500,"2.25",2);
		insert("Hamburger",315,"3.15",1);
		insert("Cheeseburger",430,"3.30",1);
		insert("Gardenburger",225,"3.89",1);
		insert("Grilled Chicken Fillet",240,"3.79",1);
		insert("Fish Burger",400,"3.10",1);
		insert("Breaded Chicken Patty",370,"3.50",1);
		insert("Chicken Fingers",640,"4.50",4);
		insert("Nachos",725,"2.50",4);
		insert("Soft Pretzal",480,"2.25",5);	
		insert("Chili",300,"2.00",2);
		insert("Pepperoni Pizza",300,"2.15",1);
		insert("Sausage Pizza",315,"2.15",1);
		insert("Cheese Pizza",265,"2.15",1);
		insert("Veggie Pizza",270,"2.15",1);
		insert("Cappucino 12 oz",100,"0.99",3);
		insert("Cappucino 16 oz",150,"1.49",3);
		insert("Hot Chocolate 12 oz",200,"0.99",3);
		insert("Hot Chocolate 16 oz",150,"1.49",3);
		insert("Hot Cider 12 oz",200,"0.99",3);
		insert("Hot Cider 16 oz",180,"1.49",3);
		insert("Skim Milk 12 oz",240,"0.99",3);
		insert("Skim Milk 16 oz",120,"1.49",3);
		insert("Chocolate Milk 12 oz",260,"0.99",3);
		insert("Chocolate Milk 16 oz",225,"1.49",3);
		insert("Black Coffee 12 oz",5,"0.99",3);
		insert("Black Coffee 16 oz",10,"1.49",3);
		insert("Tea 12 oz",100,"0.99",3);
		insert("Tea 16 oz",130,"1.49",3);
		insert("Pop 12 oz",140,"0.99",3);
		insert("Pop 16 oz",190,"1.49",3);
//Cat 1		Main	
//Cat 2		Side	
//Cat 3		Beverage
//Cat 4		Main+	
//Cat 5		Misc	
	}

}

