package com.bank.statement;


import com.bank.amount.Amount;
import com.bank.helpers.Helper;
import com.bank.operation.Operation;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Statement {



    private final List<StatementItem> statementItems = new LinkedList<>();

    /**
     * Append occurrence content
     * @param operation : The operation object
     * @param currentBalance : The current balance object
     */
    public void appendOccurrenceContent(Operation operation, Amount currentBalance) {
        statementItems.add(Helper.START, new StatementItem(operation, currentBalance));
    }

    /**
     * Print the line
     * @param printer : The print stream object
     */
    public void printProcess(PrintStream printer) {
        printer.println(Helper.HEADER_OP);
        printer.println(Helper.HEADER);
        printStatementOccurrence(printer);
    }

    /**
     * Print the statement occurrence
     * @param printer : The print stream object
     */
    private void printStatementOccurrence(PrintStream printer) {
        for (StatementItem statementItem : statementItems) {
            statementItem.printProcess(printer);
        }
    }
}
