package com.nil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.dto.BankAccountDTO;
import com.nil.dto.UserDTO;
import com.nil.entity.BankAccount;
import com.nil.entity.User;
import com.nil.exception.MobileBankException;
import com.nil.repository.BankAccountRepository;
import com.nil.repository.UserRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<BankAccountDTO> listAccounts(Long mobileNo) throws MobileBankException {
		Optional<User> x = userRepository.findById(mobileNo);
		x.orElseThrow(() -> new MobileBankException("Service.MOBILE_NUMBER_NOT_AVALIABLE"));
		Iterable<BankAccount> list = bankAccountRepository.findAll();
		List<BankAccountDTO> bankAccountDTOs = new ArrayList<>();
		for(BankAccount bankAccount : list) {
			if(bankAccount.getUser().getMobileNumber().equals(mobileNo)) {
				BankAccountDTO bankAccountDTO = new BankAccountDTO();
				bankAccountDTO.setAccountNumber(bankAccount.getAccountNumber());
				bankAccountDTO.setAccountType(bankAccount.getAccountType());
				bankAccountDTO.setBalance(bankAccount.getBalance());
				bankAccountDTO.setBankName(bankAccount.getBankName());
				bankAccountDTO.setIfscCode(bankAccount.getIfscCode());
				bankAccountDTO.setOpeningDate(bankAccount.getOpeningDate());
				UserDTO userDTO = new UserDTO();
				userDTO.setAccountHolderName(bankAccount.getUser().getAccountHolderName());
				userDTO.setCommunicationAddress(bankAccount.getUser().getCommunicationAddress());
				userDTO.setDateOfBirth(bankAccount.getUser().getDateOfBirth());
				userDTO.setEmail(bankAccount.getUser().getEmail());
				userDTO.setGender(bankAccount.getUser().getGender());
				userDTO.setMobileNumber(bankAccount.getUser().getMobileNumber());
				userDTO.setPAN(bankAccount.getUser().getPAN());
				userDTO.setPassword(bankAccount.getUser().getPassword());
				userDTO.setUserId(bankAccount.getUser().getUserId());
				bankAccountDTO.setUser(userDTO);
				bankAccountDTOs.add(bankAccountDTO);
			}
		}
		return bankAccountDTOs;
	}
	
	
}
