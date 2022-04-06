package net.developia.spring05.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Aspect
@Component
public class LogAdvice {
	@AfterThrowing(pointcut="execution(* net.developia.spring05.service.AutocompleteService*.*(..)", throwing="e")
	public void logException(Exception e) {
		log.info("Exception~!");
		log.info("Exception Type : " + e);
	}
}
