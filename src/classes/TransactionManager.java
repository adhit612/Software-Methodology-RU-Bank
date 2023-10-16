package classes;

import java.text.ParseException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * class should handle all Java exceptions and invalid data before it calls the methods in classes.AccountDatabase
 */

public class TransactionManager {

    /**
     * O Command => CC and S accounts have 7 separate tokens, C and MM accounts have 6
     * MM requires $2000 to open, and is set to loyal customer (1)(true) by default
     * Names NOT case-sensitive
     * classes.Date of birth is on today or future => invalid date
     *
     * C Command => 5 separate tokens
     *
     * D Command => 6 separate tokens
     * Reject on invalid input entered
     *
     * W Command => 6 separate tokens
     * Check if there is enough money to be removed
     *
     * UB => apply fees and interest earned
     * Resets # of withdrawals in money market to 0
     */

    /**
     * TO DO:
     *Test Checkings
     *Test classes.Savings
     * Test classes.CollegeChecking
     *Test classes.MoneyMarket
     * Add check for money market in transactionmanager scanner to make sure minimum deposit is $2000
     *
     * COMPLETED:
     * classes.Checking class implementation
     * classes.Savings class implementation
     * classes.CollegeChecking class implementation
     * classes.MoneyMarket class implementation
     */

    /**
     * Runner for Scanner input processing.
     * Taking in user input until "Q" is entered.
     * Processing each command at a very high level.
     */
    public void run() {
        Account[] collection = null;
        AccountDatabase ad = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Transaction Manager is running.");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("Q")) {
                System.out.println("Transaction Manager is terminated.");
                break;
            }
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            String[] contents = new String[tokenizer.countTokens()];
            int w = 0;
            while (tokenizer.hasMoreTokens()) {
                contents[w++] = tokenizer.nextToken();
            }
            if (contents.length == 0) {

            }
            else if (!isValidCommand(contents[0])) {
                System.out.println("Invalid command!");
            }
            else if (!contents[0].equals("O") && collection == null) {
                System.out.println("Account Database is empty!");
            }
            else if (contents[0].equals("O") && collection == null && contents.length < 6) {
                System.out.println("Missing data for opening an account.");
            }
            else if (contents[0].equals("O") && collection == null) {
                if((!isNumeric(contents[5]))){
                    System.out.println("Not a valid amount.");
                }
                else if(isNumeric(contents[5]) &&  Double.parseDouble(contents[5]) <= 0){
                    System.out.println("Initial deposit cannot be 0 or negative.");
                }
                else{
                    collection = new Account[4];
                    ad = new AccountDatabase(collection, 0);
                    Profile prof = new Profile(contents[2],contents[3],new Date(contents[4]));
                    if(contents[1].equals("C")){
                        Account account = new Checking(Double.parseDouble(contents[5]),prof);
                        ad.open(account);
                        System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(C)" + " opened");
                    }
                    else if(contents[1].equals("CC")){
                        CampusCode campusCode = null;
                        if(contents[6].equals("0")){
                            campusCode = CampusCode.ZERO;
                        }
                        else if(contents[6].equals("1")){
                            campusCode = CampusCode.ONE;
                        }
                        else{
                            campusCode = CampusCode.TWO;
                        }

                        Account account = new CollegeChecking(Double.parseDouble(contents[5]),campusCode,prof);
                        ad.open(account);
                        System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(CC)" + " opened");
                    }
                    else if(contents[1].equals("S")){
                        boolean isLoyal = false;
                        if(contents[6].equals("1")){
                            isLoyal = true;
                        }
                        Account account = new Savings(Double.parseDouble(contents[5]),isLoyal,prof);
                        ad.open(account);
                        System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(S)" + " opened");
                    }
                    else{
                        if(Double.parseDouble(contents[5]) < 2000){
                            System.out.println("Minimum of $2000 to open a Money Market account.");
                        }
                        else{
                            Account account = new MoneyMarket(Double.parseDouble(contents[5]),true,0,prof);
                            ad.open(account);
                            System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(MM)" + " opened");
                        }
                    }
                }
                //aCommandInit(collection,calendar,contents);
            }
            else if(contents[0].equals("O")){
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                if((!isNumeric(contents[5]))){
                    System.out.println("Not a valid amount.");
                }
                else if(isNumeric(contents[5]) &&  Double.parseDouble(contents[5]) <= 0){
                    System.out.println("Initial deposit cannot be 0 or negative.");
                }
                else{
                    Account account = null;
                    Profile prof = new Profile(contents[2],contents[3],new Date(contents[4]));
                    Date date = new Date(contents[4]);
                    if(!date.isValid()){
                        System.out.println("DOB invalid: " + contents[4] + " not a valid calendar date!");
                        continue;
                    }
                    if(!date.checkIfWithinBounds(date.getMonth(),date.getYear(),date.getDay())){
                        System.out.println("DOB invalid: " + contents[4] + " cannot be today or a future date");
                        continue;
                    }

                    int age = 2023 - date.getYear();
                    int dayDiff = date.getDay() - date.getTodaysDate();
                    //System.out.println("todays date " + date.getTodaysDate() + " age " + age + " dayDiff " + dayDiff );
                    if(age <= 16 && dayDiff >= 0){
                        System.out.println("DOB invalid: " + contents[4] + " under 16.");
                        continue;
                    }

                    double res = 0.0;
                    try {
                        res = decimalFormat.parse(contents[5]).doubleValue();
                    }catch(ParseException e){
                        e.printStackTrace();
                    }

                    if(contents[1].equals("C")){
                        account = new Checking(res,prof);
                    }
                    else if(contents[1].equals("CC")){
                        if(age >= 24 && (date.getDay() - date.getTodaysDate() >= 0)){
                            System.out.println("DOB invalid: " + contents[4] + " over 24.");
                            continue;
                        }
                        CampusCode campusCode = null;
                        if(contents[6].equals("0")){
                            campusCode = CampusCode.ZERO;
                        }
                        else if(contents[6].equals("1")){
                            campusCode = CampusCode.ONE;
                        }
                        else if(contents[6].equals("2")){
                            campusCode = CampusCode.TWO;
                        }
                        else{
                            System.out.println("Invalid campus code.");
                            continue;
                        }

                        account = new CollegeChecking(res,campusCode,prof);
                    }
                    else if(contents[1].substring(0,1).equals("S")){
                        boolean isLoyal = false;
                        if(contents[6].equals("1")){
                            isLoyal = true;
                        }
                        account = new Savings(res,isLoyal,prof);
                    }
                    else if(contents[1].equals("MM")){
                        if(Double.parseDouble(contents[5]) < 2000){
                            System.out.println("Minimum of $2000 to open a Money Market account.");
                            continue;
                        }
                        else{
                            account = new MoneyMarket(res,true,0,prof);
                        }
                    }

                    if(ad.contains(account)){
                        System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(" + contents[1] + ") is already in the database.");
                    }
                    else{
                        ad.open(account);
                        if(contents[1].equals("MM") || contents[1].equals("CC")){
                            System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(" + contents[1] + ") opened.");
                        }
                        else {
                            System.out.println(account.getProfile().getFname() + " " + account.getProfile().getLname() + " " + account.getProfile().getDOB() + "(" + contents[1].substring(0, 1) + ") opened.");
                        }
                    }
                }
            }
            else if(contents[0].equals("P")){
                ad.printSorted();
            }
            else if(contents[0].equals("C")){
                if(contents.length < 5){
                    System.out.println("Missing data for closing an account");
                    continue;
                }
                else{
                    Profile prof = new Profile(contents[2],contents[3], new Date(contents[4]));
                    Date date = new Date(contents[4]);
                    if(!date.checkIfWithinBounds(date.getMonth(),date.getYear(),date.getDay())){
                        System.out.println("DOB invalid: " + date.toString() + " cannot be a today or a future day.");
                        continue;
                    }
                    else{
                        Account ac = new Checking(prof);
                        String type = "";
                        if(contents[1].equals("C")){
                            type = "Checking";
                        }
                        else if(contents[1].equals("CC")){
                            type = "College Checking";
                        }
                        else if(contents[1].equals("S")){
                            type = "Savings";
                        }
                        else if(contents[1].equals("MM")){
                            type = "Money Market";
                        }

                        if(ad.containsForClose(ac,type) == null){
                            System.out.println(prof.getFname() + " " + prof.getLname() + " " + prof.getDOB() + "(" + contents[1] + ") is not in the database.");
                        }
                        else {
                            ad.close(ad.containsForClose(ac,type));
                            System.out.println(prof.getFname() + " " + prof.getLname() + " " + prof.getDOB() + "(" + contents[1] + ") has been closed.");
                        }
                    }
                }
            }
//            else {
//                runThroughNonEdgeCases(collection,calendar,contents);
//            }


        }
        scanner.close();
    }

    public static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }

        try{
            double d = Double.parseDouble(strNum);
        }catch(NumberFormatException e){
            return false;
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Determining what to output to user after all edge cases are tackled.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    /*
    public void runThroughNonEdgeCases(Account [] collection, AccountDatabase calendar, String [] contents){
        if (contents[0].equals("A")) {
            aCommandAfterInit(collection,calendar,contents);
        }
        else if (!contents[0].equals("A") && calendar.getNumEvents() == 0) {
            System.out.println("Event calendar is empty!");
        }
        else if (contents[0].equals("R")) {
            rCommandChecker(collection,calendar,contents);
        }
        else if (contents[0].equals("P")) {
            System.out.println("* Event calendar *");
            pCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
        else if (contents[0].equals("PE")) {
            System.out.println("* Event calendar by event date and start time *");
            peCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
        else if (contents[0].equals("PC")) {
            System.out.println("* Event calendar by campus and building *");
            pcCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
        else if(contents[0].equals("PD")) {
            System.out.println("* Event calendar by department *");
            pdCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
    }
    */

    /**
     * Generate the appropriate message when adding event to a currently null calendar.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */

    /*
    public void aCommandInit(Event [] collection, EventCalendar calendar, String [] contents){
        if (isValidEvent(contents).equals("valid")) {
            Event event = createEvent(contents);
            calendar.add(event);
            System.out.println("Event added to the calendar.");
        }
        else {
            System.out.println(isValidEvent(contents));
        }
    }

     */

    /**
     * Generate appropriate message when adding event to a non-null calendar.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */

    /*
    public void aCommandAfterInit(Event [] collection, EventCalendar calendar, String [] contents){
        if (isValidEvent(contents).equals("valid")) {
            Event event = createEvent(contents);
            if (calendar.contains(event)) {
                System.out.println("The event is already on the calendar");
            }
            else {
                calendar.add(event);
                System.out.println("Event added to the calendar.");
            }
        }
        else {
            System.out.println(isValidEvent(contents));
        }
    }

     */

    /**
     * Package the remove command processing.
     * Determine whether Event can be removed and performing the operation.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    /*
    public void rCommandChecker(Event [] collection, EventCalendar calendar, String [] contents){
        if (isValidRemove(contents).equals("valid")) {
            Event event = createEventForRemove(contents);
            if (calendar.contains(event)) {
                calendar.remove(event);
                System.out.println("Event has been removed from the calendar!");
            }
            else {
                System.out.println("Cannot remove; event is not in the calendar!");
            }
        }
        else {
            System.out.println(isValidRemove(contents));
        }
    }

     */

    /**
     * Process printing the unsorted calendar if the "P" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    /*
    public void pCommandChecker(Event [] collection, EventCalendar calendar, String [] contents){
        if (contents.length == 1) {
            calendar.print();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

     */

    /**
     * Process printing the calendar sorted by date and timeslot if the "PE" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    /*
    public void peCommandChecker(Event [] collection, EventCalendar calendar, String [] contents) {
        if (contents.length == 1) {
            calendar.printByDate();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

     */

    /**
     * Process printing the calendar sorted by campus and building if the "PC" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    /*
    public void pcCommandChecker(Event [] collection, EventCalendar calendar, String [] contents) {
        if (contents.length == 1) {
            calendar.printByCampus();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

     */

    /**
     * Process printing the calendar sorted by department if the "PD" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    /*
    public void pdCommandChecker(Event [] collection, EventCalendar calendar, String [] contents) {
        if (contents.length == 1) {
            calendar.printByDepartment();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

     */

    /**
     * Check if the given command is of proper type.
     * Determining if the command is one of the 6 possible inputs.
     * @param str Command to be checked.
     * @return true if command is one of 6 possible inputs, otherwise false.
     */
    private boolean isValidCommand(String str) {
        if (str.equals("O") || str.equals("C") || str.equals("D")
                || str.equals("W") || str.equals("P") || str.equals("PI") || str.equals("UB")) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the event to be added is valid.
     * Process each component of Event to ensure it is fully valid.
     * Generate appropriate message.
     * @param contents Command to be checked.
     * @return Error message depending on the issue with Event parameter, otherwise "valid".
     */
    /*
    private String isValidEvent(String[] contents) {
        Date date = new Date(contents[1]);
        if (!date.isValid()) {
            return contents[1] + ": Invalid calendar date!";
        }
        else if (date.checkIfWithinBounds(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be within 6 months!";
        }
        else if (date.checkIfInPast(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be a future date!";
        }
        if (!contents[2].toLowerCase().equals("morning")
                && !contents[2].toLowerCase()
                .equals("afternoon") && !contents[2].toLowerCase()
                .equals("evening")) {
            return "Invalid time slot!";
        }
        String roomToLower = contents[3].toLowerCase();
        if (!roomToLower.equals("hll114") && !roomToLower.equals("arc103")
                && !roomToLower.equals("be_aud") && !roomToLower.equals("til232")
                && !roomToLower.equals("ab2225") && !roomToLower.equals("mu302")) {
            return "Invalid location!";
        }
        String departmentToLower = contents[4].toLowerCase();
        if (!departmentToLower.equals("cs") && !departmentToLower.equals("ee")
                && !departmentToLower.equals("iti") && !departmentToLower.equals("math")
                && !departmentToLower.equals("bait")) {
            return "Invalid contact information!";
        }
        Contact contact = new Contact(Department.CS, contents[5]);
        if (!contact.isValid()) {
            return "Invalid contact information!";
        }
        int duration = Integer.parseInt(contents[6]);
        if (duration < 30 || duration > 120) {
            return "Event duration must be at least 30 minutes"
                    + " and at most 120 minutes";
        }
        return "valid";
    }

     */

    /**
     * Determines if the event to be removed is valid.
     * Checks if Date, Timeslot, and Location are proper.
     * @param contents The remove command to be processed.
     * @return Error message depending on the issue with Event parameter, otherwise "valid".
     */
    /*
    private String isValidRemove(String[] contents) {
        Date date = new Date(contents[1]);
        if (!date.isValid()) {
            return contents[1] + ": Invalid calendar date!";
        }
        else if (date.checkIfWithinBounds(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be within 6 months!";
        }
        else if (date.checkIfInPast(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be a future date!";
        }
        if (!contents[2].toLowerCase().equals("morning") && !contents[2].toLowerCase()
                .equals("afternoon") && !contents[2].toLowerCase().equals("evening")) {
            return "Invalid time slot!";
        }
        String roomToLower = contents[3].toLowerCase();
        if (!roomToLower.equals("hll114") && !roomToLower.equals("arc103")
                && !roomToLower.equals("be_aud") && !roomToLower.equals("til232")
                && !roomToLower.equals("ab2225") && !roomToLower.equals("mu302")) {
            return "Invalid location!";
        }
        return "valid";
    }

     */

    /**
     * Creating an event based on user input to add.
     * Once Event is determined to be valid, generate Event.
     * Add that event to the EventCalendar.
     * @param contents Command to be processed into an Event.
     * @return the Event that will be added to the EventCalendar.
     */
    /*
    private Event createEvent(String[] contents) {
        Date date = new Date(contents[1]);
        Timeslot startTime = createTimeSlot(contents[2]);
        Location location = createLocation(contents[3]);
        Department department = createDepartment(contents[4]);
        Contact contact = new Contact(department, contents[5]);
        int duration = Integer.parseInt(contents[6]);
        return new Event(date, startTime, location, contact, duration);
    }

     */

    /**
     * Creating an event based on user input to remove.
     * Once Event is determined to be valid, generate Event.
     * Remove that event from the EventCalendar.
     * @param contents Command to be processed into an Event.
     * @return the Event that will be added to the EventCalendar.
     */
    /*
    private Event createEventForRemove(String[] contents) {
        Date date = new Date(contents[1]);
        Timeslot startTime = createTimeSlot(contents[2]);
        Location location = createLocation(contents[3]);
        return new Event(date, startTime, location);
    }

     */

    /**
     * Create a Timeslot for an Event based on user input.
     * Once Event is determined to be valid, create its Timeslot.
     * Turn the String input into a Timeslot enum constant.
     * @param str The string to be converted to Timeslot enum.
     * @return the Timeslot enum constant that was generated.
     */
    /*
    private Timeslot createTimeSlot(String str) {
        if (str.toLowerCase().equals("morning")) {
            return Timeslot.MORNING;
        }
        else if (str.toLowerCase().equals("afternoon")) {
            return Timeslot.AFTERNOON;
        }
        else {
            return Timeslot.EVENING;
        }
    }

     */

    /**
     * Create a Location for an Event based on user input.
     * Once Event is determined to be valid, create its Location.
     * Turn the String input into a Location enum constant.
     * @param str The string to be converted to Location enum.
     * @return the Location enum constant that was generated.
     */
    /*
    private Location createLocation(String str) {
        if (str.toLowerCase().equals("hll114")) {
            return Location.HLL114;
        }
        else if (str.toLowerCase().equals("arc103")) {
            return Location.ARC103;
        }
        else if (str.toLowerCase().equals("be_aud")) {
            return Location.BE_AUD;
        }
        else if (str.toLowerCase().equals("til232")) {
            return Location.TIL232;
        }
        else if (str.toLowerCase().equals("ab2225")) {
            return Location.AB2225;
        }
        else {
            return Location.MU302;
        }
    }

     */

    /**
     * Create a Department for an Event based on user input.
     * Once Event is determined to be valid, create its Department.
     * Turn the String input into a Department enum constant.
     * @param str The string to be converted to Department enum.
     * @return the Department enum constant that was generated.
     */
    /*
    private Department createDepartment(String str) {
        if (str.toLowerCase().equals("cs")) {
            return Department.CS;
        }
        else if (str.toLowerCase().equals("ee")) {
            return Department.EE;
        }
        else if (str.toLowerCase().equals("iti")) {
            return Department.ITI;
        }
        else if (str.toLowerCase().equals("math")) {
            return Department.MATH;
        }
        else {
            return Department.BAIT;
        }
    }

     */
}
