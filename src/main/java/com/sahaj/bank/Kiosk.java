package com.sahaj.bank;

import com.sahaj.bank.commands.CommandExecutor;
import com.sahaj.bank.commands.CommandExecutorFactory;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.exception.TransactionLimitExceedsException;
import com.sahaj.bank.models.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Kiosk {
	CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory();
	public void start(String filename) throws IOException {
		String path = new File("").getAbsolutePath();
		File file = new File(path + File.separator + filename);
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(file));
		String input;
		while ((input = reader.readLine()) != null) {
			try {
				Command command = new Command(input);
				processCommand(command);
			} catch (InvalidCommandException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	void processCommand(Command command) {
		try {
			CommandExecutor executor = commandExecutorFactory.getCommandExecutor(command);
			if(executor.isValid(command)) {
				executor.execute(command);
			}
		} catch (InvalidCommandException | TransactionLimitExceedsException e) {
			System.out.println(e.getMessage());
		}

	}
}
