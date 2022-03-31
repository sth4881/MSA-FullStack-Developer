package net.developia.spring01.jdbc2;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcTest {
	public static void main(String[] args) {
		GenericApplicationContext context = new GenericXmlApplicationContext(
				JdbcTest.class, "beanInit.xml");
		DeptController controller = (DeptController) context.getBean("controller");
		//controller.listDept();
		//controller.insertDept();
		//controller.deleteDept(50);
		//controller.updateDept();
		//controller.countDept();
		controller.listEmployees();
	}	
}
