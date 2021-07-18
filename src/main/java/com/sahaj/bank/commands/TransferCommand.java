package com.sahaj.bank.commands;

import com.sahaj.bank.data.Database;
import com.sahaj.bank.exception.InvalidCommandException;
import com.sahaj.bank.exception.TransactionLimitExceedsException;
import com.sahaj.bank.models.*;

import java.util.List;

public class TransferCommand implements CommandExecutor{
	public static final String COMMAND_NAME = "transfer";
	Long sourceAccountNumber;
	Long targetAccountNumber;
	Integer amount;

	@Override
	public void execute(Command command) throws InvalidCommandException, TransactionLimitExceedsException {
		Database database = Database.getInstance();
		if(database.hasAccount(sourceAccountNumber) && database.hasAccount(targetAccountNumber)) {
			BankAccount sourceAccount = database.getAccount(sourceAccountNumber);
			BankAccount targetAccount = database.getAccount(targetAccountNumber);
			Transaction sourceTransaction = new Transaction(TransactionType.WITHDRAW, sourceAccountNumber);
			database.addTransaction(sourceTransaction);
			if(database.isWithdrawLimitReached(sourceAccountNumber)) {
				throw new TransactionLimitExceedsException("Only 3 withdrawals are allowed in a day for account " + sourceAccount.getAccountNumber());
			}
			if(database.isDepositLimitReached(targetAccountNumber)) {
				throw new TransactionLimitExceedsException("Only 3 deposits are allowed in a day for account " + targetAccount.getAccountNumber());
			}
			if (targetAccount.getFundsBalance() + amount > 100000) {
				throw new InvalidCommandException("Maximum account balance limit is Rs. 1,00,000");
			}

			if((sourceAccount.getFundsBalance() - amount) > 0) {
				sourceAccount.withdrawMoney(amount);
				targetAccount.depositMoney(amount);
				sourceTransaction.setTransactionAmount(amount);
				Transaction targetTransaction = new Transaction(TransactionType.DEPOSIT, targetAccountNumber);
				database.addTransaction(targetTransaction);
				targetTransaction.setTransactionAmount(amount);
				System.out.println("Successful");
			} else {
				throw new InvalidCommandException("Insufficient balance");
			}
		} else {
			throw new InvalidCommandException("Account doesn't exists");
		}
	}

	@Override
	public boolean isValid(Command command) throws InvalidCommandException {
		List<String> params = command.getParams();
		if (params.size() != 3) {
			throw new InvalidCommandException();
		}
		try {
			sourceAccountNumber = Long.parseLong(params.get(0));
			targetAccountNumber = Long.parseLong(params.get(1));
			amount = Integer.parseInt(params.get(2));
			if(amount > 25000) {
				throw new InvalidCommandException("Maximum withdrawal amount is 25000 for account "+ sourceAccountNumber);
			} else if(amount < 1000) {
				throw new InvalidCommandException("Minimum withdrawal amount is 1000 for account "+ sourceAccountNumber);
			}

		} catch (NumberFormatException e) {
			throw new InvalidCommandException();
		}
		return true;
	}

}
