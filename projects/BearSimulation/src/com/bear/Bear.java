package com.bear;

public abstract class Bear {
	RoarBehavior roarBehavior;
	HuntBehavior huntBehavior;
	public Bear() {
		
	}
	
	public void setRoarBehavior(RoarBehavior roarBehavior) {
		this.roarBehavior = roarBehavior;
	}

	public void setHuntBehavior(HuntBehavior huntBehavior) {
		this.huntBehavior = huntBehavior;
	}

	public abstract void display();
	
	public void performRoar() {
		roarBehavior.roar();
	}
	
	public void performHunt() {
		huntBehavior.hunt();
	}
	
	public void sleep() {
		System.out.println("Zzz...");
	}
}
