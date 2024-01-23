package com.bank.account;

import com.bank.amount.Amount;
import com.bank.error.InsufficientFundException;
import com.bank.operation.Operation;
import com.bank.statement.Statement;

import java.io.PrintStream;
import java.util.Date;

public class Account {

    private final Statement statement;

    private Amount balance = Amount.newAmount(0);

    public Account(Statement statement) {
        this.statement = statement;
    }

    /**
     * Update the new deposit
     * @param input : The input value
     * @param date  : The input date
     */
    public void computeDeposit(Amount input, Date date) throws InsufficientFundException {
        saveOperation(input, date);
    }

    /**
     * Update the new withdrawal
     * @param input : The input value
     * @param date   The input date
     */
    public void computeWithdrawal(Amount input, Date date) throws InsufficientFundException {

        saveOperation(input.subtract(), date);
    }

    /**
     *  Print the statement
     * @param printer : The printer
     */
    public void printStatement(PrintStream printer) {
        statement.printProcess (printer);
    }

    /**
     * Save the operations
     * @param input : The input value
     * @param date   : The input date
     */
    private void saveOperation(Amount input, Date date) throws InsufficientFundException {
        Operation operation = new Operation(input, date);
        Amount balanceAfterOperation = operation.computeBalanceAfterOperation(balance);

        if(!balanceAfterOperation.isGreaterThan(Amount.newAmount(0)))
        {throw new InsufficientFundException("Account not sufficiently provisionned : " );}
       else {
            balance = balanceAfterOperation;
        }
        statement.appendOccurrenceContent(operation, balanceAfterOperation);
    }
}
