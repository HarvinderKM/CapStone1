package transactions;

import java.util.Scanner;

public class Transactions {
    public static void main(String[] args) {
        Scanner homeScreen = new Scanner(System.in);

        System.out.println("How much are you depositing?");
        double amount = homeScreen.nextDouble();
        homeScreen.nextLine();

        System.out.println("What is today's date? (00/00/0000)");
        String date = homeScreen.nextLine();

        System.out.println("What is the current time? (00:00)");
        String time = homeScreen.nextLine();

        System.out.println("Name the vendor");
        String vendor = homeScreen.nextLine();

        System.out.println("Describe the transaction");
        String description = homeScreen.nextLine();

        System.out.println(date +"|"+ time + "|" + description + "|"+ vendor + "|"+ amount);

    }
}

