package com.bank.error;

public class InsufficientFundException extends Exception {
    public InsufficientFundException(String s) {
        super("operation refused, insufficient fund.");
    }
}
