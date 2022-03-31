package net.developia.spring01.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String testStr() {
		return "DAO test~";
	}
	
	public List<DeptDTO> listDept() throws Exception {
		List<DeptDTO> list = new ArrayList<>();
		
		String sql = "SELECT deptno, dname, loc FROM dept WHERE deptno>=? and deptno<=? ORDER BY deptno";

		Object[] args = { 10, 40 };
		
		RowMapper rowMapper = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeptDTO dto = new DeptDTO();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				return dto;
			}
		};
		
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}
	
	public int insertDept(DeptDTO dto) throws Exception {
		String sql = "INSERT INTO DEPT(deptno, dname, loc) VALUES(?, ?, ?)";
		
		Object[] args = {
			dto.getDeptno(),
			dto.getDname(),
			dto.getLoc()
		};
		return jdbcTemplate.update(sql.toString(), args);
	}
	
	public int deleteDept(int deptno) throws SQLException {
		String sql = "DELETE FROM DEPT WHERE deptno = ?";
		
		Object[] args = {deptno};
		
		return jdbcTemplate.update(sql.toString(), args);
	}

	public int updateDept(DeptDTO dto) throws Exception {
		String sql = "UPDATE DEPT SET dname = ?, loc =? WHERE deptno = ?";
		
		Object[] args = {
			dto.getDname(),
			dto.getLoc(),
			dto.getDeptno()
		};
		
		return jdbcTemplate.update(sql.toString(), args);
	}

	public int countDept() {
		String sql = "SELECT COUNT(*) FROM DEPT";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<EmpDeptDTO> listEmployees() {
		String sql = "SELECT e.last_name, e.salary, e.job_id, d.department_name"
				+ " FROM employees e JOIN departments d ON e.department_id=d.department_id"
				+ " WHERE e.department_id=?";

		Object[] args = { 60 };
		
		RowMapper rowMapper = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmpDeptDTO dto = new EmpDeptDTO();
				dto.setLast_name(rs.getString("last_name"));
				dto.setSalary(rs.getInt("salary"));
				dto.setJob_id(rs.getString("job_id"));
				dto.setDepartment_name(rs.getString("department_name"));
				return dto;
			}
		};
		
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}
}
