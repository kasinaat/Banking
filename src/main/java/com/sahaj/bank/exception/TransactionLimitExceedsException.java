package com.sahaj.bank.exception;

public class TransactionLimitExceedsException extends Exception{
	public TransactionLimitExceedsException() {
		super("Transaction Limit of day exceeded");
	}

	public TransactionLimitExceedsException(String message) {
		super(message);
	}
}
