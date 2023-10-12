package classes;

public class Savings extends Account {
    public static final double INTEREST_RATE = 0.04;
    public static final int FEE = 25;
    protected boolean isLoyal; //loyal customer status

    public Savings(double balance,boolean isLoyal){
        this.balance = balance;
        this.isLoyal = isLoyal;
    }

    @Override
    public double monthlyInterest() {
        double finalRate = INTEREST_RATE;
        if(isLoyal){
            finalRate += 0.25;
            return finalRate;
        }
        return finalRate;
    }

    @Override
    public double monthlyFee() {
        //-1 means insufficient funds
        if(this.balance >= 500){
            return 0.0;
        }
        else if(this.balance - FEE < 0){
            return -1;
        }
        else{
            return FEE;
        }
    }

    @Override
    public int compareTo(Account o) {
        if(this.balance < o.balance){
            return -1;
        }
        else if(this.balance > o.balance){
            return 1;
        }
        else{
            return 0;
        }
    }
}
