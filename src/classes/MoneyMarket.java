package classes;

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
            return INTEREST_RATE_LOYAL;
        }
        else{
            return INTEREST_RATE;
        }
    }

    @Override
    public double monthlyFee() {
        //-1 means balance greater than 2000
        if(this.balance >= 2000){
            this.isLoyal = true;
            return 0.0;
        }
        else{
            this.isLoyal = false;
            double finalDeduction = FEE;
            if(this.withdrawal > 3){
                finalDeduction -= 10;
            }
            return finalDeduction;
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

    public int getWithdrawal(){
        return this.withdrawal;
    }

    public void setWithdrawal(int newWithdrawal){
        this.withdrawal = newWithdrawal;
    }
}
