package com.nil.dto;

public class LoginDTO {

	private Long mobileNumber;
	private String password;
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [mobileNumber=" + mobileNumber + ", password=" + password + "]";
	}
	
}
