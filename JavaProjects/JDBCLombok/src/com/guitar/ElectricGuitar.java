package com.guitar;

import lombok.Data;

@Data
public class ElectricGuitar {
	private String brand = "YAMAHA";
	private int numOfPickups;
	private boolean rockStarUsesIt;
}