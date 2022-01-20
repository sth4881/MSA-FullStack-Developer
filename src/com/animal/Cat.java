package com.animal;

public class Cat extends Feline implements Pet {
	@Override
	public void makeNoise() {
		System.out.println("Cat Makes Noise");
	}
	@Override
	public void eat() {
		System.out.println("Cat Eats");
	}
	@Override
	public void beFriendly() {
		System.out.println("Cat wants to be a friend");
	}
	@Override
	public void play() {
		System.out.println("Cat wants to play with");
	}
}
