package com.nil.service;

import com.nil.dto.AccountDTO;
import com.nil.exception.MobileBankException;

public interface AccountService {

	public String createAccount(AccountDTO accountDTO) throws MobileBankException;
}
