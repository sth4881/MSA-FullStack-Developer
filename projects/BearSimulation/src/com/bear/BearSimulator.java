package com.bear;

public class BearSimulator {
	public static void main(String[] args) {
		Bear grizzlyBear = new GrizzlyBear();
		grizzlyBear.display();
		grizzlyBear.performRoar();
		grizzlyBear.performHunt();
		System.out.println();
		
		Bear polarBear = new PolarBear();
		polarBear.display();
		polarBear.performRoar();
		polarBear.performHunt();
		System.out.println();
		
		Bear teddyBear = new TeddyBear();
		teddyBear.display();
		teddyBear.performRoar();
		teddyBear.performHunt();
		System.out.println();
		
		teddyBear.setRoarBehavior(new TeddyBearSound());
		teddyBear.performRoar();
	}
}
