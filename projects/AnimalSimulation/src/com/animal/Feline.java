package com.animal;

public abstract class Feline extends Animal {
	@Override
	public abstract void makeNoise();
	@Override
	public abstract void eat();
	@Override
	public void roam() {
		System.out.println("Feline Roams");
	}
}
