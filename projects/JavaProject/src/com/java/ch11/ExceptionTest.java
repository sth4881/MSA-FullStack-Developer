package com.java.ch11;

public class ExceptionTest {
	static void checkAge(int age) throws ArithmeticException {
		if(age<18) {
			throw new ArithmeticException("Access denied - You must be at least 18");
		} else {
			System.out.println("Access gratnted - You are old enough!");
		}
	}
	public static void main(String[] args) {
		/*
		 * checkAge(15); // 예외처리를 해주지 않아서 오류 발생
		 */
		try {
			checkAge(15);
		} catch(ArithmeticException e) {
			// checkAge에서 정의한 조건문 내용 출력
			System.out.println(e.getMessage());
			// 발생하는 오류 내용 출력
			e.printStackTrace();
		}
	}
}
