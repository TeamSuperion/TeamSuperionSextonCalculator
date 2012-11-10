package com.example.sextoncalculator;

public class FoodItem {

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
		return name + " " + price + " " + quantity;
	}

}
