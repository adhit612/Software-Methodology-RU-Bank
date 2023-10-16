package classes;

import java.text.DecimalFormat;

public class Checking extends Account {
    public static final double INTEREST_RATE = 0.01;
    public static final double FEE = 12;

    public Checking(double balance, Profile prof) {
        this.balance = balance;
        this.holder = prof;
    }

    public Checking(Profile prof){
        this.holder = prof;
    }

    @Override
    public double monthlyInterest() {
        return INTEREST_RATE;
    }

    @Override
    public double monthlyFee() {
        //-1 means insufficient funds
        if (this.balance >= 1000){
            return 0.0;
        }
        else {
            return FEE;
        }
    }

    @Override
    public int compareTo(Account o) {
        if (this.balance < o.balance){
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
        return "Checking::" + this.getProfile().getFname() + " " + this.getProfile().getLname() + " " + this.getProfile().getDOB().toString() + "::Balance $" + df.format(getBalance());
    }

    public String returnType(){
        return "Checking";
    }
}
