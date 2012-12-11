package com.example.sextoncalculator.test;

import com.example.sextoncalculator.*;
import junit.framework.TestCase;

public class FoodItemTest extends TestCase {

	FoodItem one, two;
	String name = "Test";
	double price = 3.49;
	int quantity = 3;

	public void setup() {
		one = new FoodItem();
		two = new FoodItem(name, price, quantity);
	}
	
	public void testInitialize(){
		assertTrue(one.getName() == null);
		assertTrue(two.getName().equals("Test"));
		assertTrue(two.getPrice() == 3.49);
		assertTrue(two.getQuantity() == 3);
	}
	
	public void testSettersAndGetters() {
		one.setCalories(20);
		one.setName("Hello");
		one.setPrice(4.23);
		one.setQuantity(43);
		assertTrue(one.getCalories() == 20);
		assertTrue(one.getName().equals("Hello"));
		assertTrue(one.getPrice() == 4.23);
		assertTrue(one.getQuantity() == 43);
	}
	
	public void testToString(){
		assertTrue(two.toString().equals("Test $3.49 X 3"));
		one.setCalories(20);
		one.setName("Hello");
		assertTrue(one.toString().equals("Hello calories: 20"));
	}
	
}
