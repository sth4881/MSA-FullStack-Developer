package com.bear;

public class GrizzlyBear extends Bear {
	public GrizzlyBear() {
		roarBehavior = new BearRoar();
		huntBehavior = new ChaseAndSmash();
	}
	@Override
	public void display() {
		System.out.println("The Grizzly Bear is eating honey . . .");
	}
}
