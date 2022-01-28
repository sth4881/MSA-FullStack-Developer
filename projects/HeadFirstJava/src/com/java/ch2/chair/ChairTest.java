package com.java.ch2.chair;

public class ChairTest {
	public static void main(String[] args) {
		Square square = new Square();
		square.rotate();
		square.playSound();
		
		Amoeba amoeba = new Amoeba();
		amoeba.rotate();
		amoeba.playSound();
	}
}
