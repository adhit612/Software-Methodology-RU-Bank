public class CollegeChecking extends Checking {

    private Campus campus; //campus code
    public static final double INTEREST_RATE = 0.01;

    public CollegeChecking(double balance,Campus campus){
        super(balance);
        this.campus = campus;
    }

    @Override
    public double monthlyInterest() {
        return (this.balance += (this.balance * INTEREST_RATE));
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
