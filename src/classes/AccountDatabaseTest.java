package classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDatabaseTest {

    @Test
    public void closeTrue() {
        Profile prof1 = new Profile("Bob", "Ross", new Date("7/4/2003"));
        Account checkAcct = new Checking(prof1);
        Account[] accounts = new Account[4];
        AccountDatabase db = new AccountDatabase(accounts, 4);
        db.open(checkAcct);
        assertTrue(db.close(checkAcct));
    }
    @Test
    public void closeFalse() {
        Profile prof2 = new Profile("Lionel", "Messi", new Date("10/20/2003"));
        Account savingsAcct = new Savings(100.00, true, prof2);
        Account[] accounts = new Account[4];
        AccountDatabase db = new AccountDatabase(accounts, 1);
        assertFalse(db.close(savingsAcct));
    }
}