package transactions;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main { //main class
    public static void makeDeposit () throws IOException { //make deposit method
        Scanner homeScreen = new Scanner(System.in); // scanner for getting input

        System.out.println("How much are you depositing?"); //print out how much is a person going to deposit
        double amount = homeScreen.nextDouble(); // the amount in decimals they're depositing
        homeScreen.nextLine(); // will get what's left at the next line

        LocalDate date = LocalDate.now(); // will give us the current date

        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // current time in seconds

        System.out.println("Name the vendor"); // asking who is the vendor
        String vendor = homeScreen.nextLine(); // vendor is a string

        System.out.println("Describe the transaction"); // asking for a description for the transaction
        String description = homeScreen.nextLine(); // description is a string


        String transaction = date +"|"+ time + "|" + description + "|"+ vendor + "|"+ amount; // the format of the transaction with all variables
        BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv",true)); // writes a line in transactions.csv file
        buffer.write(transaction); //this writes the line
        buffer.close(); // will write line on the transaction file

    }

    public static void allEntries () throws IOException { //
        // This method to display all entries
        //pass in the array list
        // Transaction is my datatype
        //transactions name anything
        ArrayList<Transaction> entry = get_transaction(); // gget_transactiosn returns all the transacations assigned as an array
        for (int i = 0; i <= entry.size(); i++) { // i = 0 , entry size until i is less or equal to the size of the file and prints all entries
            Transaction p = entry.get(i);// gets 1st transaction, then printing that transaction below
            System.out.println(p.getDate() + "\n " + p.getTime() + "\n " + p.getDescription() + " \n" + p.getVendor() + " \n" + p.getAmount());
        }
    }

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
            case "d":
                makePayment(); // change this to display only positive entries (create a method)
                break;
            case "P":
            case "p": // create a method for displaying all negative transactions
                break;
            case "R":
            case "r": // open a new screen that allows user to run predefined reports or a custom search
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
    public static void main(String[] args) throws IOException { //main method
        board();

    }
}

