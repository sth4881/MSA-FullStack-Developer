package com.generic.types;

public class PairTest {
	public static void main(String[] args) {
		// p1�� �μ��� String, Integer�� ��� ������ �� �߿� �ϳ��� �ٸ� Ÿ������ �ָ� ���� �߻�
		Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
		Pair<String, String>  p2 = new OrderedPair<String, String>("hello", "world");
	}
}
