package com.nil.utility;

import java.time.LocalDateTime;

public class ErrorInfo {

	private int errorCode;
	private String errorMessage;
	private LocalDateTime errorTimeStamp;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getErrorTimeStamp() {
		return errorTimeStamp;
	}
	public void setErrorTimeStamp(LocalDateTime errorTimeStamp) {
		this.errorTimeStamp = errorTimeStamp;
	}
	
	
}
