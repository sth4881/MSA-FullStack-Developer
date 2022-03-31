package net.developia.spring01.jdbc;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcTest {
	public static void main(String[] args) {
		GenericApplicationContext context = new GenericXmlApplicationContext(JdbcTest.class, "beanInit.xml");
		DeptController ms = (DeptController) context.getBean("msController");
		//ms.listDept();
		//ms.insertDept();
		//ms.deleteDept(50);
		ms.updateDept();
	}	
}
