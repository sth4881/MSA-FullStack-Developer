package com.animal;

public abstract class Animal {
	private String picture;
	private String food;
	private String hunger;
	private String boundaries;
	private String location;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getHunger() {
		return hunger;
	}
	public void setHunger(String hunger) {
		this.hunger = hunger;
	}
	public String getBoundaries() {
		return boundaries;
	}
	public void setBoundaries(String boundaries) {
		this.boundaries = boundaries;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public abstract void makeNoise();
	public abstract void eat();
	public abstract void roam();
	
	public void sleep() {
		System.out.println("Zzz...");
	}
}