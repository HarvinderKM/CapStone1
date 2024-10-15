package transactions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Transactions {
    public static void makeDeposit () throws IOException {
        Scanner homeScreen = new Scanner(System.in);

        System.out.println("How much are you depositing?");
        double amount = homeScreen.nextDouble();
        homeScreen.nextLine();

        LocalDate date = LocalDate.now();

        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // current time

        System.out.println("Name the vendor");
        String vendor = homeScreen.nextLine();

        System.out.println("Describe the transaction");
        String description = homeScreen.nextLine();


        String transaction = date +"|"+ time + "|" + description + "|"+ vendor + "|"+ amount;
        BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv",true)); // writes a line in a file
        buffer.write(transaction); //this writes the line
        buffer.close(); // will write line on the transaction file

    }
    public static void makePayment () throws IOException {
        Scanner homeScreen = new Scanner(System.in);

        System.out.println("How much are you paying?");
        double amount = homeScreen.nextDouble();
        homeScreen.nextLine();

        LocalDate date = LocalDate.now();

        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // current time

        System.out.println("Name the vendor");
        String vendor = homeScreen.nextLine();

        System.out.println("Describe the transaction");
        String description = homeScreen.nextLine();


        String transaction = "\n" + date +"|"+ time + "|" + description + "|"+ vendor + "|"+ (amount*-1);

        BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv",true)); // writes a line in a file
        buffer.write(transaction); //this writes the line
        buffer.close(); // will write line on the transaction file

    }
    public static void main(String[] args) throws IOException {
      makeDeposit();
      makePayment();

    }
}

