import java.util.*;

/**
 * Represents a Bank that manages multiple bank accounts.
 *
 * @author Emma Lee, Andres Arevalo
 * @version 1.0
 */
public class Bank
{
    /**
     * List of bank accounts managed by this bank.
     */
    private final List<BankAccount> accounts;
    private static final int MINIMUM_BALANCE = 0;

    {
        accounts = new ArrayList<>();
    }

    /**
     * Adds a new bank account to the bank's list of accounts.
     *
     * @param account The bank account to be added. Must not be null.
     * @throws NullPointerException if the account is null.
     */
    public void addAccount(final BankAccount account)
    {
        if(account == null )
        {
            throw new NullPointerException("Account can't be null.");
        }
        accounts.add(account);
    }

    /**
     * Retrieves a bank account by its account number.
     *
     * @param accountNumber The account number of the bank account to retrieve.
     * @return The bank account with the specified account number, or null if no such account exists.
     */
    public BankAccount retrieveAccount(final String accountNumber)
    {
        for(final BankAccount account : accounts)
        {
            if(account.getAccountNumber().equals(accountNumber))
            {
                return account;
            }
        }

        return null;
    }

    /**
     * Calculates the total balance in USD of all bank accounts managed by this bank.
     *
     * @return The total balance in USD.
     */
    public int totalBalanceUsd()
    {
        int totalBalance = MINIMUM_BALANCE;
        for(final BankAccount account : accounts)
        {
            totalBalance += account.getBalanceUsd();
        }

        return totalBalance;
    }
}
