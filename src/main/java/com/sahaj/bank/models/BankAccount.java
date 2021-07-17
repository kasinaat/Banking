package com.sahaj.bank.models;

public class BankAccount {
	private Integer fundsBalance = 0;
	private Integer deposits = 0;
	private Integer withdrawals = 0;
	private String holderName;
	private Long accountNumber = 0L;

	public BankAccount(Long accountNumber, String holderName) {
		this.holderName = holderName;
		this.accountNumber = accountNumber;
	}

	public void depositMoney(Integer amount) {
		this.fundsBalance += amount;
		this.deposits += 1;
	}
	public void resetTransactions() {
		this.deposits = 0;
		this.withdrawals = 0;
	}
	public void withdrawMoney(Integer amount) {
		this.fundsBalance -= amount;
		this.withdrawals += 1;
	}
	public Integer getFundsBalance() {
		return this.fundsBalance;
	}

	public Integer getDeposits() {
		return deposits;
	}

	public Integer getWithdrawals() {
		return withdrawals;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public boolean isDepositLimitReached() {
		return getDeposits() < 3;
	}
	public boolean isWithdrawLimitReached() {
		return getWithdrawals() < 3;
	}
}
