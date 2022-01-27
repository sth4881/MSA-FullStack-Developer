package com.bear;

public class PolarBear extends Bear {
	public PolarBear() {
		roarBehavior = new BearRoar();
		huntBehavior = new ChaseAndBite();
	}
	@Override
	public void display() {
		System.out.println("The Polar Bar is taking care of its young . . .");
	}
}
