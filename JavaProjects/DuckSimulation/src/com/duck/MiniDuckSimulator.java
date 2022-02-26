package com.duck;

public class MiniDuckSimulator {
	public static void main(String[] args) {
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.peformFly();
		
		Duck rubber = new RubberDuck();
		rubber.performQuack();
		rubber.peformFly();
		
		Duck decoy = new DecoyDuck();
		decoy.performQuack();
		decoy.peformFly();
		
		decoy.setFlyBehavior(new FlyRocketPowered());
		decoy.peformFly();
	}
}
