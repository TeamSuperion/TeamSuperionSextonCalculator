package com.example.sextoncalculator;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * FoodData creates a database used to store food item information. It also
 * provides methods to perform queries on the database.
 */
public class FoodData extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "food.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "food";
	public static final String ID = BaseColumns._ID;
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String CALORIES = "calories";
	public static final String CATEGORY = "category";
	public static final String QUANTITY = "quantity";

	/** Default constructor for FoodData. */
	public FoodData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Called when database is created. Creates new table with appropriate
	 * columns and conditions.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// String createSql = "CREATE TABLE " + TABLE_NAME + " (" + ID +
		// " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " +
		// PRICE + " REAL NOT NULL, " + CALORIES + " INTEGER NOT NULL);";
		// db.execSQL(createSql);
		String createSql = "CREATE TABLE " + TABLE_NAME + " (" + ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
				+ " TEXT NOT NULL, " + CALORIES + " INTEGER NOT NULL, " + PRICE
				+ " TEXT NOT NULL, " + CATEGORY + " INTEGER NOT NULL, "
				+ QUANTITY + " INTEGER NOT NULL);";
		db.execSQL(createSql);
	}

	/** Called when database version changes. Replaces old table with new one. */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropSql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(dropSql);
		onCreate(db);
	}

	/**
	 * Inserts new food item record into database. Information includes name,
	 * calories, price, and quantity.
	 */
	public void insert(String name, int calories, String price, int category,
			int quantity) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(CALORIES, calories);
		values.put(PRICE, price);
		values.put(CATEGORY, category);
		values.put(QUANTITY, quantity);
		db.insertOrThrow(TABLE_NAME, null, values);
	}

	/** Returns all food item records from database. */
	public Cursor all(Activity activity) {
		String[] from = { ID, NAME, CALORIES, PRICE, CATEGORY, QUANTITY };
		String order = NAME;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, from, null, null, null, null,
				order);
		activity.startManagingCursor(cursor);
		return cursor;
	}

	/** Returns all category 1 (entre) food item records from database. */
	public Cursor cat1(Activity activity) {
		String[] from = { ID, NAME, CALORIES, PRICE, CATEGORY };
		String order = NAME;
		String selection = CATEGORY + " in (1, 4)";
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, from, selection, null, null, null,
				order);
		activity.startManagingCursor(cursor);
		return cursor;
	}

	/** Returns all category 2 (side) food item records from database. */
	public Cursor cat2(Activity activity) {
		String[] from = { ID, NAME, CALORIES, PRICE, CATEGORY };
		String order = NAME;
		String selection = CATEGORY + " = 2";
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, from, selection, null, null, null,
				order);
		activity.startManagingCursor(cursor);
		return cursor;
	}

	/** Returns all category 3 (drink) food item records from database. */
	public Cursor cat3(Activity activity) {
		String[] from = { ID, NAME, CALORIES, PRICE, CATEGORY };
		String order = NAME;
		String selection = CATEGORY + " = 3";
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, from, selection, null, null, null,
				order);
		activity.startManagingCursor(cursor);
		return cursor;
	}

	/** Returns number of food item records in database. */
	public long count() {
		SQLiteDatabase db = getReadableDatabase();
		return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
	}

	/** Loads database with food item information. */
	public void load() {

		insert("Apple", 110, "0.70", 2, 0);
		insert("Orange", 86, "0.70", 2, 0);
		insert("Banana", 105, "0.70", 2, 0);
		insert("Cut Fresh Fruit", 10, "2.75", 2, 0);
		insert("Garden Salad", 200, "3.00", 2, 0);
		insert("Raw Veggies", 50, "2.00", 2, 0);
		insert("Tuna Sandwich", 220, "2.75", 1, 0);
		insert("Egg Salad Sandwich", 230, "2.75", 1, 0);
		insert("Chicken Salad Sandwich", 175, "3.00", 1, 0);
		insert("White Sub 1/3", 125, "2.75", 5, 0);
		insert("Wheat Sub 1/3", 134, "2.75", 5, 0);
		insert("White Sub 1/2", 190, "4.10", 1, 0);
		insert("Wheat Sub 1/2", 200, "4.10", 1, 0);
		insert("White Sub Full", 380, "8.20", 4, 0);
		insert("Wheat Sub Full", 400, "8.20", 4, 0);
		insert("Wrap", 180, "4.10", 1, 0);
		insert("Crossiant", 210, "4.50", 5, 0);
		insert("Jumbo Muffin", 390, "1.10", 5, 0);
		insert("Cinnamon Roll", 600, "1.00", 5, 0);
		insert("Scone", 460, "1.25", 5, 0);
		insert("Bagel", 250, "1.10", 5, 0);
		insert("Monster Cookie", 340, "0.75", 5, 0);
		insert("Peanut Butter Cookie", 300, "0.75", 5, 0);
		insert("Chocolate Chip Cookie", 300, "0.75", 5, 0);
		insert("French Fries", 450, "1.49", 2, 0);
		insert("Onion Rings", 470, "2.25", 2, 0);
		insert("Curly Fries", 445, "1.49", 2, 0);
		insert("Tater Tots", 500, "2.25", 2, 0);
		insert("Hamburger", 315, "3.15", 1, 0);
		insert("Cheeseburger", 430, "3.30", 1, 0);
		insert("Gardenburger", 225, "3.89", 1, 0);
		insert("Grilled Chicken Fillet", 240, "3.79", 1, 0);
		insert("Fish Burger", 400, "3.10", 1, 0);
		insert("Breaded Chicken Patty", 370, "3.50", 1, 0);
		insert("Chicken Fingers", 640, "4.50", 4, 0);
		insert("Nachos", 725, "2.50", 4, 0);
		insert("Soft Pretzal", 480, "2.25", 5, 0);
		insert("Chili", 300, "2.00", 2, 0);
		insert("Pepperoni Pizza", 300, "2.15", 1, 0);
		insert("Sausage Pizza", 315, "2.15", 1, 0);
		insert("Cheese Pizza", 265, "2.15", 1, 0);
		insert("Veggie Pizza", 270, "2.15", 1, 0);
		insert("Cappucino 12 oz", 100, "0.99", 3, 0);
		insert("Cappucino 16 oz", 150, "1.49", 3, 0);
		insert("Hot Chocolate 12 oz", 200, "0.99", 3, 0);
		insert("Hot Chocolate 16 oz", 150, "1.49", 3, 0);
		insert("Hot Cider 12 oz", 200, "0.99", 3, 0);
		insert("Hot Cider 16 oz", 180, "1.49", 3, 0);
		insert("Skim Milk 12 oz", 240, "0.99", 3, 0);
		insert("Skim Milk 16 oz", 120, "1.49", 3, 0);
		insert("Chocolate Milk 12 oz", 260, "0.99", 3, 0);
		insert("Chocolate Milk 16 oz", 225, "1.49", 3, 0);
		insert("Black Coffee 12 oz", 5, "0.99", 3, 0);
		insert("Black Coffee 16 oz", 10, "1.49", 3, 0);
		insert("Tea 12 oz", 100, "0.99", 3, 0);
		insert("Tea 16 oz", 130, "1.49", 3, 0);
		insert("Pop 12 oz", 140, "0.99", 3, 0);
		insert("Pop 16 oz", 190, "1.49", 3, 0);
		// Cat 1 Main
		// Cat 2 Side
		// Cat 3 Beverage
		// Cat 4 Main+
		// Cat 5 Misc
	}

	/** Updates quantity for a food item record. */
	public void updateQuantity(int id, int quantity) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(QUANTITY, quantity);
		db.update(TABLE_NAME, values, ID + "=" + id, null);
	}

	/** Resets quantity to zero for all food item records. */
	public void resetQuantity() {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(QUANTITY, 0);
		db.update(TABLE_NAME, values, null, null);
		// db.update(TABLE_NAME, values, null);
	}
}
