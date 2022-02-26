package com.bear;

public class TeddyBear extends Bear {
	public TeddyBear() {
		roarBehavior = new UnableRoar();
		huntBehavior = new UnableHunt();
	}
	@Override
	public void display() {
		System.out.println("The Teddy Bear is quiet . . .");
	}
}
