package com.nil.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nil.exception.MobileBankException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(MobileBankException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(MobileBankException ex){
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(environment.getProperty(ex.getMessage()));
		error.setErrorTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception ex){
		LOGGER.error(ex.getMessage(), ex);
		
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		error.setErrorTimeStamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
			
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorInfo> exceptionHandler2(Exception ex){
		LOGGER.error(ex.getMessage(), ex);
		
		String errorMsg = null;
		
		if(ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
			errorMsg = manve.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", "));
		}
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(errorMsg);
		error.setErrorTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}
}
