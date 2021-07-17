package com.sahaj.bank.command;

import com.sahaj.bank.commands.CreateAccountCommand;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestCreateAccountCommand {
	private CreateAccountCommand createAccountCommand;
	private InputStream inputStream;
	private PrintStream printStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void start() {
		inputStream = System.in;
		printStream = System.out;
		System.setOut(new PrintStream(outContent));
		createAccountCommand = new CreateAccountCommand();
	}

	@After
	public void end() {
		System.setIn(inputStream);
		System.setOut(printStream);
	}

	@Test
	public void testValidCommand() throws InvalidCommandException {
		assertTrue(createAccountCommand.isValid(new Command("Create \"Kasinaat Sukesh\"")));
	}
	@Test(expected = InvalidCommandException.class)
	public void testInValidCommand() throws InvalidCommandException {
		createAccountCommand.isValid(new Command("Create"));
	}
	@Test
	public void testCreateAccount() throws InvalidCommandException {
		String expected = "1001\r\n" +
				"1002\r\n" +
				"1003\r\n" +
				"1004\r\n";
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		createAccountCommand.execute(new Command("Create \"Kasinaat Selvi Sukesh\""));
		assertEquals(expected, outContent.toString());
	}
}
