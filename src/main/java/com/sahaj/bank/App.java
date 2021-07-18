package com.sahaj.bank;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        new Kiosk().start(args[0]);
    }
}
