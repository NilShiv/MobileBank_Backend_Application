package com.nil.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	private Long mobileNumber;
	private String userId;
	private String accountHolderName;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private String email;
	private String communicationAddress;
	@Column(name = "p_a_n")
	private String PAN;
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCommunicationAddress() {
		return communicationAddress;
	}
	public void setCommunicationAddress(String communicationAddress) {
		this.communicationAddress = communicationAddress;
	}
	public String getPAN() {
		return PAN;
	}
	public void setPAN(String pAN) {
		PAN = pAN;
	}
	@Override
	public String toString() {
		return "User [mobileNumber=" + mobileNumber + ", userId=" + userId + ", accountHolderName=" + accountHolderName
				+ ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", password=" + password + ", email=" + email
				+ ", communicationAddress=" + communicationAddress + ", PAN=" + PAN + "]";
	}
	
	
}
