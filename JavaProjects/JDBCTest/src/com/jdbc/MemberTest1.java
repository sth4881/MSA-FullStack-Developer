package com.jdbc;

import java.util.ArrayList;

public class MemberTest1 {
	public static void main(String[] args) {
		MemberDAO1 dao = new MemberDAO1();
		ArrayList<MemberDTO> list = dao.list();

		for (int i = 0; i < list.size(); i++) {
			MemberDTO data = list.get(i);
			String id = data.getId();
			String name = data.getName();
			int height = data.getHeight();
			int weight = data.getWeight();
			int age = data.getAge();

			System.out
					.println("아이디는>>" + id + " 이름은>>" + name + " 키는>>" + height + " 몸무게는>>" + weight + " 나이는>>" + age);
		}
	}
}