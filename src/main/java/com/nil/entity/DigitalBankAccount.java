package com.nil.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class DigitalBankAccount {

	@Id
	@Column(name = "digital_banking_id")
	private String digitalBankingId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mobile_number")
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number", unique= true)
	private BankAccount bankAccount;
	private String accountType;
	public String getDigitalBankingId() {
		return digitalBankingId;
	}
	public void setDigitalBankingId(String digitalBankingId) {
		this.digitalBankingId = digitalBankingId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
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
		return "DigitalBankAccount [digitalBankingId=" + digitalBankingId + ", user=" + user + ", bankAccount="
				+ bankAccount + ", accountType=" + accountType + "]";
	}
	
	
}
