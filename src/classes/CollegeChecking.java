package classes;

public class CollegeChecking extends Checking {

    private CampusCode campus; //campus code
    public static final double INTEREST_RATE = 0.01;

    public CollegeChecking(double balance, CampusCode campus, Profile prof){
        super(balance,prof);
        this.campus = campus;
    }

    public CampusCode getCampus(){
        return this.campus;
    }

    @Override
    public double monthlyInterest() {
        return INTEREST_RATE;
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
    @Override
    public String toString() {
        return "College Checking::" + this.getProfile().getFname() + " " + this.getProfile().getLname() + " " + this.getProfile().getDOB().toString() + "::Balance $" + this.getBalance() + "::" + this.getCampus().toString();
    }

    public String returnType(){
        return "College Checking";
    }
}
