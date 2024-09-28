package com.nil.service;

import java.util.List;

import com.nil.dto.TransactionDTO;
import com.nil.exception.MobileBankException;

public interface TransactionService {

	public String fundTransfer(TransactionDTO transactionDTO) throws MobileBankException;
	public List<TransactionDTO> accountStatement(Long mobileNo) throws MobileBankException;
}
