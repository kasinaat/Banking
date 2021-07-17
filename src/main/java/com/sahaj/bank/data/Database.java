package com.sahaj.bank.data;

import com.sahaj.bank.models.BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Database {
	private Map<Long, BankAccount> bankAccounts;
	private static Database database = null;
	private static Long LAST_ACCOUNT_NUMBER = 1000L;
	private Database(){
		bankAccounts = new HashMap<>();
	}
	public static Database getInstance() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}

	public Long getNewAccountNumber() {
		return ++LAST_ACCOUNT_NUMBER;
	}
	public Map<Long, BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void addAccount(BankAccount newAccount) {
		getBankAccounts().put(newAccount.getAccountNumber(), newAccount);
	}
	public BankAccount getAccount(Long accountNumber) {
		return bankAccounts.get(accountNumber);
	}

	public boolean hasAccount(Long accountNumber) {
		return bankAccounts.containsKey(accountNumber);
	}
}
