public class Checking extends Account {
    public static final int INTEREST_RATE = 5;
    public static final int FEE = 5;

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
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}