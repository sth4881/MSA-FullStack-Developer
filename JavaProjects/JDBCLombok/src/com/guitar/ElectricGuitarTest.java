package com.guitar;

public class ElectricGuitarTest {
	public static void main(String[] args) {
		ElectricGuitar guitar = new ElectricGuitar();
		guitar.setBrand("YAMAHA");
		System.out.println(guitar.getBrand());
		System.out.println(guitar.toString());  // ��ü�� ���� state Ȯ��!
		System.out.println(guitar);
	}
}