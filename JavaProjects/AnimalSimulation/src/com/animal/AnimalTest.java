package com.animal;

public class AnimalTest {
	public static void main(String[] args) {
		Animal[] animals = new Animal[6];
		animals[0] = new Dog();
		animals[1] = new Cat();
		animals[2] = new Hippo();
		animals[3] = new Tiger();
		animals[4] = new Lion();
		animals[5] = new Wolf();
		
		Pet[] pets = new Pet[2];
		pets[0] = new Cat();
		pets[1] = new Dog();
		
		for(Animal animal : animals) {
			animal.makeNoise(); animal.eat();
			animal.roam(); animal.sleep();
			System.out.println();
		}
		
		for(Pet pet : pets) {
			pet.beFriendly(); pet.play();
			System.out.println();
		}
	}
}
