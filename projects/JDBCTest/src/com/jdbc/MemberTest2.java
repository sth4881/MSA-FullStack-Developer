package com.jdbc;

import java.util.ArrayList;

public class MemberTest2 {
	public static void main(String[] args) {
		MemberDAO2 dao = new MemberDAO2();
		String _name = "Peter";
		int _age = 24;

		MemberDTO dto = new MemberDTO(_name, _age);
		ArrayList<MemberDTO> list = dao.list(dto);

		for (int i = 0; i < list.size(); i++) {
			MemberDTO data = (MemberDTO) list.get(i);
			String id = data.getId();
			String name = data.getName();
			int height = data.getHeight();
			int weight = data.getWeight();
			int age = data.getAge();

			System.out.println("아이디는>>" + id + 
					           " 이름은>>" + name + 
					           " 키는>>" + height + 
					           " 몸무게는>>" + weight + 
					           " 나이는>>" + age);
		}
	}
}
