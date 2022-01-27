package com.animal;

public class Tiger extends Feline {
	@Override
	public void makeNoise() {
		System.out.println("Tiger Makes Noise");
	}
	@Override
	public void eat() {
		System.out.println("Tiger Eats");
	}
}
