package com.example.sextoncalculator;

public class PunchFoodItem {

	private String name;
	private double calories;
	private int category;

	public PunchFoodItem() {
	}

	public PunchFoodItem(String name, double calories, int category) {
		this.name = name;
		this.calories = calories;
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public void setCalories(double calories){
		this.calories = calories;
	}

	public String getName() {
		return name;
	}
	
	public int getCategory() {
		return category;
	}
	
	public double getCalories() {
		return calories;
	}

	public String toString() {
		return name + " " + calories + " " + category;
	}

}