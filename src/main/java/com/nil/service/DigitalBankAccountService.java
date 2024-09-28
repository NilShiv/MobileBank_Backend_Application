package com.nil.service;

import com.nil.exception.MobileBankException;

public interface DigitalBankAccountService {

	public String linkAccount(Long mobileNo, Long accountNo) throws MobileBankException;
	
	public String linkAccount(Long mobileNo, Long accountNo, Integer OTP) throws MobileBankException;
	
	public Double checkBalance(Long mobileNo, Long accountNo) throws MobileBankException;
	
}
