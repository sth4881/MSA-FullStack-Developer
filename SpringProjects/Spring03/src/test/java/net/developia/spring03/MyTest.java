package net.developia.spring03;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dto.BoardDTO;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/*-context.xml"})
public class MyTest {
	@Before // 각 메소드가 실행되기 전에 실행할 메소드에 사용하는 어노테이션
	public void testBefore() {
		log.info("@Before : 각 메소드가 실행되기 전에 실행");
	}
	
	@After // 각 메소드가 실행된 후에 실행할 메소드에 사용하는 어노테이션
	public void testAfter() {
		log.info("@After : 각 메소드가 실행된 후에 실행");
	}
	
	@BeforeClass // 테스트를 수행하기 전에 오직 한번 실행하기 위해서 사용하는 어노테이션
	public static void testBeforeClass() {
		log.info("@BeforeClass : 테스트 시작 전 최초 1번만 실행");
	}
	
	@AfterClass // 테스트를 수행한 후에 오직 한번 실행하기 위해서 사용하는 어노테이션
	public static void testAfterClass() {
		log.info("@AfterClass : 테스트 종료 전 마지막에 1번 실행");
	}
	
	@Test // 해당 메소드가 JUnit 테스트를 진행함을 알려주는 어노테이션
	public void testBoardDTO() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(1);
		boardDTO.setTitle("제목");
		boardDTO.setName("이름");
		boardDTO.setPassword("1111");
		log.info(boardDTO.toString());
		assertNotNull(boardDTO);
	}
	
	// 실무 : 현업에서는 2초 안에 서비스가 동작해야함
	// 2초 안에 서비스가 동작하는지 테스트하는 코드
	@Test(timeout=2000)
	public void testTimeOut() {
		BoardDTO boardDTO = new BoardDTO();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boardDTO.setNo(1);
		boardDTO.setTitle("제목");
		boardDTO.setName("이름");
		boardDTO.setPassword("1111");
		log.info(boardDTO.toString());
		assertNotNull(boardDTO);
	}
	
	@Test
	@Ignore // 해당 메소드를 제외하고 JUnit 테스트를 진행하기 위한 어노테이션
	public void createBoardDTO() {
		log.info("실행하지 않음");
	}
	
	@Test
	public void testAdd() {
		TestCalculator tc = new TestCalculator();
		assertNotEquals(tc.add(33, 333), 667); // 테스트가 올바르게 동작하는지 true, false로 판단
	}
}
