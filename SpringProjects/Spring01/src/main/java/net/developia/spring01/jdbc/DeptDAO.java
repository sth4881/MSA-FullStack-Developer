package net.developia.spring01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO {
	public String testStr() {
		return "msDAO test~";
	}
	
	public List<DeptDTO> listDept() throws Exception {
		List<DeptDTO> list = new ArrayList<>();
		
		String sql = "SELECT deptno, dname, loc FROM dept";

		Class.forName("net.sf.log4jdbc.DriverSpy");
		try(Connection conn = DriverManager.getConnection(
		"jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1", "ace", "me");
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					DeptDTO dto = new DeptDTO();
					dto.setDeptno(rs.getInt("deptno"));
					dto.setDname(rs.getString("dname"));
					dto.setLoc(rs.getString("loc"));
					list.add(dto);
				}
			}
		}
		return list;
	}
	
	public int insertDept(DeptDTO dto) throws Exception {
		String sql = "INSERT INTO DEPT(deptno, dname, loc) VALUES(?, ?, ?)";
		
		Object[] args = {
			dto.getDeptno(),
			dto.getDname(),
			dto.getLoc()
		};
		
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		return jdbcTemplate.update(sql.toString(), args);
	}
	
	public int deleteDept(int deptno) throws SQLException {
		String sql = "DELETE FROM DEPT WHERE deptno = ?";
		
		Object[] args = {deptno};
		
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		return jdbcTemplate.update(sql.toString(), args);
	}

	public int updateDept(DeptDTO dto) throws Exception {
		String sql = "UPDATE DEPT SET dname = ?, loc =? WHERE deptno = ?";
		
		Object[] args = {
			dto.getDname(),
			dto.getLoc(),
			dto.getDeptno()
		};
		
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		return jdbcTemplate.update(sql.toString(), args);
	}
}
