package classes;

/**
 * checking and college checking accounts - $12 fee, 1.0% annual interest rate, monthly fee waived if account balance >= 1000, no monthly fee for college checking
 * savings (4%) and money market savings accounts (4.5%), savings loyal customer states gets additional 0.25% for annual interest rate
 * savings - no monthly fee for savings with balance >= 500
 * minimum deposit $2000 to open Money Market, default loyal customer status so additional 0.25%, no monthly fee for money market with balance >= 2000
 * uncheck loyal status when account balance drops below 2k, check status again when above= 2k
 * $10 fee applies if withdraw more than 3 times
 */
public abstract class Account implements Comparable<Account>{
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    public abstract String returnType();

    public void setBalance(double bal) {
        this.balance = bal;
    }
    public double getBalance() {
        return balance;
    }

    public Profile getProfile(){
        return this.holder;
    }
    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account acc = (Account) obj;
            return this.getProfile().getDOB().equals(acc.getProfile().getDOB()) && this.getProfile().getLname().toLowerCase().equals(acc.getProfile().getLname().toLowerCase()) && this.getProfile().getFname().toLowerCase().equals(acc.getProfile().getFname().toLowerCase()) && this.getBalance() == acc.getBalance();
        }
        return false;
    }
}
