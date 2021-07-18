package com.sahaj.bank.models;

public class BankAccount {
	private Integer fundsBalance = 0;
	private String holderName;
	private Long accountNumber = 0L;

	public BankAccount(Long accountNumber, String holderName) {
		this.holderName = holderName;
		this.accountNumber = accountNumber;
	}

	public void depositMoney(Integer amount) {
		this.fundsBalance += amount;
	}

	public void withdrawMoney(Integer amount) {
		this.fundsBalance -= amount;
	}

	public Integer getFundsBalance() {
		return this.fundsBalance;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

}
