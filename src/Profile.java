public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    //compare by first name then last name, then dob
    @Override
    public int compareTo(Profile o) {
        if(this.fname.toLowerCase().compareTo(o.fname.toLowerCase()) < 0){
            return -1;
        }
        else if(this.fname.toLowerCase().compareTo(o.fname.toLowerCase()) > 0){
            return 1;
        }
        else if(this.lname.toLowerCase().compareTo(o.lname.toLowerCase()) < 0){
            return -1;
        }
        else if(this.lname.toLowerCase().compareTo(o.lname.toLowerCase()) > 0){
            return 1;
        }
        else {
            if(this.dob.compareTo(o.dob) < 0) {
                return -1;
            }
            else if(this.dob.compareTo(o.dob) > 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public static void main(String [] args){
        Profile one = new Profile("Yo","Mane",new Date("3/28/2010"));
        Profile two = new Profile("Yo","Mane",new Date("3/28/2010"));

        System.out.println(one.compareTo(two));
    }
}
