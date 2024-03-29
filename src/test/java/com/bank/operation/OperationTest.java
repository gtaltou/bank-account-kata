package com.bank.operation;

import com.bank.amount.Amount;
import com.bank.helpers.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    @Mock
    PrintStream printer;

    @Test
    public void
    test_print_credit_operation() {
        Operation operation = new Operation(Amount.newAmount(3000), Helper.date("01/03/2023"));

        operation.printProcess(printer, Amount.newAmount(3000));

        verify(printer).println("01/03/2023 || 3000,00  ||          || 3000,00");
    }

    @Test public void
    test_print_operation_debit() {
        Operation operation = new Operation(Amount.newAmount(-2000), Helper.date("30/03/2023"));

        operation.printProcess(printer, Amount.newAmount(-2000));

        verify(printer).println("30/03/2023 ||          || 2000,00  || -2000,00");
    }

    @Test public void
    test_compute_current_balance_after_deposit() {
        Operation operation = new Operation(Amount.newAmount(3000), Helper.date("15/03/2023"));

        Amount currentValue = operation.computeBalanceAfterOperation(Amount.newAmount(500));

        assertThat(currentValue, is(Amount.newAmount(3500)));
    }

    @Test public void
    test_compute_current_balance_after_withdrawal() {
        Operation operation = new Operation(Amount.newAmount(-3000), Helper.date("15/03/2023"));

        Amount currentValue = operation.computeBalanceAfterOperation(Amount.newAmount(300));

        assertThat(currentValue, is(Amount.newAmount(-2700)));
    }

    @Test public void
    test_an_operation_is_equal_to_another_one_with_same_value_and_date() {
        Date depositDate = Helper.date("15/03/2023");
        Operation depositOfThreeHundred = new Operation(Amount.newAmount(3000), depositDate);
        Operation anotherDepositOfThreeHundred = new Operation(Amount.newAmount(3000), depositDate);

        assertThat(depositOfThreeHundred, is(equalTo(anotherDepositOfThreeHundred)));
    }
}
