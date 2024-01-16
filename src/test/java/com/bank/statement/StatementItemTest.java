package com.bank.statement;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static com.bank.amount.Amount.newAmount;
import static com.bank.helpers.Helper.date;
import static com.bank.operation.OperationHelper.aTransaction;
import static org.mockito.Mockito.verify;



@RunWith(MockitoJUnitRunner.class)
public class StatementItemTest {

    @Mock
    PrintStream printer;

    @Test
    public void
    should_print_an_operation() {
        StatementItem statementItem = new StatementItem(
                aTransaction()
                        .with(newAmount(4000))
                        .with(date("15/03/2023")).build(),
                newAmount(4000));

        statementItem.printProcess(printer);

        verify(printer).println("15/03/2023 || 4000,00  ||          || 4000,00");
    }

    @Test public void
    test_print_withdrawal() {
        StatementItem statementItem = new StatementItem(
                aTransaction()
                        .with(newAmount(-500))
                        .with(date("15/03/2023")).build(),
                newAmount(-500));

        statementItem.printProcess(printer);

        verify(printer).println("15/03/2023 ||          || 500,00   || -500,00");
    }

}
