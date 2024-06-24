/**
 * IllegalBankException is thrown when an illegal operation is attempted within the bank system.
 *
 * @author Emma Lee, Andres Arevalo
 * @version 1.0
 */
public class IllegalBankException extends RuntimeException
{
    /**
     * Constructs a new IllegalBankException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public IllegalBankException(final String message)
    {
        super(message);
    }
}
