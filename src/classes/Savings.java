package classes;

import java.text.DecimalFormat;

public class Savings extends Account {
    public static final double INTEREST_RATE = 0.04;
    public static final double FEE = 25;
    protected boolean isLoyal; //loyal customer status

    public Savings(double balance, boolean isLoyal, Profile prof) {
        this.balance = balance;
        this.isLoyal = isLoyal;
        this.holder = prof;
    }

    public boolean getLoyal() {
        return this.isLoyal;
    }

    @Override
    public double monthlyInterest() {
        double finalRate = INTEREST_RATE;
        if (isLoyal) {
            finalRate += 0.25;
            return finalRate;
        }
        return finalRate;
    }

    @Override
    public double monthlyFee() {
        if (this.balance >= 500) {
            return 0.0;
        }
        else {
            return FEE;
        }
    }

    @Override
    public int compareTo(Account o) {
        if (this.balance < o.balance) {
            return -1;
        }
        else if (this.balance > o.balance) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        if (this.getLoyal()) {
            return "Savings::" + this.getProfile().getFname() + " " +
                    this.getProfile().getLname() + " " + this.getProfile()
                    .getDOB().toString() + "::Balance $" +
                    df.format(this.getBalance()) + "::is loyal";
        }
        else {
            return "Savings::" + this.getProfile().getFname() + " " +
                    this.getProfile().getLname() + " " + this.getProfile()
                    .getDOB().toString() + "::Balance $" +
                    df.format(getBalance());
        }

    }

    public String returnType() {
        return "Savings";
    }
}
