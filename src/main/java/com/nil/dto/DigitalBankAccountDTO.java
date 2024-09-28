package com.nil.dto;

public class DigitalBankAccountDTO {

	private String digitalBankingId;
	private UserDTO user;
	private BankAccountDTO bankAccount;
	private String accountType;
	public String getDigitalBankingId() {
		return digitalBankingId;
	}
	public void setDigitalBankingId(String digitalBankingId) {
		this.digitalBankingId = digitalBankingId;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public BankAccountDTO getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccountDTO bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "DigitalBankAccountDTO [digitalBankingId=" + digitalBankingId + ", user=" + user + ", bankAccount="
				+ bankAccount + ", accountType=" + accountType + "]";
	}
	
	
}
