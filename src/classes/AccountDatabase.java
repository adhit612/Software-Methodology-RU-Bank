package classes;

/**
 * use similar implementation of array as first project
 */

public class AccountDatabase {
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    public AccountDatabase(Account[] accounts, int numAcct) {
        this.accounts = accounts;
        this.numAcct = numAcct;
    }

    private int find(Account account) { //search for an account in the array
        return 0;
    }
    private void grow() { //increase the capacity by 4

    }
    public boolean contains(Account account) { //overload if necessary
        return false;
    }
    public boolean open(Account account) { //add a new account
        return false;
    }
    public boolean close(Account account) { //remove the given account
        return false;
    }
    public boolean withdraw(Account account) { //false if insufficient fund
        return false;
    }
    public void deposit(Account account) {

    }
    public void printSorted() { //sort by account type and profile

    }
    public void printFeesAndInterests() { //calculate interests/fees

    }
    public void printUpdatedBalances() { //apply the interests/fees

    }

}
