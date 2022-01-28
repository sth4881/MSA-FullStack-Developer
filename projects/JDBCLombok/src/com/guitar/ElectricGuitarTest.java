package com.guitar;

public class ElectricGuitarTest {
	public static void main(String[] args) {
		ElectricGuitar guitar = new ElectricGuitar();
		guitar.setBrand("YAMAHA");
		System.out.println(guitar.getBrand());
		System.out.println(guitar.toString());  // 객체의 현재 state 확인!
		System.out.println(guitar);
	}
}