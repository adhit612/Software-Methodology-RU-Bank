package classes;
import java.text.DecimalFormat;

/**
 * use similar implementation of array as first project
 */

public class AccountDatabase {
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    public static final int NOT_FOUND = -1;
    public static final int ARRAY_SIZE_ADDER = 4;

    /**
     * Constructor to initialize an EventCalendar.
     * @param accounts The array of Events, initially size 4.
     * @param numAcct The number of events, initially 0.
     */
    public AccountDatabase(Account[] accounts, int numAcct) {
        this.accounts = accounts;
        this.numAcct = numAcct;
    }

    /**
     * Locate a given Event within the EventCalendar.
     * @param account the event to be searched for.
     * @return The index of the event if found, NOT_FOUND otherwise.
     */
    private int find(Account account) {
        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i].equals(account)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Grow the AccountDatabase if max capacity is reached.
     * If the last element in the AccountDatabase is non-null, the array needs to grow.
     * Makes 4 more spaces for additional Accounts.
     */
    private void grow() { //increase the capacity by 4
        Account[] newAccounts = new Account[this.accounts.length + ARRAY_SIZE_ADDER];
        for (int i = 0; i < this.accounts.length; i++) {
            newAccounts[i] = this.accounts[i];
        }
        this.accounts = newAccounts;
    }
    /**
     * Check if Account is within the AccountDatabase.
     * @param account The Account to be checked for.
     * @return true if Account is in AccountDatabase, false otherwise.
     */
    public boolean contains(Account account) {
        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                return false;
            }
            if (this.accounts[i].equals(account)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add an account to the AccountDatabase.
     * Checking if valid Account not done here.
     * Simply adding to Calendar.
     * @param account The Account to be added.
     * @return true if added to AccountDatabase, false otherwise.
     */
    public boolean open(Account account) { //add a new account
        if (this.accounts[this.accounts.length - 1] != null) {
            grow();
        }

        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                this.accounts[i] = account;
                this.numAcct++;
                return true;
            }
        }
        return false;
    }

    /**
     * Remove an Account from the AccountDatabase.
     * Given an Account, remove that Account entirely from the AccountDatabase.
     * @param account The Account to be removed.
     * @return true if Account was removed, false otherwise.
     */
    public boolean close(Account account) { //remove the given account
        Account[] newAccounts = new Account[this.accounts.length];
        int j = find(account);
        if (j == NOT_FOUND) {
            return false;
        }
        int k = 0;
        for (int x = 0; x < this.accounts.length; x++) {
            if (x == j) {
                continue;
            }
            newAccounts[k] = this.accounts[x];
            k++;
        }

        this.numAcct--;
        this.accounts = newAccounts;
        return true;
    }

    public boolean withdraw(Account account) { //false if insufficient fund
       //increase money market amount of withdrawals here, use getter and setter methods
        for(int i = 0; i < this.accounts.length; i ++){
            if(this.accounts[i].equals(account)){
                double currBal = this.accounts[i].getBalance();
                if(currBal - account.getBalance() < 0){
                    return false;
                }
                else{
                    currBal -= account.getBalance();
                    this.accounts[i].setBalance(currBal);
                }
            }
        }
        return false;
    }
    public void deposit(Account account) {
        for(int i = 0; i < this.accounts.length; i ++) {
            if(this.accounts[i].equals(account)) {
                double currBal = this.accounts[i].getBalance();
                currBal += account.getBalance();
                this.accounts[i].setBalance(currBal);
                break;
            }
        }
    }
    public void printSorted() { //sort by account type and profile
        System.out.println("*Accounts sorted by account type and profile.");
        for (int i = 1; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                break;
            }

            Account temp = this.accounts[i];
            int j = i - 1;
            while (j >= 0 && this.accounts[j].returnType().compareTo(temp.returnType()) > 0){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            while (j >= 0 && (this.accounts[j].getProfile().getLname().compareTo(temp.getProfile().getLname()) > 0 && this.accounts[j].returnType().equals(temp.returnType()))){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            while (j >= 0 && this.accounts[j].getProfile().getFname().compareTo(temp.getProfile().getFname()) > 0 && this.accounts[j].getProfile().getLname().equals(temp.getProfile().getLname()) && this.accounts[j].returnType().equals(temp.returnType())){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            this.accounts[j + 1] = temp;
        }

        for (int x = 0; x < this.accounts.length; x++) {
            if (this.accounts[x] == null) {
                break;
            }
            System.out.println(this.accounts[x].toString());
        }
        System.out.println("*end of list.");
    }
    public void printFeesAndInterests() { //calculate interests/fees
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 1; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                break;
            }

            Account temp = this.accounts[i];
            int j = i - 1;
            while (j >= 0 && this.accounts[j].returnType().compareTo(temp.returnType()) > 0){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            while (j >= 0 && (this.accounts[j].getProfile().getLname().compareTo(temp.getProfile().getLname()) > 0 && this.accounts[j].returnType().equals(temp.returnType()))){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            while (j >= 0 && this.accounts[j].getProfile().getFname().compareTo(temp.getProfile().getFname()) > 0 && this.accounts[j].getProfile().getLname().equals(temp.getProfile().getLname()) && this.accounts[j].returnType().equals(temp.returnType())){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            this.accounts[j + 1] = temp;
        }

        for (int x = 0; x < this.accounts.length; x++) {
            if (this.accounts[x] == null) {
                break;
            }
            double interest = this.accounts[x].getBalance() * this.accounts[x].monthlyInterest();
            System.out.println(this.accounts[x].toString() + "::fee $" + this.accounts[x].monthlyFee() + "::monthly interest $" + df.format(this.accounts[x].getBalance() * interest));
        }
    }

    public void printUpdatedBalances() { //apply the interests/fees
        for (int x = 0; x < this.accounts.length; x++) {
            if(this.accounts[x] == null) {
                break;
            }
            double interest = this.accounts[x].getBalance() * this.accounts[x].monthlyInterest();
            double fee = this.accounts[x].getBalance();
            double currBalance = this.accounts[x].getBalance() - fee + interest;
            this.accounts[x].setBalance(currBalance);
        }

        for (int i = 1; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                break;
            }

            Account temp = this.accounts[i];
            int j = i - 1;
            while (j >= 0 && this.accounts[j].returnType().compareTo(temp.returnType()) > 0){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            while (j >= 0 && (this.accounts[j].getProfile().getLname().compareTo(temp.getProfile().getLname()) > 0 && this.accounts[j].returnType().equals(temp.returnType()))){
                this.accounts[j + 1] = this.accounts[j];
                j--;
            }

            this.accounts[j + 1] = temp;
        }

        for (int x = 0; x < this.accounts.length; x++) {
            if (this.accounts[x] == null) {
                break;
            }
            System.out.println(this.accounts[x].toString());
        }
    }

    public int getNumAcct() {
        return this.numAcct;
    }

    public Account containsForClose(Account account, String type){
        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                return null;
            }
            if(this.accounts[i].getProfile().getFname().equals(account.getProfile().getFname()) && this.accounts[i].getProfile().getLname().equals(account.getProfile().getLname()) && this.accounts[i].getProfile().getDOB().equals(account.getProfile().getDOB()) && this.accounts[i].returnType().equals(type)){
                return this.accounts[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Account[] accounts = new Account[4];
        AccountDatabase db = new AccountDatabase(accounts, 4);

        Date date1 = new Date ("11/21/2003");
        Profile prof1 = new Profile("Bob", "Ross", date1);
        Account checkingAcct = new Checking(200.0, prof1);
        Account collegeAcct = new CollegeChecking(1500.00, CampusCode.ONE, prof1);

        Date date2 = new Date ("10/15/2005");
        Profile prof2 = new Profile("Babe", "Ruth", date2);
        Account moneyAcct = new MoneyMarket(25000.00, true, 0, prof2);

        Date date3 = new Date ("1/10/2000");
        Profile prof3 = new Profile("Baba", "Sheik", date3);
        Account savingsAcct = new Savings(10.00, true, prof3);

        Date date4 = new Date ("5/19/2004");
        Profile prof4 = new Profile("Bruce", "Wayne", date4);
        Account savingsAcct2 = new Savings(10.00, false, prof4);

        db.open(savingsAcct);
        db.open(collegeAcct);
        db.open(savingsAcct2);
        db.open(moneyAcct);
        db.open(checkingAcct);

        //db.close(savingsAcct);
        //db.close(moneyAcct);
        //db.close(collegeAcct);

        db.printSorted();
        System.out.println("BREAK");
        db.printFeesAndInterests();
        System.out.println("BREAK");
        db.printUpdatedBalances();
    }

}
