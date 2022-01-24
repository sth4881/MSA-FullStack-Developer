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
		 * checkAge(15); // ����ó���� ������ �ʾƼ� ���� �߻�
		 */
		try {
			checkAge(15);
		} catch(ArithmeticException e) {
			// checkAge���� ������ ���ǹ� ���� ���
			System.out.println(e.getMessage());
			// �߻��ϴ� ���� ���� ���
			e.printStackTrace();
		}
	}
}
