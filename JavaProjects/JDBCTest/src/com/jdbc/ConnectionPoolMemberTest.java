package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolMemberTest {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private static final String user = "ace";
	private static final String pwd = "me";
	private static final int initialCons = 5;
	private static final int maxCons = 20;
	private static final boolean block = true;
	private static final long timeout = 10000;

	public static void main(String[] args) {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		ConnectionPool cp;

		try {
			Class.forName(driver);
			System.out.println("Oracle �뱶�씪�씠踰� 濡쒕뵫 �꽦怨�");

			// 而ㅻ꽖�뀡 �� �깮�꽦
			cp = new ConnectionPool(url, user, pwd, initialCons, maxCons, block, timeout);
			System.out.println("ConnectionPool �깮�꽦...");

			// 而ㅻ꽖�뀡 ��濡쒕��꽣 �븯�굹�쓽 而ㅻ꽖�뀡 媛��졇�샂
			conn = cp.getConnection();

			stmt = conn.createStatement();
			System.out.println("Statement �깮�꽦 �꽦怨�");

			String query = "INSERT INTO t1 VALUES (50, 'IT', 'SEOUL')";
			System.out.println(query);
			stmt.executeUpdate(query);

			String query2 = "SELECT * FROM t1";
			System.out.println(query2);

			rs = stmt.executeQuery(query2);

			while (rs.next()) {
				System.out.print("遺��꽌踰덊샇>>" + rs.getInt("deptno"));
				System.out.print("遺��꽌�씠由�>>" + rs.getString("dname"));
				System.out.print("吏��뿭>>" + rs.getString("loc"));				
			}

			rs.close();
			stmt.close();

			// Connection�쓣 �떕�쑝硫� �븞�맖
			// conn.close();
			// 而ㅻ꽖�뀡 ���뿉寃� �궗�슜�븳 而ㅻ꽖�뀡�쓣 �룎�젮以�
			cp.releaseConnection(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
