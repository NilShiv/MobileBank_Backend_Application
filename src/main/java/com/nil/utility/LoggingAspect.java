package com.nil.utility;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@AfterThrowing(pointcut = "execution(* com.nil.service.*.(..))", throwing = "exception")
	public void logServiceException(Exception exception) throws Exception{
		LOGGER.error(exception.getMessage(), exception);
	}
}
