package transactions;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

public class Main { //main class
    public static void makeDeposit () throws IOException {
        //public = method is accessible for the public
        // static = method belongs to the class itself (not objects) accessed through class name
        // void = method doesn't return any value
        // makeDeposit() = name of method
        // throws IOException = a checked exception thrown when there's an input/output operation failure - we need to catch this by usign TRY-CATCH

        try{ //adding a try-catch function to
            Scanner homeScreen = new Scanner(System.in); // scanner for getting input

            System.out.println("How much are you depositing?"); //print out how much is a person going to deposit
            double amount = homeScreen.nextDouble(); // the amount in decimals they're depositing
            homeScreen.nextLine(); // will get what's left over at the next line

            LocalDate date = LocalDate.now(); // will give us the current date
//Osmig helped me figure out the "TruncatedTo(ChronoUnit.SECONDS) so I don't get nanoseconds
            LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // current time in seconds
            // time is variable
            //LocalTime is a class that gives time in hr,min,sec,nanosec
            // LocalTime.now() = method returns current time
            // .truncatedto(ChronoUnit.SECONDS) = time will show up to seconds

            System.out.println("Name the vendor"); // asking who is the vendor
            String vendor = homeScreen.nextLine(); // vendor is a string

            System.out.println("Describe the transaction"); // asking for a description for the transaction
            String description = homeScreen.nextLine(); // description is a string


            String transaction = "\n" + date +"|"+ time + "|" + description + "|"+ vendor + "|"+ amount; // the format of the transaction with all variables
            BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv",true)); // writes a line in transactions.csv file
            // BufferWriter writes to the csv file and appends data to file, stores data temp , best for records
            buffer.write(transaction); //this writes the line
            buffer.close(); // will write line on the transaction file

            System.out.println("Transaction has been Processed Successfully, GoodBye!");

        } //make deposit methodon google why the execption wasn't printing what i said, i was missing the InputMismatchException
        //I searched
        catch (InputMismatchException e){
            System.out.println("AN ERROR OCCURRED, TRY AGAIN");
        }
            catch (IOException e) {
        }
            // this pops up if the IOException error
            System.out.println("AN ERROR OCCURRED, TRY AGAIN");
        }

    public static void allEntries () throws IOException {
        // This method to display all entries
        //pass in the array list
        // Transaction is my datatype
        //transactions name anything
        ArrayList<Transaction> entry = get_transaction(); // gget_transactiosn returns all the transacations assigned as an array
       int counter = 1;
        for (int i = 0; i < entry.size(); i++) { // i = 0 , entry size until i is less o the size of the file and prints all entries
            Transaction p = entry.get(i);// gets 1st transaction, then printing that transaction below
            System.out.println("Transaction " + counter); // lists transaction #
            System.out.println(p.getDate() + "\n " + p.getTime() + "\n " + p.getDescription() + " \n" + p.getVendor() + " \n" + p.getAmount());
            counter++; //incrememts the count of the transaction
        }
    }
    public static void negPayments(ArrayList<Transaction> transactions){ //new method for payments
        int counter = 1;
        for (Transaction d: transactions) { //transactions is the argument passed into the function
            if (d.getAmount()<0){ // will only bring us the negative transactions
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());
                counter++; //increments the count of the transaction
            }
    }}
    public static void allDeposits (ArrayList<Transaction> transactions){ //new method for deposits
        int counter = 1;
        for (Transaction d: transactions) { //transactions is the argument passed into the function
            if (d.getAmount()>0){ // will only bring us the negative transactions
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());
                counter++; //increments the count of the transaction
            }
        }}


//Osmig helped explain how the ledgar screen works
    public static void ledger () throws IOException { //method for ledger
        //ledgar will need to display new screen
        // screen must have options
        // display the following options
        System.out.println("LEDGAR"); // HAVE THE FOLLOWING OPTIONS POP UP SLOWLY
        System.out.println("Choose An Option from the following (input letter)");
        System.out.println("A - Displays All Entries" );
        System.out.println("D - Display only deposits into the account");
        System.out.println("P - Payments, display only negative entries");
        System.out.println("R -  New screen that allows user to run predefined reports or run custom search ");
        System.out.println("H - Home Screen");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch //choose an option
        (input) {
            case "A":
            case "a": allEntries(); // displays all entries
                ; // change this display all transactions (create a method?)
                break;
            case "D":
            case "d": allDeposits(get_transaction()); // get transactions is returnign the transaction array
                // change this to display only positive entries (create a method)
                break;
            case "P":
            case "p": // create a method for displaying all negative transactio/ns
                negPayments(get_transaction()); // displays negpayments method , gets the transactions
                break;
            case "R":
            case "r": reports();
                break;
            case "H":
            case "h": board(); // go back to the home screen
                break;
            default: // choose an invalid option than the following
                System.out.println("Invalid Option - Try Again!"); //this will get printed
                ledger(); //will go back to the ledgar screen
        }}

