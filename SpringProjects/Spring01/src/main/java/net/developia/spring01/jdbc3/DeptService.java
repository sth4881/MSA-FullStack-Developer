package net.developia.spring01.jdbc3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeptService {
	@Autowired
	private DeptDAO dao;

	public List<DeptDTO> listDept() throws Exception {
		try {
			List<DeptDTO> list = dao.listDept(1, 1000);
			if(list==null) throw new RuntimeException("데이터가 없습니다.");
			return list;
		} catch(Exception e) {
			log.info("Error : " + e);
			throw e;
		}
	}
	
	/*
	public void insertDept(DeptDTO dto) throws Exception {
		try {
			dao.insertDept(dto);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public void deleteDept(int deptno) throws Exception {
		try {
			if(dao.deleteDept(deptno) != 1) throw new RuntimeException("삭제된 부서가 없습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	*/

	public void updateDept(DeptDTO dto) throws Exception {
		try {
			if(dao.updateDept(dto) != 1) throw new RuntimeException("수정된 부서가 없습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public int countDept() throws Exception {
		try {
			int count = dao.countDept();
			return count;
		} catch(Exception e) {
			log.info("Error : " + e);
			throw e;
		}
	}

	public List<EmpDeptDTO> listEmployees() throws Exception {
		try {
			List<EmpDeptDTO> list = dao.listEmployees(90);
			if(list==null) throw new RuntimeException("데이터가 없습니다.");
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
