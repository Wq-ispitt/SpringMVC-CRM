package com.crm.spring.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	//pointcut declare for controller/service/dao, match any class/method/param
	@Pointcut("execution(* com.crm.spring.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.crm.spring.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.crm.spring.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	//before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===> in @Before: calling method: " + theMethod);
		//get the arguments and loop thru display
		Object [] args = theJoinPoint.getArgs();
		for (Object tempArg:args) {
			myLogger.info("====>> argument: " + tempArg);
		}
	}
	
	//afterReturn advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theRes"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theRes) {
		//display method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===> in @AfterReturning: from method: " + theMethod);
		//display data returned
		myLogger.info("====>> Result: " + theRes);
	}

}
