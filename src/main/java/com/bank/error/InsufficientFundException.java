package com.bank.error;

public class InsufficientFundException extends Exception {
    public InsufficientFundException() {
        super("operation refused, insufficient fund.");
    }
}
