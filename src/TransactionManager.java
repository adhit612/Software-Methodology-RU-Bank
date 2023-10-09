/**
 * class should handle all Java exceptions and invalid data before it calls the methods in AccountDatabase
 */

public class TransactionManager {

    /**
     * O Command => CC and S accounts have 7 separate tokens, C and MM accounts have 6
     * MM requires $2000 to open, and is set to loyal customer (1)(true) by default
     * Names NOT case-sensitive
     * Date of birth is on today or future => invalid date
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

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
