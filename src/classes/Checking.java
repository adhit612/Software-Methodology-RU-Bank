package classes;

public class Checking extends Account {
    public static final double INTEREST_RATE = 0.01;
    public static final int FEE = 12;

    public Checking(double balance) {
        this.balance = balance;
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
        else if (this.balance - FEE < 0) {
            return -1.0;
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

    public static void main(String[] args) {

    }
}
