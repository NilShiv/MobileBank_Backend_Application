package com.nil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.dto.TransactionDTO;
import com.nil.entity.BankAccount;
import com.nil.entity.Transaction;
import com.nil.exception.MobileBankException;
import com.nil.repository.BankAccountRepository;
import com.nil.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Override
	public String fundTransfer(TransactionDTO transactionDTO) throws MobileBankException {
		Optional<BankAccount> list = bankAccountRepository.findById(transactionDTO.getSenderAccountNumber());
		if(transactionDTO.getReceiverAccountNumber().equals(transactionDTO.getSenderAccountNumber())) {
			throw new MobileBankException("Service.ACCOUNTS_ARE_SAME");
		}
		BankAccount senderBankAccount = list.orElseThrow(() -> new MobileBankException("Service.SENDER_ACCOUNT_INVALID"));
		if(!(senderBankAccount.getUser().getMobileNumber().equals(transactionDTO.getPaidForm()))){
			throw new MobileBankException("Server.SENDER_MISMATCH");
		}
		Optional<BankAccount> list2 = bankAccountRepository.findById(transactionDTO.getReceiverAccountNumber());
		BankAccount receverBankAccount = list2.orElseThrow(() -> new MobileBankException("Service.RECEVER_ACCOUNT_INVALID"));
		if(!(receverBankAccount.getUser().getMobileNumber().equals(transactionDTO.getPaidTo()))) {
			throw new MobileBankException("Service.RECEVER_MISMATCH");
		}if(transactionDTO.getAmount() > senderBankAccount.getBalance()) {
			throw new MobileBankException("Service.INSUFFICIENT_FUNDS");
		}
		senderBankAccount.setBalance(senderBankAccount.getBalance() - transactionDTO.getAmount());
		receverBankAccount.setBalance(receverBankAccount.getBalance()+transactionDTO.getAmount());
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setModeOfTransaction(transactionDTO.getModeOfTransaction());
		transaction.setPaidForm(transactionDTO.getPaidForm());
		transaction.setPaidTo(transactionDTO.getPaidTo());
		transaction.setReceiverAccountNumber(transactionDTO.getReceiverAccountNumber());
		transaction.setRemarks(transactionDTO.getRemarks());
		transaction.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
		transaction.setTransactionDateTime(transactionDTO.getTransactionDateTime());
		transaction.setTransactionId(transactionDTO.getTransactionId());
		transactionRepository.save(transaction);
		transactionDTO.setTransactionId(transaction.getTransactionId());
		return "Transaction detais successfully added with amount : " + transaction.getTransactionId();
	}

	@Override
	public List<TransactionDTO> accountStatement(Long mobileNo) throws MobileBankException {
		List<Transaction> list = transactionRepository.findByPaidFrom(mobileNo);
		if(list.isEmpty()) {
			throw new MobileBankException("Service.NO_ACTIVE_TRANSATION");
		}
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		for(Transaction transaction : list) {
			TransactionDTO transactionDTO = new TransactionDTO();
			transactionDTO.setAmount(transaction.getAmount());
			transactionDTO.setModeOfTransaction(transaction.getModeOfTransaction());
			transactionDTO.setPaidForm(transaction.getPaidForm());
			transactionDTO.setPaidTo(transaction.getPaidTo());
			transactionDTO.setReceiverAccountNumber(transaction.getReceiverAccountNumber());
			transactionDTO.setRemarks(transaction.getRemarks());
			transactionDTO.setSenderAccountNumber(transaction.getSenderAccountNumber());
			transactionDTO.setTransactionDateTime(transaction.getTransactionDateTime());
			transactionDTO.setTransactionId(transaction.getTransactionId());
			transactionDTOs.add(transactionDTO);
			
		}
		return transactionDTOs;
	}

}
