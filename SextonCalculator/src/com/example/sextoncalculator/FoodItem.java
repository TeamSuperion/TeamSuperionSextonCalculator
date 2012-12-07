package com.example.sextoncalculator;

import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FoodItem is used to create a food item that is available for purchase. Food
 * items have a name, price, and quantity.
 * @author Jonathan Ly, Tsuehue Xiong, Adam Bachmeier, Justin Springer
 *
 */
public class FoodItem implements Parcelable, Comparable<FoodItem> {

	private String name;
	private double price;
	private int quantity;
	private int calories = 0;

	/**
	 * Default constructor for FoodItem
	 */
	public FoodItem() {
	}

	/**
	 * Constructor for FoodItem. Initializes name, price, and quantity
	 * @param name the initialized name
	 * @param price the initialized price
	 * @param quantity the initialized quantity
	 */
	public FoodItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/** 
	 * Sets the name of the food item. 
	 * @param name the name to set it to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 *Sets the price of the food item. 
	 * @param price the price to set it to
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/** 
	 * Sets the quantity of the food item. 
	 * @param quantity the quantity to set it to
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Sets the calories of the food item
	 * @param calories
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * Gets the name of the food item
	 * @return name the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the price of the food item
	 * @return price the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets the quantity of the food item 
	 * @return quantity the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Gets the calories of the food item
	 * @return calories the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * Returns a string formated version of the food item
	 * two options depending if calories was set
	 * @return out the string representation of food item
	 */
	public String toString() {
		String out;
		if (calories == 0) {
			out = name + " $" + price + " X " + quantity;
		} else {
			out = name + " calories: " + calories;
		}
		return out;
	}

	/**
	 * Checks if two food items have the same name.
	 * @return true if they are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof FoodItem) {
			result = this.name.equals(((FoodItem) obj).getName());
		}
		return result;
	}

	/*
	 * Parcelable methods are needed to make FoodItem passable to other
	 * activities.
	 */

	/**
	 * Empty method, required for implementing parcelable.
	 */
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Writes food item to parcel.
	 */
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeDouble(price);
		dest.writeInt(quantity);
		dest.writeInt(calories);
	}

	/**
	 * Creates parcelable food item.
	 */
	public static final Parcelable.Creator<FoodItem> CREATOR = new Parcelable.Creator<FoodItem>() {
		public FoodItem createFromParcel(Parcel in) {
			return new FoodItem(in);
		}

		public FoodItem[] newArray(int size) {
			return new FoodItem[size];
		}
	};

	/**
	 * Reads food item from parcel.
	 * @param in
	 */
	private FoodItem(Parcel in) {
		name = in.readString();
		price = in.readDouble();
		quantity = in.readInt();
		calories = in.readInt();
	}

	/**
	 * Compares two food items to determine if they are equal
	 * @return 1 if ___, 0 if ___
	 */
	public int compareTo(FoodItem another) {
		String thisFoodItem = this.getName();
		String anotherFoodItem = another.getName();
		return thisFoodItem.compareTo(anotherFoodItem);
	}
}
