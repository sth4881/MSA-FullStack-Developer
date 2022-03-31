package net.developia.spring01.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeptController {
	@Autowired
	private DeptService msService;
	
	public String testStr() {
		return msService.testStr();
	}

	public void listDept() {
		try {
			List<DeptDTO> list = msService.listDept();
			for(DeptDTO dto : list) {
				System.out.println(dto);
			}
		} catch(Exception e) {
			System.out.println("예외가 발생하였습니다.");
		}
	}
	
	public void insertDept() {
		DeptDTO dto = new DeptDTO();
		dto.setDeptno(70);
		dto.setDname("business");
		dto.setLoc("beijing");
		
		try {
			msService.insertDept(dto);
			System.out.println("입력 성공");
		} catch(Exception e) {
			System.out.println("입력 에러");
		}
	}
	
	public void deleteDept(int deptno) {
		DeptDTO dto = new DeptDTO();
		
		try {
			msService.deleteDept(deptno);
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
			msService.updateDept(dto);
			System.out.println("수정 성공");
		} catch(Exception e) {
			System.out.println("수정 에러");
		}
	}
}
