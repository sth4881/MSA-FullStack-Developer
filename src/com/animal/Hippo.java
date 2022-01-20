package com.animal;

public class Hippo extends Animal {
	@Override
	public void makeNoise() {
		System.out.println("Hippo Makes Noise");
	}
	@Override
	public void eat() {
		System.out.println("Hippo Eats");
	}
	@Override
	public void roam() {
		System.out.println("Hippo Roams");
	}
}
