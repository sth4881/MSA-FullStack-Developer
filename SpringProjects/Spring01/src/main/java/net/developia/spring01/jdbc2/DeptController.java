package net.developia.spring01.jdbc2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller(value="controller")
public class DeptController {
	@Autowired
	private DeptService service;
	
	public String testStr() {
		return service.testStr();
	}

	public void listDept() {
		try {
			List<DeptDTO> list = service.listDept();
			for(DeptDTO dto : list) {
				System.out.println(dto);
			}
		} catch(Exception e) {
			System.out.println("예외가 발생하였습니다.");
		}
	}
	
	public void insertDept() {
		DeptDTO dto = new DeptDTO();
		dto.setDeptno(80);
		dto.setDname("business");
		dto.setLoc("beijing");
		
		try {
			service.insertDept(dto);
			System.out.println("입력 성공");
		} catch(Exception e) {
			System.out.println("입력 에러");
		}
	}
	
	public void deleteDept(int deptno) {
		DeptDTO dto = new DeptDTO();
		
		try {
			service.deleteDept(deptno);
			System.out.println("삭제 성공");
		} catch(Exception e) {
			System.out.println("삭제 에러");
		}
	}

	public void updateDept() {
		DeptDTO dto = new DeptDTO();
		dto.setDeptno(60);
		dto.setDname("money");
		dto.setLoc("las");
		
		try {
			service.updateDept(dto);
			System.out.println("수정 성공");
		} catch(Exception e) {
			System.out.println("수정 에러");
		}
	}

	public void countDept() {
		try {
			int count = service.countDept();
			System.out.println("부서의 개수 : " + count);
		} catch(Exception e) {
			System.out.println("예외가 발생하였습니다.");
		}
	}

	public void listEmployees() {
		try {
			List<EmpDeptDTO> list = service.listEmployees();
			for(EmpDeptDTO dto : list)
				System.out.println(dto);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
