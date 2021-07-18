package com.sahaj.bank;

import com.sahaj.bank.tasks.TransactionResetTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;

public class App
{
    public static void main( String[] args ) throws IOException {
        new Kiosk().start(args[0]);
    }
}
