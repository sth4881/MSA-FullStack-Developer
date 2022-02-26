package com.jdbc;

import java.util.ArrayList;

public class MemberTest3 {
public static void main(String[] args) {
		
		MemberDAO3 dao = new MemberDAO3();
		String _name = "Jennifer";
		int _age = 20;

		MemberDTO dto = new MemberDTO(_name, _age);
		dao.modMember(dto);

		ArrayList<MemberDTO> list = dao.list(dto);
		
		for (int i = 0; i < list.size(); i++) {
			MemberDTO data = list.get(i);
			String id = data.getId();
			String name = data.getName();
			int height = data.getHeight();
			int weight = data.getWeight();
			int age = data.getAge();

			System.out.println("아이디는>>"+id+
					   ", 이름은>>"+name+
					   ", 키는>>"+height+
					   ", 체중은>>"+weight+
					   ", 나이는>>"+age);		
		}
	}
}
