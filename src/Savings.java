public class Savings extends Account {
    public static final int INTEREST_RATE = 5;
    public static final int FEE = 5;
    protected boolean isLoyal; //loyal customer status


    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}