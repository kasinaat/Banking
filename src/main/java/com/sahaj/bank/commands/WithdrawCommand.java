package com.sahaj.bank.commands;

import com.sahaj.bank.data.Database;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.exception.TransactionLimitExceedsException;
import com.sahaj.bank.models.*;

import java.util.List;

public class WithdrawCommand implements CommandExecutor{
	public static final String COMMAND_NAME = "withdraw";
	List<String> params;
	public Long accountNumber;
	public Integer amount;

	@Override
	public void execute(Command command) throws InvalidCommandException, TransactionLimitExceedsException {
		Database database = Database.getInstance();
		if(database.hasAccount(accountNumber)) {
			BankAccount account = database.getAccount(accountNumber);
			Transaction transaction = new Transaction(TransactionType.WITHDRAW, accountNumber);
			if(database.isWithdrawLimitReached(accountNumber)) {
				throw new TransactionLimitExceedsException("Only 3 withdrawals are allowed in a day");
			}
			if((account.getFundsBalance() - amount) > 0) {
				account.withdrawMoney(amount);
				transaction.setTransactionAmount(amount);
				database.addTransaction(transaction);
				System.out.println(account.getFundsBalance());
			} else {
				throw new InvalidCommandException("Insufficient balance");
			}
		} else {
			throw new InvalidCommandException("Account doesn't exists");
		}
	}

	@Override
	public boolean isValid(Command command) throws InvalidCommandException {
		params = command.getParams();
		if (params.size() != 2) {
			throw new InvalidCommandException();
		}
		try {
			accountNumber = Long.parseLong(params.get(0));
			amount = Integer.parseInt(params.get(1));
			if(amount > 25000) {
				throw new InvalidCommandException("Maximum withdrawal amount is 25000");
			} else if(amount < 1000) {
				throw new InvalidCommandException("Minimum withdrawal amount is 1000");
			}
		} catch (NumberFormatException e) {
			throw new InvalidCommandException();
		}
		return true;
	}

}
