/**
 * BankAccount
 *
 * @author user
 * @version 1.0
 */
public class BankAccount
{
    private final String accountNumber;
    private int balanceUsd;

    private static final int POSITIVE_NUMBER;
    private static final int ACCOUNT_MAX_LEN;

    static
    {
        POSITIVE_NUMBER = 0;
        ACCOUNT_MAX_LEN = 5;
    }

    public BankAccount(final String accountNumber,
                       final int balanceUsd)
    {
        validateAccountNumber(accountNumber);
        validateBalanceUsd(balanceUsd);
        this.accountNumber = accountNumber;
        this.balanceUsd = balanceUsd;
    }

    private static void validateBalanceUsd(final int balanceUsd)
    {
        if(balanceUsd < POSITIVE_NUMBER)
        {
            throw new IllegalArgumentException("Invalid initial account balance: " + balanceUsd);
        }

    }

    private static void validateAccountNumber(final String accountNumber)
    {
        if(accountNumber == null || accountNumber.isBlank() || accountNumber.length() != ACCOUNT_MAX_LEN)
        {
            throw new IllegalArgumentException("Invalid account id: " + accountNumber);
        }
    }

    public void deposit(final int amount)
    {
        if(amount < POSITIVE_NUMBER)
        {
            throw new IllegalArgumentException("Can't deposit a negative value. If you wish to withdraw use the appropriate option");
        }
        balanceUsd += amount;
    }

    public int getBalanceUsd()
    {
        return balanceUsd;
    }

    public void withdraw(final int amountUsd)
    {
        if(amountUsd > balanceUsd)
        {
            throw new RuntimeException("Insufficient funds");
        }
        balanceUsd -= amountUsd;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void transferToBank(final BankAccount account2,
                                  final String accountNumber,
                                  final int amountUsd)
    {
        if(this.equals(account2))
        {
            throw new IllegalBankException("Cannot transfer money to the same account");
        }

        if(!this.accountNumber.equals(accountNumber))
        {
            throw new IllegalArgumentException("The given account number doesn't match your account number. " + accountNumber);
        }

        this.withdraw(amountUsd);
        account2.deposit(amountUsd);
    }
}
