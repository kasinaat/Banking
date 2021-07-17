package com.sahaj.bank.commands;

import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.models.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
	private Map<String, CommandExecutor> commands = new HashMap<>();
	public CommandExecutorFactory() {
		commands.put(CreateAccountCommand.COMMAND_NAME, new CreateAccountCommand());
		commands.put(DepositCommand.COMMAND_NAME, new DepositCommand());
		commands.put(WithdrawCommand.COMMAND_NAME, new WithdrawCommand());
		commands.put(BalanceCommand.COMMAND_NAME, new BalanceCommand());
		commands.put(TransferCommand.COMMAND_NAME, new TransferCommand());
	}

	public CommandExecutor getCommandExecutor(Command command) throws InvalidCommandException {
		final CommandExecutor commandExecutor = commands.get(command.getCommandName());
		if (commandExecutor == null) {
			throw new InvalidCommandException();
		}
		return commandExecutor;
	}
}
