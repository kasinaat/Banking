package com.sahaj.bank.models;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
	public Integer transactionId;
	public static final Integer TRANSACTION_LIMIT = 3;
	private static final AtomicInteger count = new AtomicInteger(0);

	public TransactionType transactionType;
	public Long targetAccount;
	public Long sourceAccount;
	public Date transactionTime;
	public Integer transactionAmount;

	public Transaction(TransactionType transactionType, Long sourceAccount) {
		this.transactionId = count.incrementAndGet();
		this.transactionTime = new Date();
		this.transactionType = transactionType;
		this.sourceAccount = sourceAccount;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public Long getSourceAccount() {
		return sourceAccount;
	}

	public Long getTargetAccount() {
		return targetAccount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionAmount(Integer transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
}
