package com.example.sextoncalculator;

import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FoodItem is used to create a food item that is available for purchase. Food
 * items have a name, price, and quantity.
 */
public class FoodItem implements Parcelable, Comparable<FoodItem> {

	private String name;
	private double price;
	private int quantity;

	/** Default constructor for FoodItem */
	public FoodItem() {
	}

	/** Constructor for FoodItem. Initializes name, price, and quantity. */
	public FoodItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/** Sets the name of the food item. */
	public void setName(String name) {
		this.name = name;
	}

	/** Sets the price of the food item. */
	public void setPrice(double price) {
		this.price = price;
	}

	/** Sets the quantity of the food item. */
	public void setQuantity(int calories) {
		this.quantity = calories;
	}

	/** Gets the name of the food item. */
	public String getName() {
		return name;
	}

	/** Gets the price of the food item. */
	public double getPrice() {
		return price;
	}

	/** Gets the quantity of the food item. */
	public int getQuantity() {
		return quantity;
	}

	/** Returns food item description. */
	public String toString() {
		return name + " $" + price + " X " + quantity;
	}

	/** Checks if two food items have the same name. */
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

	/** Empty method, required for implementing parcelable. */
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/** Writes food item to parcel. */
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeDouble(price);
		dest.writeInt(quantity);
	}

	/** Creates parcelable food item. */
	public static final Parcelable.Creator<FoodItem> CREATOR = new Parcelable.Creator<FoodItem>() {
		public FoodItem createFromParcel(Parcel in) {
			return new FoodItem(in);
		}

		public FoodItem[] newArray(int size) {
			return new FoodItem[size];
		}
	};

	/** Reads food item from parcel. */
	private FoodItem(Parcel in) {
		name = in.readString();
		price = in.readDouble();
		quantity = in.readInt();
	}

	public int compareTo(FoodItem another) {
		String thisFoodItem = this.getName();
		String anotherFoodItem = another.getName();
		return thisFoodItem.compareTo(anotherFoodItem);
	}
}
