package com.sahaj.bank.models;

import com.sahaj.bank.commands.CreateAccountCommand;
import com.sahaj.bank.exception.InvalidCommandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

	private static final String SPACE = " ";
	private String commandName;
	private List<String> params;

	public String getCommandName() {
		return commandName;
	}

	public List<String> getParams() {
		return params;
	}

	public Command(final String inputLine) throws InvalidCommandException {
		Pattern pattern = Pattern.compile("^Create \"([^\"]*)\"");
		Matcher matcher = pattern.matcher(inputLine);
		if(matcher.matches()) {
			commandName = CreateAccountCommand.COMMAND_NAME;
			params = new ArrayList<>();
			params.add(matcher.group(1));
		} else {
			List<String> tokenList = new ArrayList<>();
			Collections.addAll(tokenList, inputLine.split(" "));
			if (tokenList.size() == 0) {
				throw new InvalidCommandException();
			}

			commandName = tokenList.get(0).toLowerCase();
			tokenList.remove(0);
			params = tokenList;
		}
	}

}