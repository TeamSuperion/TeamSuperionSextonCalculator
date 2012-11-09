package com.example.sextoncalculator;

public class FoodItem {

	private String name;
	private double price;
	private int calories;

	public FoodItem() {
	}

	public FoodItem(String name, double price, int calories) {
		this.name = name;
		this.price = price;
		this.calories = calories;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getCalories() {
		return calories;
	}

	public String toString() {
		return name + " " + price + " " + calories;
	}

}
