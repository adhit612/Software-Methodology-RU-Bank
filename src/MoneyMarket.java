public class MoneyMarket extends Savings {
    public static final double INTEREST_RATE = 0.045;

    public static final double INTEREST_RATE_LOYAL = 0.045;
    public static final int FEE = 25;
    private int withdrawal; //number of withdrawals

    public MoneyMarket(double balance, boolean isLoyal, int withdrawal){
        super(balance,isLoyal);
        this.withdrawal = withdrawal;
    }

    @Override
    public double monthlyInterest() {
        if(isLoyal){
            return ((this.balance) +(this.balance * INTEREST_RATE_LOYAL));
        }
        else{
            return ((this.balance) +(this.balance * INTEREST_RATE));
        }
    }

    @Override
    public double monthlyFee() {
        withdrawal ++;
        //-1 means balance greater than 2000
        if(this.balance >= 2000){
            this.isLoyal = true;
            return -1;
        }
        else{
            this.isLoyal = false;
            this.balance -= FEE;
            if(this.withdrawal > 3){
                this.balance -= 10;
            }
            return this.balance;
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
