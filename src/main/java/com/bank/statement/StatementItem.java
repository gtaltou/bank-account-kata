package com.bank.statement;

import com.bank.amount.Amount;
import com.bank.operation.Operation;

import java.io.PrintStream;

public class StatementItem {

    private final Operation operation;
    private final Amount currentBalance;

    public StatementItem(Operation operation, Amount currentBalance) {
        this.operation = operation;
        this.currentBalance = currentBalance;
    }

    /**
     * Print the process
     * @param printer : The print stream object
     */
    public void printProcess(PrintStream printer) {
        this.operation.printProcess(printer, currentBalance);
    }

}
