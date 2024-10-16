package transactions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

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
    public static void ledger () throws IOException { //method for ledger
        //ledgar will need to display new screen
        // screen must have options
        // display the following options
        System.out.println("LEDGAR");
        System.out.println("Choose An Letter from the following");
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
            case "a":
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
            case "d": makeDeposit();
                break;
            case "P":
            case "p": makePayment();
                break;
            case "L": ledger(); // if the user inputs L or L then the ledgar method will pop up
            case"l": ledger();
                break;
            case "X":
                System.out.println("okay bye!");
                break;
            default:
                System.out.println("Invalid Option - Try Again!");
                board();
    }}
    public static void main(String[] args) throws IOException { //main method
        board();

    }
}

