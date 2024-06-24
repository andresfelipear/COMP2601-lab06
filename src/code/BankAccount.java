/**
 * Represents a bank account with an account number and balance in USD.
 *
 * @author Emma Lee, Andres Arevalo
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

    /**
     * Constructs a new BankAccount with the specified account number and initial balance.
     *
     * @param accountNumber The account number of this bank account. Must be non-null, non-blank, and exactly 5 characters long.
     * @param balanceUsd The initial balance in USD. Must be non-negative.
     * @throws IllegalArgumentException if the account number or initial balance is invalid.
     */
    public BankAccount(final String accountNumber,
                       final int balanceUsd)
    {
        validateAccountNumber(accountNumber);
        validateBalanceUsd(balanceUsd);
        this.accountNumber = accountNumber;
        this.balanceUsd = balanceUsd;
    }

    /**
     * Validates the initial balance.
     *
     * @param balanceUsd The initial balance to validate.
     * @throws IllegalArgumentException if the balance is negative.
     */
    private static void validateBalanceUsd(final int balanceUsd)
    {
        if(balanceUsd < POSITIVE_NUMBER)
        {
            throw new IllegalArgumentException("Invalid initial account balance: " + balanceUsd);
        }
    }

    /**
     * Validates the account number.
     *
     * @param accountNumber The account number to validate.
     * @throws IllegalArgumentException if the account number is null, blank, or not exactly 5 characters long.
     */
    private static void validateAccountNumber(final String accountNumber)
    {
        if(accountNumber == null || accountNumber.isBlank() || accountNumber.length() != ACCOUNT_MAX_LEN)
        {
            throw new IllegalArgumentException("Invalid account id: " + accountNumber);
        }
    }

    /**
     * Deposits a specified amount into this bank account.
     *
     * @param amount The amount to deposit. Must be non-negative.
     * @throws IllegalArgumentException if the amount is negative.
     */
    public void deposit(final int amount)
    {
        if(amount < POSITIVE_NUMBER)
        {
            throw new IllegalArgumentException("Can't deposit a negative value. If you wish to withdraw use the appropriate option");
        }
        balanceUsd += amount;
    }

    /**
     * Returns the current balance in USD of this bank account.
     *
     * @return The current balance in USD.
     */
    public int getBalanceUsd()
    {
        return balanceUsd;
    }

    /**
     * Withdraws a specified amount from this bank account.
     *
     * @param amountUsd The amount to withdraw. Must be less than or equal to the current balance.
     * @throws RuntimeException if there are insufficient funds.
     */
    public void withdraw(final int amountUsd)
    {
        if(amountUsd > balanceUsd)
        {
            throw new RuntimeException("Insufficient funds");
        }
        balanceUsd -= amountUsd;
    }

    /**
     * Returns the account number of this bank account.
     *
     * @return The account number.
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Transfers a specified amount from this bank account to another bank account.
     *
     * @param account2 The target bank account to transfer money to. Must not be the same as the current account.
     * @param accountNumber The account number of this bank account. Must match the account number of the current account.
     * @param amountUsd The amount to transfer. Must be less than or equal to the current balance.
     * @throws IllegalBankException if attempting to transfer to the same account.
     * @throws IllegalArgumentException if the provided account number does not match the current account's number.
     * @throws RuntimeException if there are insufficient funds for the transfer.
     */
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
