package com.sahaj.bank;

import com.sahaj.bank.tasks.TransactionResetTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;

public class App
{
    public static void main( String[] args ) throws IOException {
//        new Kiosk().start(args[0]);
//        Timer timer = new Timer();
//        Calendar date = Calendar.getInstance();
//        date.set(Calendar.HOUR, 0);
//        date.set(Calendar.MINUTE, 0);
//        date.set(Calendar.SECOND, 0);
//        date.set(Calendar.MILLISECOND, 0);
//        timer.schedule(
//                new TransactionResetTask(),
//                date.getTime(),
//                1000 * 60 * 60 * 24
//        );
        new Kiosk().start(args[0]);
    }
}
