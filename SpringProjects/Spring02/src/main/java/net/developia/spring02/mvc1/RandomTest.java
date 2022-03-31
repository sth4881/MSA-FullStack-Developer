package net.developia.spring02.mvc1;

public class RandomTest {
	public static void main(String[] args) {
		// 4~15 숫자 생성
		int num = (int)(Math.random()*12) + 4;
		
		// 600, 700, 800, ... , 2300 숫자 생성
		int num2 = ((int)(Math.random() * 18) + 6) * 100;
	}
}
