import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BankApplicationTests
 *
 * @author Andres Arevalo & Emma Lee
 * @version 1.0
 */
public class BankApplicationTests
{
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp()
    {
        try
        {
            bank1 = new Bank();
            bank2 = new Bank();
            account1 = new BankAccount("12345", 1000);
            account2 = new BankAccount("67890", 500);
            bank1.addAccount(account1);
            bank2.addAccount(account2);
        }
        catch(Exception e)
        {
            fail();
        }
    }

    @AfterEach
    void tearDown()
    {
        bank1 = null;
        bank2 = null;
        account1 = null;
        account2 = null;
    }

    @Test
    void depositIncreasesBalanceAccount1()
    {
        account1.deposit(200);
        assertEquals(1200,
                     account1.getBalanceUsd());
    }

    @Test
    void withdrawDecreasesBalanceAccount1()
    {
        account1.withdraw(200);
        assertEquals(800,
                     account1.getBalanceUsd());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAccount1()
    {
        Exception exception = assertThrows(RuntimeException.class,
                                           () -> account1.withdraw(1200));
        assertEquals("Insufficient funds",
                     exception.getMessage());
    }

    @Test
    void addingNewAccountToBank2()
    {
        BankAccount newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);
        assertEquals(newAccount, bank2.retrieveAccount("54321"));
    }

    @Test
    void transferBetweenBankAccounts()
    {
        account1.transferToBank(account2,
                                "12345", 200);
        assertEquals(800, account1.getBalanceUsd());
        assertEquals(700, account2.getBalanceUsd());
    }

    @Test
    void totalBalanceCalculationForBank1()
    {
        assertEquals(1000, bank1.totalBalanceUsd());
    }

    @Test
    void totalBalanceCalculationForBank2()
    {
        assertEquals(500, bank2.totalBalanceUsd());
    }

    // Additional Tests

    @Test
    void retrieveAccountById()
    {
        assertEquals(account1, bank1.retrieveAccount("12345"));
        assertEquals(account2, bank2.retrieveAccount("67890"));
    }

    @Test
    void testAccountNumber()
    {
        assertEquals("12345", account1.getAccountNumber());
        assertEquals("67890", account2.getAccountNumber());

        badAccountNumber("1234541561");
        badAccountNumber("123456");
        badAccountNumber(null);
        badAccountNumber("     ");
        badAccountNumber(" ");
        badAccountNumber("");
        badAccountNumber("123");
    }

    @Test
    void testAccountBalance()
    {
        assertEquals(1000, account1.getBalanceUsd());
        assertEquals(500, account2.getBalanceUsd());

        badAccountBalance(-100);
        badAccountBalance(-2500);
    }

    @Test
    void increaseBankTotalBalance()
    {
        BankAccount newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);

        assertEquals(600, bank2.totalBalanceUsd()); //initial balance 500 + 100

        bank1.addAccount(newAccount);
        assertEquals(1100, bank1.totalBalanceUsd()); //initial balance 1000 + 100
    }

    @Test
    void cannotTransferToSameBankAccount()
    {
        Exception exception = assertThrows(IllegalBankException.class,
                                           () -> account1.transferToBank(account1, "12345", 400));
        assertEquals("Cannot transfer money to the same account",
                     exception.getMessage());
    }

    @Test
    void cannotAddNullAccountToBank()
    {
        final NullPointerException ex;
        ex = assertThrows(NullPointerException.class, () -> bank1.addAccount(null));
        assertEquals("Account can't be null.",  ex.getMessage());

    }

    @Test
    void cannotDepositNegativeAmount()
    {
        final IllegalArgumentException ex;
        ex = assertThrows(IllegalArgumentException.class, () -> account1.deposit(-200));
        assertEquals("Can't deposit a negative value. If you wish to withdraw use the appropriate option",  ex.getMessage());
    }

    void badAccountNumber(final String invalidAccountNumber)
    {
        final IllegalArgumentException ex;
        ex = assertThrows(IllegalArgumentException.class, () -> new BankAccount(invalidAccountNumber, 2000));
        assertEquals("Invalid account id: " + invalidAccountNumber,  ex.getMessage());
    }

    void badAccountBalance(final int invalidBalance)
    {
        final IllegalArgumentException ex;
        ex = assertThrows(IllegalArgumentException.class, () -> new BankAccount("12345", invalidBalance));
        assertEquals("Invalid initial account balance: " + invalidBalance,  ex.getMessage());
    }
}
