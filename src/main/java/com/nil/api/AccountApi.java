package com.nil.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nil.dto.AccountDTO;
import com.nil.dto.BankAccountDTO;
import com.nil.dto.TransactionDTO;
import com.nil.exception.MobileBankException;
import com.nil.service.AccountService;
import com.nil.service.BankAccountService;
import com.nil.service.DigitalBankAccountService;
import com.nil.service.TransactionService;

@RestController
@RequestMapping(value = "/account")
@Validated
public class AccountApi {
	
	@Autowired
	Environment environment;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired	
	DigitalBankAccountService digitalBankAccountService;
	
	@PostMapping(value = "/accounts")
	public ResponseEntity<String> createAccount(@RequestBody AccountDTO accountDTO) throws MobileBankException{
		String message = accountService.createAccount(accountDTO);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/accounts/{mobileNo}")
	public ResponseEntity<List<BankAccountDTO>> listAccounts(@PathVariable Long mobileNo) throws MobileBankException{
		List<BankAccountDTO> accountDTOs = bankAccountService.listAccounts(mobileNo);
		return new ResponseEntity<List<BankAccountDTO>>(accountDTOs, HttpStatus.OK);
				
	}
	@PostMapping(value = "/accounts/{mobileNo}")
	public ResponseEntity<String> LinkAccount(@RequestBody AccountDTO accountNo, @PathVariable Long mobileNo) throws MobileBankException{
		String message = digitalBankAccountService.linkAccount(mobileNo, accountNo.getAccountNumber());
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/accounts/{mobileNo}")
	public ResponseEntity<String> LinkAccount(@PathVariable Long mobileNo, @RequestBody AccountDTO accountNo) throws MobileBankException{
		String message = digitalBankAccountService.linkAccount(mobileNo, accountNo.getAccountNumber(), accountNo.getOTP());
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/accounts/balance/{mobileNo}")
	public ResponseEntity<String> cheakBalance(@PathVariable Long mobileNo, @RequestParam Long accountNo) throws MobileBankException{
		Double d = digitalBankAccountService.checkBalance(mobileNo, accountNo);
		String message = environment.getProperty("BALANCE_IS")+" : "+d;
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	@PutMapping(value = "/accounts/fundtransfer/")
	public ResponseEntity<String> fundTransfer(@RequestBody TransactionDTO transactionDTO) throws MobileBankException{
		String message = transactionService.fundTransfer(transactionDTO);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/accounts/statement/{mobileNo}")
	public ResponseEntity<List<TransactionDTO>> accountStatement(@PathVariable Long mobileNo) throws MobileBankException{
		List<TransactionDTO> transactionDTOs = transactionService.accountStatement(mobileNo);
		return new ResponseEntity<List<TransactionDTO>>(transactionDTOs, HttpStatus.CREATED);
	}

}
