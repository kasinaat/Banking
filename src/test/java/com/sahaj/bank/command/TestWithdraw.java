package com.sahaj.bank.command;

import com.sahaj.bank.commands.CreateAccountCommand;
import com.sahaj.bank.commands.DepositCommand;
import com.sahaj.bank.commands.WithdrawCommand;
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

public class TestWithdraw {
	private InputStream inputStream;
	private PrintStream printStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	Database database;
	WithdrawCommand withdrawCommand;
	@Before
	public void start() {
		inputStream = System.in;
		printStream = System.out;
		database = Database.getInstance();
		withdrawCommand = new WithdrawCommand();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void end() {
		System.setIn(inputStream);
		System.setOut(printStream);
	}
	@Test(expected = InvalidCommandException.class)
	public void testWithdrawLimit() throws InvalidCommandException {
		CreateAccountCommand createAccountCommand = new CreateAccountCommand();
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		String expected = "1001\r\n" +
				"Maximum withdrawal amount is 25000\r\n";
		withdrawCommand.isValid(new Command("Withdraw 1001 50000"));
		assertEquals(expected,outContent.toString());
	}

	@Test(expected = InvalidCommandException.class)
	public void testInvalidParams() throws InvalidCommandException {
		CreateAccountCommand createAccountCommand = new CreateAccountCommand();
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		withdrawCommand.isValid(new Command("With Amount"));
	}
}
