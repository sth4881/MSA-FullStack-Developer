package com.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class JdbcConnection {
	public static void main(String args[]) throws SQLException {

		OracleDataSource ods = new OracleDataSource();

		/* Thin driver */

		//  [1] Thin driver�� localhost:1521/xepdb1���� �����Ѵ�.
		ods.setURL("jdbc:oracle:thin:@localhost:1521/xepdb1");
		ods.setUser("ace");
		ods.setPassword("me");
		Connection conn1 = ods.getConnection();

		DatabaseMetaData meta = conn1.getMetaData();
		System.out.println("JDBC driver version is " + meta.getDriverVersion());

		// [2] [1]�� 4��¥�� ������ 2��¥���� �ٿ��ٴ� �� �ܿ��� ���̰� ����
		ods.setURL("jdbc:oracle:thin:ace/me@localhost:1521/xepdb1");
		Connection conn2 = ods.getConnection();
		System.out.println("Thin Driver [2] ���");

		/* Oracle Call Interface (OCI) driver */

		// [1]
		ods.setURL("jdbc:oracle:oci8:@mydb");
		ods.setUser("ace");
		ods.setPassword("me");
		Connection conn3 = ods.getConnection();
		System.out.println("OCR Driver [1] ���");

		// [2]
		ods.setURL("jdbc:oracle:oci8:ace/me@mydb");
		Connection conn4 = ods.getConnection();
		System.out.println("OCR Driver [2] ���");

		/* ���� ���� + �̱��� ���� Ȱ�� ���� */
//		Connection conn5 = DBConnection.getConnection();
	}

}