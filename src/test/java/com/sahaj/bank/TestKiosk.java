package com.sahaj.bank;

import com.sahaj.bank.commands.CreateAccountCommand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

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
		new Kiosk().start("testcases\\testcase1.txt");
		Assert.assertEquals(readFileAsString("testOutputs\\output1.txt"), outContent.toString());
	}

	private String readFileAsString(String filename) throws IOException {
		String path = new File("").getAbsolutePath();
		File file = new File(path + File.separator + filename);
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(
				new FileReader(file));
		char[] buf = new char[1024];
		int numRead=0;
		while((numRead=reader.read(buf)) != -1){
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}
}
