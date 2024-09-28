package com.nil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.entity.BankAccount;
import com.nil.entity.DigitalBankAccount;
import com.nil.entity.User;
import com.nil.exception.MobileBankException;
import com.nil.repository.BankAccountRepository;
import com.nil.repository.DigitalBankAccountRepository;
import com.nil.repository.UserRepository;
import com.nil.utility.OTPUtility;

@Service
public class DigitalBankAccountServiceImpl implements DigitalBankAccountService {

	@Autowired
	DigitalBankAccountRepository digitalBankAccountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	OTPUtility otpUtility;
	
	@Override
	public String linkAccount(Long mobileNo, Long accountNo) throws MobileBankException {
		Optional<User> list = userRepository.findById(mobileNo);
		User user = list.orElseThrow(() -> new MobileBankException("Service.USER_NOT_FOUND"));
		Optional<DigitalBankAccount> list2 = digitalBankAccountRepository.findByAccountNumber(accountNo);
		if(list2.isPresent()) {
			throw new MobileBankException("Service.ACCOUNT_ALREADY_FOUND");
		}
		Optional<BankAccount> list3 = bankAccountRepository.findById(accountNo);
		if(list3.isEmpty()) {
			throw new MobileBankException("Service.INVALID_ACCOUNT_NUMBER");
		}
		List<BankAccount> list4 = bankAccountRepository.findByMobileNumber(mobileNo);
		if(list4.isEmpty()) {
			throw new MobileBankException("Service.NO_ACCOUNTS_FOUND");
		}
		Boolean flag = false;
		BankAccount bankAccount2 = new BankAccount();
		for(BankAccount bankAccount : list4) {
			if(bankAccount.getAccountNumber().equals(accountNo)) {
				bankAccount2=bankAccount;
				flag=true;
			}
		}
		if(flag==false) {
			throw new MobileBankException("Service.NO_MISMATCHED");
		}
		DigitalBankAccount digitalBankAccount = new DigitalBankAccount();
		String string = digitalBankAccountRepository.findLatestId();
		String s = string.substring(2, string.length());
		Integer integer = Integer.parseInt(s)+1;
		String s1 = integer.toString();
		String string2 = "W_"+s1;
		digitalBankAccount.setDigitalBankingId(string2);
		digitalBankAccount.setAccountType(bankAccount2.getAccountType());;
		digitalBankAccount.setUser(user);
		digitalBankAccountRepository.save(digitalBankAccount);
				
		return "Successfully link in digital bank account id : " + digitalBankAccount.getDigitalBankingId();
	}

	@Override
	public String linkAccount(Long mobileNo, Long accountNo, Integer OTP) throws MobileBankException {
		Optional<User> list = userRepository.findById(mobileNo);
		User user = list.orElseThrow(() -> new MobileBankException("Service.USER_NOT_FOUND"));
		Optional<DigitalBankAccount> list2 = digitalBankAccountRepository.findByAccountNumber(accountNo);
		if(list2.isPresent()) {
			throw new MobileBankException("Service.ACCOUNT_ALREADY_FOUND");
		}
		Optional<BankAccount> list3 = bankAccountRepository.findById(accountNo);
		if(list3.isEmpty()) {
			throw new MobileBankException("Service.INVALID_ACCOUNT_NUMBER");
		}
		List<BankAccount> list4 = bankAccountRepository.findByMobileNumber(mobileNo);
		if(list4.isEmpty()) {
			throw new MobileBankException("Service.NO_ACCOUNTS_FOUND");
		}
		Boolean flag = false;
		BankAccount bankAccount2 = new BankAccount();
		for(BankAccount bankAccount : list4) {
			if(bankAccount.getAccountNumber().equals(accountNo)) {
				bankAccount2=bankAccount;
				flag=true;
			}
		}
		if(!(otpUtility.sendOTP().equals(OTP))) {
			throw new MobileBankException("Service.OTP_INVALID");
		}
		if(flag==false) {
			throw new MobileBankException("Service.NO_MISMATCHED");
		}
		DigitalBankAccount digitalBankAccount = new DigitalBankAccount();
		String string = digitalBankAccountRepository.findLatestId();
		String s = string.substring(2, string.length());
		Integer integer = Integer.parseInt(s)+1;
		String s1 = integer.toString();
		String string2 = "W_"+s1;
		digitalBankAccount.setDigitalBankingId(string2);
		digitalBankAccount.setAccountType(bankAccount2.getAccountType());;
		digitalBankAccount.setUser(user);
		digitalBankAccountRepository.save(digitalBankAccount);
				
		return "Successfully link in digital bank account id : " + digitalBankAccount.getDigitalBankingId();
	}

	@Override
	public Double checkBalance(Long mobileNo, Long accountNo) throws MobileBankException {
		DigitalBankAccount digitalBankAccount = digitalBankAccountRepository.findByMobileNoAndAccountNo(mobileNo, accountNo);
		if(digitalBankAccount==null) {
			throw new MobileBankException("Service.NO_ACCOUNT_LINKED");
		}
		Optional<BankAccount> account = bankAccountRepository.findById(accountNo);
		account.orElseThrow(() -> new MobileBankException("Service.NO_ACCOUNT_FOUND"));
		return account.get().getBalance();
	}

}
