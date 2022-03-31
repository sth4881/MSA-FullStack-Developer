package net.developia.spring01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {
	// 싱글턴 패턴을 위해서 자기자신의 인스턴스를 생성
	private static final JdbcTemplate jdbcTemplate = new JdbcTemplate();
	public static JdbcTemplate getInstance() {
		return jdbcTemplate;
	}
	private JdbcTemplate() {
		try {
			Class.forName("net.sf.log4jdbc.DriverSpy");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public int update(String sql, Object[] args) throws SQLException {
		try(Connection conn = DriverManager.getConnection(
			"jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1", "ace", "me");
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			for(int i=0; i<args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			System.out.println(pstmt.executeUpdate());
			return pstmt.executeUpdate(); // 결과가 반영된 레코드의 개수 반환
		}
	}
}
