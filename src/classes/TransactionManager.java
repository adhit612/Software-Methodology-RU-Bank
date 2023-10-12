package classes;

/**
 * class should handle all Java exceptions and invalid data before it calls the methods in classes.AccountDatabase
 */

public class TransactionManager {

    /**
     * O Command => CC and S accounts have 7 separate tokens, C and MM accounts have 6
     * MM requires $2000 to open, and is set to loyal customer (1)(true) by default
     * Names NOT case-sensitive
     * classes.Date of birth is on today or future => invalid date
     *
     * C Command => 5 separate tokens
     *
     * D Command => 6 separate tokens
     * Reject on invalid input entered
     *
     * W Command => 6 separate tokens
     * Check if there is enough money to be removed
     *
     * UB => apply fees and interest earned
     * Resets # of withdrawals in money market to 0
     */

    /**
     * TO DO:
     *Test Checkings
     *Test classes.Savings
     * Test classes.CollegeChecking
     *Test classes.MoneyMarket
     * Add check for money market in transactionmanager scanner to make sure minimum deposit is $2000
     *
     * COMPLETED:
     * classes.Checking class implementation
     * classes.Savings class implementation
     * classes.CollegeChecking class implementation
     * classes.MoneyMarket class implementation
     */

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
