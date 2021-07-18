package com.sahaj.bank.commands;

import com.sahaj.bank.data.Database;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.exception.TransactionLimitExceedsException;
import com.sahaj.bank.models.*;

import java.util.List;

public class DepositCommand implements CommandExecutor{
	public static final String COMMAND_NAME = "deposit";
	List<String> params;
	public Long accountNumber;
	public Integer amount;

	@Override
	public void execute(Command command) throws InvalidCommandException, TransactionLimitExceedsException {
		Database database = Database.getInstance();
		if(database.hasAccount(accountNumber)) {
			BankAccount account = database.getAccount(accountNumber);
			if(database.isDepositLimitReached(accountNumber)) {
				throw new TransactionLimitExceedsException("Only 3 deposits are allowed in a day");
			}
			Transaction transaction = new Transaction(TransactionType.DEPOSIT, accountNumber);
			if((account.getFundsBalance() + amount) < 100000) {
				account.depositMoney(amount);
				transaction.setTransactionAmount(amount);
				database.addTransaction(transaction);
				System.out.println(account.getFundsBalance());
			} else {
				throw new InvalidCommandException("Maximum account balance limit is Rs. 1,00,000");
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
			if(amount > 50000) {
				throw new InvalidCommandException("Maximum deposit amount is 50000");
			} else if(amount < 500) {
				throw new InvalidCommandException("Minimum deposit amount is 500");
			}
		} catch (NumberFormatException e) {
			throw new InvalidCommandException();
		}
		return true;
	}

}
