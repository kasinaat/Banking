package com.sahaj.bank;

import com.sahaj.bank.commands.CreateAccountCommand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TestKiosk {
	private InputStream inputStream;
	private PrintStream printStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void start() {
		inputStream = System.in;
		printStream = System.out;
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void end() {
		System.setIn(inputStream);
		System.setOut(printStream);
	}
	
	@Test
	public void testcase1() throws IOException {
		String expected = "1001\r\n" +
				"1002\r\n" +
				"500\r\n" +
				"1500\r\n" +
				"Minimum deposit amount is 500\r\n" +
				"Maximum deposit amount is 50000\r\n" +
				"11500\r\n" +
				"Only 3 deposits are allowed in a day\r\n" +
				"11500\r\n" +
				"Minimum withdrawal amount is 1000\r\n" +
				"Insufficient balance\r\n" +
				"10500\r\n" +
				"8600\r\n" +
				"7600\r\n" +
				"2600\r\n" +
				"Insufficient balance\r\n" +
				"0\r\n" +
				"2600\r\n" +
				"Minimum withdrawal amount is 1000 for account 1002\r\n" +
				"Maximum withdrawal amount is 25000 for account 1002\r\n";
		new Kiosk().start("testcases\\testcase1.txt");
		Assert.assertEquals(expected, outContent.toString());
	}
}
