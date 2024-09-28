package com.nil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.dto.AccountDTO;
import com.nil.entity.BankAccount;
import com.nil.entity.User;
import com.nil.exception.MobileBankException;
import com.nil.repository.BankAccountRepository;
import com.nil.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public String createAccount(AccountDTO accountDTO) throws MobileBankException {
		Optional<com.nil.entity.User> list = userRepository.findByUserId(accountDTO.getUser().getUserId());
		User user = list.orElseThrow(() -> new MobileBankException("Service.USER_ID_NOT_EXIST"));
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(accountDTO.getAccountNumber());
		bankAccount.setAccountType(accountDTO.getAccountType());
		bankAccount.setBalance(accountDTO.getBalance());
		bankAccount.setBankName(accountDTO.getBankName());
		bankAccount.setIfscCode(accountDTO.getIfscCode());
		bankAccount.setOpeningDate(accountDTO.getOpeningDate());
		bankAccount.setUser(user);
		bankAccountRepository.save(bankAccount);
		
		return "Account created successfully, with id: "+ bankAccount.getUser().getUserId();
	}

}
