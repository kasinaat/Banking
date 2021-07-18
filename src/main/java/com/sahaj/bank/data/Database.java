package com.sahaj.bank.data;

import com.sahaj.bank.models.BankAccount;
import com.sahaj.bank.models.Transaction;
import com.sahaj.bank.models.TransactionType;

import java.util.*;

public class Database {
	private final Map<Long, BankAccount> bankAccounts;
	private final Map<Long, List<Transaction>> transactions;
	private static Database database = null;
	private static Long lastAccountNumber = 1000L;

	private Database(){
		this.transactions = new HashMap<>();
		this.bankAccounts = new HashMap<>();
	}

	public static Database getInstance() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}

	public Long getNewAccountNumber() {
		return ++lastAccountNumber;
	}

	public Map<Long, BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void addAccount(BankAccount newAccount) {
		getBankAccounts().put(newAccount.getAccountNumber(), newAccount);
		getTransactions().put(newAccount.getAccountNumber(), new ArrayList<>());
	}

	public Map<Long, List<Transaction>> getTransactions() {
		return transactions;
	}

	public BankAccount getAccount(Long accountNumber) {
		return bankAccounts.get(accountNumber);
	}

	public boolean hasAccount(Long accountNumber) {
		return bankAccounts.containsKey(accountNumber);
	}

	public void addTransaction(Transaction transaction) {
		transactions.get(transaction.sourceAccount).add(transaction);
	}

	public List<Transaction> getTransactionsByAccount(Long accountNumber, TransactionType transactionType) {
		List<Transaction> transactions = getTransactions().get(accountNumber);
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if(transaction.getTransactionType().equals(transactionType)) {
				result.add(transaction);
			}
		}
		return result;
	}

	public List<Transaction> getDepositsByAccount(Long accountNumber) {
		return getTransactionsByAccount(accountNumber, TransactionType.DEPOSIT);
	}

	public List<Transaction> getWithdrawalsByAccount(Long accountNumber) {
		return getTransactionsByAccount(accountNumber, TransactionType.WITHDRAW);
	}

	public List<Transaction> getTransactionsByAccount(Long accountNumber, TransactionType transactionType, Date date) {
		List<Transaction> transactions = (transactionType.equals(TransactionType.WITHDRAW) ? getWithdrawalsByAccount(accountNumber) : getDepositsByAccount(accountNumber));
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if(transaction.getTransactionTime().after(date)) {
				result.add(transaction);
			}
		}
		return result;
	}

	public List<Transaction> getDepositsFromStartOfDay(Long accountNumber) {
		return getTransactionsByAccount(accountNumber, TransactionType.DEPOSIT, getStartOfDay());
	}

	public List<Transaction> getWithdrawalsFromStartOfDay(Long accountNumber) {
		return getTransactionsByAccount(accountNumber, TransactionType.WITHDRAW, getStartOfDay());
	}

	public boolean isDepositLimitReached(Long accountNumber) {
		return getDepositsFromStartOfDay(accountNumber).size() == Transaction.TRANSACTION_LIMIT;
	}

	public boolean isWithdrawLimitReached(Long accountNumber) {
		return getWithdrawalsFromStartOfDay(accountNumber).size() == Transaction.TRANSACTION_LIMIT;
	}

	private Date getStartOfDay() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 0, 0, 0);
		return calendar.getTime();
	}

}
