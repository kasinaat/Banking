package com.sahaj.bank.commands;

import com.sahaj.bank.data.Database;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.exception.TransactionLimitExceedsException;
import com.sahaj.bank.models.BankAccount;
import com.sahaj.bank.models.Command;

import java.util.List;

public class BalanceCommand implements CommandExecutor{
	public static final String COMMAND_NAME = "balance";

	@Override
	public void execute(Command command) throws InvalidCommandException, TransactionLimitExceedsException {
		List<String> params = command.getParams();
		Long accountNumber = Long.parseLong(params.get(0));
		Database database = Database.getInstance();
		if(database.hasAccount(accountNumber)) {
			BankAccount account = database.getAccount(accountNumber);
			System.out.println(account.getFundsBalance());
		} else {
			throw new InvalidCommandException("Account doesn't exists");
		}
	}

	@Override
	public boolean isValid(Command command) throws InvalidCommandException {
		List<String> params = command.getParams();
		if (params.size() != 1) {
			throw new InvalidCommandException();
		}
		try {
			Long accountNumber = Long.parseLong(params.get(0));
		} catch (NumberFormatException e) {
			throw new InvalidCommandException();
		}
		return true;
	}
}
