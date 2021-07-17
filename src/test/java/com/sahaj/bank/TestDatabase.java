package com.sahaj.bank;

import com.sahaj.bank.data.Database;
import static org.junit.Assert.*;

import com.sahaj.bank.models.BankAccount;
import org.junit.Test;

public class TestDatabase {
	public void testAddAccount() {
		Database database = Database.getInstance();
		BankAccount account = new BankAccount(1001L, "MSD");
		database.addAccount(account);
	}
}
