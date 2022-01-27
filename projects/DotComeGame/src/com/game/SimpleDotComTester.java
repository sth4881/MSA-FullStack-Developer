package com.game;

import java.util.ArrayList;

public class SimpleDotComTester {
	public static void main(String[] args) {
		SimpleDotCom dot = new SimpleDotCom();
		ArrayList<String> locations  = new ArrayList<String>();
		locations.add("2"); locations.add("3"); locations.add("4");
		dot.setLocationCells(locations);
		String userGuess = "4";
		String result = dot.checkYourself(userGuess);
	}
}