package com.sahaj.bank.commands;

import com.sahaj.bank.data.Database;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.models.BankAccount;
import com.sahaj.bank.models.Command;

import java.util.List;

public class CreateAccountCommand implements CommandExecutor {
	public static final String COMMAND_NAME = "create";

	@Override
	public void execute(Command command) {
		String name = command.getParams().get(0);
		Database database = Database.getInstance();
		BankAccount newAccount = new BankAccount(database.getNewAccountNumber(), name);
		database.addAccount(newAccount);
		System.out.println(newAccount.getAccountNumber());
	}

	@Override
	public boolean isValid(Command command) throws InvalidCommandException {
		List<String> params = command.getParams();
		if (params.size() != 1) {
			throw new InvalidCommandException();
		}
		return true;
	}
}
