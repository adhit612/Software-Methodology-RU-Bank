public class Checking extends Account {
    public static final double INTEREST_RATE = 0.01;
    public static final int FEE = 12;

    public Checking(double balance){
        this.balance = balance;
    }

    @Override
    public double monthlyInterest() {
        return this.balance += (this.balance * INTEREST_RATE);
    }

    @Override
    public double monthlyFee() {
        //-2 means balance is over 1000, -1 means there is insufficient funds
        if(this.balance >= 1000){
            return -2;
        }
        else if(this.balance - FEE < 0){
            return -1;
        }
        else{
            return this.balance -= FEE;
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

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
