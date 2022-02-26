package com.game;

import java.util.ArrayList;

public class DotCom {
	private String name;
	private ArrayList<String> locationCells;

	public void setLocationCells(ArrayList<String> loc) {
		locationCells = loc;
	}

	public String checkYourself(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);
		if (index >= 0) {
			locationCells.remove(index);
			if (locationCells.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}
		return result;
	}

	public String getName() {
		return name;
	}
	public void setName(String string) {
		name = string;
	}
}