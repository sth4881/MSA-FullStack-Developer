package net.developia.spring01.jdbc3;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DeptDAO {
	// 매개변수를 begin, end를 통해서 넘겨줄 수 있다.
	public List<DeptDTO> listDept(
		@Param("begin") int begin, @Param("end") int end) throws Exception;

//	public int insertDept(DeptDTO dto) throws Exception;
//	public int deleteDept(@Param("deptno") int deptno) throws Exception;
	public int updateDept(DeptDTO dto) throws Exception;
	public int countDept();

	public List<EmpDeptDTO> listEmployees(
		@Param("department_id") int department_id);
}
