package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO1 {
	private Connection conn = DBConnection.getConnection();
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberDTO> list() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			String query = "select * from member";
			System.out.println(query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				int age = rs.getInt("age");

				MemberDTO data = new MemberDTO();

				data.setId(id);
				data.setName(name);
				data.setHeight(height);
				data.setWeight(weight);
				data.setAge(age);

				list.add(data);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}