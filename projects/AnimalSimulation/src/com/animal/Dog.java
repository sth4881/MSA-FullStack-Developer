package com.animal;

public class Dog extends Canine implements Pet {
	@Override
	public void makeNoise() {
		System.out.println("Dog Makes Noise");
	}
	@Override
	public void eat() {
		System.out.println("Dog Eats");
	}
	@Override
	public void beFriendly() {
		System.out.println("Dog wants to be a friend");
	}
	@Override
	public void play() {
		System.out.println("Dog wants to play with");
	}
	
}
