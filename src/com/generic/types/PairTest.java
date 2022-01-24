package com.generic.types;

public class PairTest {
	public static void main(String[] args) {
		// p1은 인수를 String, Integer로 줬기 때문에 둘 중에 하나라도 다른 타입으로 주면 오류 발생
		Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
		Pair<String, String>  p2 = new OrderedPair<String, String>("hello", "world");
	}
}
