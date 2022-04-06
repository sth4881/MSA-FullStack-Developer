package net.developia.spring04.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // AOP를 동작시키기 위한 어노테이션
@Log4j
@Component // 관점 지향 프로그래밍을 위해서 인터럽트를 거는 부가적인 코드로 사용될 빈
public class LogAdvice {
	// 클래스를 수행하기 전에 반드시 코드를 수행하도록 함
	@Before(value="execution(* net.developia.spring04.service.SampleService*.*(..))") // '*'는 '모든'을 의미함, '..'은 모든 인수를 의미함 
	public void logBefore() {
		log.info("------------------------ : logBefore()");
	}
	
	@Before(value="execution(* net.developia.spring04.service.SampleService*.doAdd(String, String)) &&"
			+ "args(str1, str2)") // '*'는 '모든'을 의미함, '..'은 모든 인수를 의미함 
	public void logBeforeWithParam(String str1, String str2) {
		log.info("------------------------ : logBeforeWithParam()");
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	// Target에서 예외가 발생했을 때 동작한다.
	@AfterThrowing(pointcut="execution(* net.developia.spring04.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception~~!!!!");
		log.info("exception : " + exception);
	}
	
	// BeforeAdvice + AfterAdvice
	@Around("execution(* net.developia.spring04.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		log.info("Target : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		log.info("time : " + (end - start)/1000 + "sec");
		return result;
	}
}
