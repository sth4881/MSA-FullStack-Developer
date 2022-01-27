package com.animal;

public class Lion extends Feline {
	@Override
	public void makeNoise() {
		System.out.println("Lion Makes Noise");
	}
	@Override
	public void eat() {
		System.out.println("Lion Eats");
	}
}
