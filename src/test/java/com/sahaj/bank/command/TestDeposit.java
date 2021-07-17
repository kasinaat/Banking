package com.sahaj.bank.command;

import com.sahaj.bank.commands.CreateAccountCommand;
import com.sahaj.bank.commands.DepositCommand;
import com.sahaj.bank.data.Database;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class TestDeposit {
	private InputStream inputStream;
	private PrintStream printStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	Database database;
	Long accountNumber;
	DepositCommand depositCommand;
	@Before
	public void start() throws InvalidCommandException {
		inputStream = System.in;
		printStream = System.out;
		database = Database.getInstance();
		depositCommand = new DepositCommand();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void end() {
		System.setIn(inputStream);
		System.setOut(printStream);
	}
	@Test
	public void testDepositLimit() throws InvalidCommandException {
		CreateAccountCommand createAccountCommand = new CreateAccountCommand();
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		String expected = "1001\r\n" +
				"Deposit Limit is 30000\r\n";
		depositCommand.isValid(new Command("Deposit 1001 30001"));
		assertEquals(expected,outContent.toString());
	}
	@Test(expected = InvalidCommandException.class)
	public void testDepositNegativeLimit() throws InvalidCommandException {
		CreateAccountCommand createAccountCommand = new CreateAccountCommand();
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		String expected = "1001\r\n" +
				"Enter Valid deposit amount\r\n";
		depositCommand.isValid(new Command("Deposit 1001 300011"));
	}
}
