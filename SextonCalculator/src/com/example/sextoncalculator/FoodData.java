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
	public static final String PRICE = "price";
	public static final String CALORIES = "calories";
	
	public FoodData(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSql = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + PRICE + " INTEGER NOT NULL, " + CALORIES + " INTEGER NOT NULL);"; 
		db.execSQL(createSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropSql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(dropSql);
		onCreate(db);
	}

	public void insert(String name, int price, int calories){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(PRICE, price);
		values.put(CALORIES, calories);
		db.insertOrThrow(TABLE_NAME, null, values);
	}
	
	public Cursor all(Activity activity){
		String[] from = { ID, NAME, PRICE, CALORIES };
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
		insert("Apple", 1 , 1);
		insert("Banana", 1 , 1);
		insert("Orange", 1 , 1);
	}

}
