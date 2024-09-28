package com.nil.service;

import java.util.List;

import com.nil.dto.BankAccountDTO;
import com.nil.exception.MobileBankException;

public interface BankAccountService {

	public List<BankAccountDTO> listAccounts(Long mobileNo) throws MobileBankException;
}
