package classes;

/**
 * Department enum to declare a Department.
 * Given a set list, define the department with its name.
 * @author Abhishek Thakare, Adhit Thakur
 */
public abstract class Account implements Comparable<Account> {
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

    public Profile getProfile() {
        return this.holder;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account acc = (Account) obj;
            return this.getProfile().getDOB().equals(acc.getProfile().getDOB())
                    && this.getProfile().getLname().toLowerCase()
                    .equals(acc.getProfile().getLname().toLowerCase())
                    && this.getProfile().getFname().toLowerCase()
                    .equals(acc.getProfile().getFname().toLowerCase())
                    && this.getBalance() == acc.getBalance();
        }
        return false;
    }
}