//void means do not return anything
    // Arraylist will retirn an array list that has transactions inside of it
    // Transaction = datatype , arraay of transaction
    public static ArrayList<Transaction> get_transaction() throws IOException //creates a method for ALL TRANSACTIONS / each line
    {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //returning a transaction datatype, making a new array called transactions , everything split fron transaction file will be added into this array
        BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv")); // reads lines in transactions file
        String input;

        while((input = bufferedReader.readLine()) != null){ // while loop will go through all the lines in csv file
            String[] item = input.split(Pattern.quote("|")); // splitting the string 1st line of the csv file , then assigning everything into a array of string , [] = array
            //^^
            Transaction transaction = new Transaction (LocalDate.parse(item[0]), LocalTime.parse(item [1]), item [2], item[3],Double.parseDouble(item[4])); // splits each line by item in order of line
            // position 0 is date, 1 is time , 2 is
            //
            transactions.add(transaction);
        }
        Collections.sort(transactions, Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime)); // sorting earliest date to latest, then compares earliest time
        return transactions; // returns
    }


    public static void makePayment () throws IOException { // payment method
        Scanner homeScreen = new Scanner(System.in); // takes in user input

        System.out.println("How much are you paying?"); // ask user for input
        double amount = homeScreen.nextDouble(); // take in input
        homeScreen.nextLine();

        LocalDate date = LocalDate.now();

        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // current time

        System.out.println("Name the vendor");
        String vendor = homeScreen.nextLine();

        System.out.println("Describe the transaction");
        String description = homeScreen.nextLine();


        String transactions = "\n" + date +"|"+ time + "|" + description + "|"+ vendor + "|"+ (amount*-1);

        BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv",true)); // writes a line in a file
        buffer.write(transactions); //this writes the line
        buffer.close(); // will write line on the transaction file

    }
    public static void board() throws IOException { //Main Screen w. Options
        System.out.println("BANK");
        System.out.println("Choose An Option from the following");
        System.out.println("D - Deposits" );
        System.out.println("P - Make A Payment");
        System.out.println("L - Ledger");
        System.out.println("X - Exit");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch //choose an option
        (input){
            case "D":
            case "d": makeDeposit(); // deposit method pops up
                break;
            case "P":
            case "p": makePayment(); // payment method pops up
                break;
            case "L": ledger(); // if the user inputs L or L then the ledgar method will pop up
            case"l": ledger();
                break;
            case "X":
            case "x":
                System.out.println("okay bye!"); // tell user bye
                break; // exit screen
            default:
                System.out.println("Invalid Option - Try Again!");
                board(); // invalid option go back to the main board
    }}
    public static void reports () throws IOException {
        System.out.println("BANK"); // fix this
        System.out.println("Choose An Option from the following");
        System.out.println("1 - Month to Date " );
        System.out.println("2 - previous month ");
        System.out.println("3 - year to date ");
        System.out.println("4 - previous year");
        System.out.println("5 - search by vendor");
        System.out.println("0 - Back");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.nextLine();

        switch //choose an option
        (input) {
            case 1: MonthToDate(get_transaction());
                break;
            case 2: previousMonth();
                break;
            case 3: yearToDate();
                break;
            case 4:previousYear();
                break;
            case 5: vendor(get_transaction());
                break; // exit screen
            case 0: ledger();
            default:
        }
    }
    // osmig helped explain to me how arrayLists work in the vendor
    public static void vendor (ArrayList<Transaction> transactions){ //search by vendor , search my name , all transaction w name pop up
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter vendor");
        String vendor = scanner.nextLine();
        int counter = 1;
        for (Transaction d: transactions) { //transactions is the argument passed into the function
            if (d.getVendor().equals(vendor)){ // checks if vendor in file is same as input we passed in
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());
                counter++; //increments the count of the transaction
            }
        }
    }
    public static void MonthToDate (ArrayList<Transaction> transactions){ // new method for month to date
        int currentMonth = LocalDate.now().getMonthValue(); //gets int alue of the current month
        int counter = 1;
        for (Transaction d: transactions) { //for loop,
            int monthForCsv = d.getDate().getMonthValue(); // gettign the int value of the month in the csv file
            if (currentMonth == monthForCsv){ // checking if both months are the same, current and csv
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());

                counter++; //increments the count of the transaction
            }
        }
    }
// osmig helped me fix why no transactions were showing up when I put -1 , had no transactions from the previous month in my transactions
    public static void previousMonth () throws IOException { // new method for previous month
        int currentMonth = LocalDate.now().getMonthValue(); //gets int value of the current month
        currentMonth = currentMonth -1; // gives us current month minus 1 which is previous month
        ArrayList<Transaction> month = get_transaction (); // this assigns the transaction into a new one
        int counter = 1; // counts
        for (Transaction d: month) { //for loop, transaction d is current item in month array,
            int monthForCsv = d.getDate().getMonthValue(); // gettign the int value of the month in the csv file
            if (currentMonth == monthForCsv) { // checking if both months are the same, current and csv
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());

                counter++; //increments the count of the transaction
            }
        }
    }

    public static void yearToDate () throws IOException { // new method for current year
        int currentYear = LocalDate.now().getYear(); //gets int value of the current year
        ArrayList<Transaction> year = get_transaction (); // this assigns the transaction into a new one
        int counter = 1; // counts
        for (Transaction d: year) { //for loop, transaction d is current item in month array,
            int yearForCsv = d.getDate().getYear(); // gettign the int value of the month in the csv file
            if (currentYear == yearForCsv) { // checking if both years are the same, current and csv, it
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());

                counter++; //increments the count of the transaction
            }
        }}
    public static void previousYear () throws IOException { // new method for current year
        int currentYear = LocalDate.now().getYear(); //gets int value of the current year
        ArrayList<Transaction> year = get_transaction (); // this assigns the transaction into a new one
        int counter = 1; // counts
        for (Transaction d: year) { //for loop, transaction d is current item in month array,
            int yearForCsv = d.getDate().getYear(); // gettign the int value of the month in the csv file
            if ((currentYear -1) == yearForCsv) { // checking if both years are the same, current and csv, it
                System.out.println("Transaction " + counter); // lists transaction #
                System.out.println(d.getDate() + "\n " + d.getTime() + "\n " + d.getDescription() + " \n" + d.getVendor() + " \n" + d.getAmount());

                counter++; //increments the count of the transaction
            }
        }}

    public static void main(String[] args) throws IOException { //main method
        board();

    }
}

