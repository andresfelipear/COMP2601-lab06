import java.util.ArrayList;
import java.util.List;

/**
 * Bank
 *
 * @author Andres Arevalo & Emma Lee
 * @version 1.0
 */
public class Bank
{
    private final List<BankAccount> accounts;

    {
        accounts = new ArrayList<>();
    }

    public void addAccount(final BankAccount account)
    {
        if(account == null )
        {
            throw new NullPointerException("Account can't be null.");
        }
        accounts.add(account);
    }

    public BankAccount retrieveAccount(final String accountNumber)
    {
       for(BankAccount account : accounts)
       {
           if(account.getAccountNumber().equals(accountNumber))
           {
               return account;
           }
       }

        return null;
    }

    public int totalBalanceUsd()
    {
        int totalBalance;
        totalBalance = 0;
        for(final BankAccount acc : accounts)
        {
            totalBalance += acc.getBalanceUsd();
        }

        return totalBalance;
    }
}
