package com.example.sextoncalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {

	private String name;
	private double price;
	private int quantity;

	public FoodItem() {
	}

	public FoodItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int calories) {
		this.quantity = calories;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String toString() {
		return name + " $" + price + " X " + quantity;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeDouble(price);
		dest.writeInt(quantity);
	}

	public static final Parcelable.Creator<FoodItem> CREATOR = new Parcelable.Creator<FoodItem>() {
		public FoodItem createFromParcel(Parcel in) {
			return new FoodItem(in);
		}

		public FoodItem[] newArray(int size) {
			return new FoodItem[size];
		}
	};

	private FoodItem(Parcel in) {
		name = in.readString();
		price = in.readDouble();
		quantity = in.readInt();
	}
}
