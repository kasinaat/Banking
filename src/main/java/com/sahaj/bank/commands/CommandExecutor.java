package com.sahaj.bank.commands;

import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.exception.TransactionLimitExceedsException;
import com.sahaj.bank.models.Command;

public interface CommandExecutor {
	void execute(Command command) throws InvalidCommandException, TransactionLimitExceedsException;
	boolean isValid(Command command) throws InvalidCommandException;
}
