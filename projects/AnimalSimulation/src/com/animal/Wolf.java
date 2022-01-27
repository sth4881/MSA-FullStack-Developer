package com.animal;

public class Wolf extends Canine {
	@Override
	public void makeNoise() {
		System.out.println("Wolf Makes Noise");
	}
	@Override
	public void eat() {
		System.out.println("Wolf Eats");
	}
}
