package com.bank.operation;


import com.bank.amount.Amount;
import com.bank.helpers.Helper;

import static org.codehaus.plexus.util.StringUtils.rightPad;

import java.io.PrintStream;
import java.util.Date;
import java.util.Objects;

public class Operation {

    private final Amount input;
    private final Date date;

    /**
     * @param input : The input value
     * @param date : The input date
     */
    public Operation(Amount input, Date date) {
        this.input = input;
        this.date = date;
    }

    /**
     * Compute the balance after operation
     * @param currentBalance ; The current balance
     * @return ; The return value
     */
    public Amount computeBalanceAfterOperation(Amount currentBalance) {

        return currentBalance.addUp(input);
    }

    /**
     * Print the Process
     * @param printer : The print stream
     * @param currentBalance : The current balance
     */
    public void printProcess(PrintStream printer, Amount currentBalance) {
        StringBuilder builder = new StringBuilder();
        appendDateTo(builder);
        computeValueTo(builder);
        appendCurrentBalanceTo(builder, currentBalance);
        printer.println(builder.toString());
    }

    /**
     * Append the current balance
     * @param builder : The string builder
     * @param currentBalance ; The current balance
     */
    private void appendCurrentBalanceTo(StringBuilder builder, Amount currentBalance) {
        builder.append("|| ")
                .append(currentBalance.computeMoneyFormat());
    }

    /**
     * Compute the value
     * @param builder : The string builder
     */
    private void computeValueTo(StringBuilder builder) {
        if (input.isGreaterThan(Amount.newAmount(0))) {
            appendCreditTo(builder);
        } else {
            appendDebitTo(builder);
        }
    }

    /**
     * Append the debit
     * @param builder : The string builder
     */
    private void appendDebitTo(StringBuilder builder) {
        builder.append(Helper.EMPTY_INPUT)
                .append("||")
                .append(inputToString());
    }

    /**
     * Append the credit
     * @param builder / The string builder
     */
    private void appendCreditTo(StringBuilder builder) {
        builder.append(inputToString())
                .append("||")
                .append(Helper.EMPTY_INPUT);
    }

    /**
     * @return : Return the input string
     */
    private String inputToString() {
        String stringValue = " " + input.absoluteValue().computeMoneyFormat();
        return rightPad(stringValue, 10);
    }

    /**
     * Append the date
     * @param builder : The string builder
     */
    private void appendDateTo(StringBuilder builder) {
        builder.append(Helper.sdf.format(date));
        builder.append(" ||");
    }

    /**
     * @param o : The input object
     * @return : The boolean return value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(input, operation.input) && Objects.equals(date, operation.date);
    }

    /**
     * @return / Return the hashcode value
     */
    @Override
    public int hashCode() {
        return Objects.hash(input, date);
    }

    /**
     * @return : Return the string value
     */
    @Override
    public String toString() {
        return "Operation{" +
                "input=" + input +
                ", date=" + date +
                '}';
    }
}
