package com.mortgage.calculator;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Advanced {

    /*
    * Principal: 100000
    * Annual Interest Rate: 3.92
    * Period (Years): 30
    * Mortgage: US$472.81
    * */

    // reduce the lines of code of this method (should be less than 20 lines)
    public static void main(String[] args) {

        int principal = 0;
        float annualInterest = 0;
        int years = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Principal [$1k - $1M]: ");
            principal = scanner.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1,000 and 1,000,000");
        }

        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterest = scanner.nextFloat();
            // magic numbers: Min Annual Interest=1, Max Annual Interest=30
            if (annualInterest >= 1 && annualInterest <= 30)
                break;
            System.out.println("Enter a value between 1 and 30");
        }

        while (true) {
            System.out.print("Period (Years) [1 - 30]: ");
            years = scanner.nextByte();
            // magic numbers: Min Year=1, Max Year=30
            if (years >= 1 && years <= 30)
                break;
            System.out.println("Enter a value between 1 and 30.");
        }

        String mortgage = formatMortgage(calculateMortgage(principal, annualInterest, years));
        System.out.println("Mortgage: " + mortgage);
    }

    public static double calculateMortgage(int principal, float annualInterest, int years) {
        float monthlyInterest = annualInterest / 100 / 12;
        int numberOfPayments = years * 12;

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    private static String formatMortgage(double mortgage) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(Locale.US));
        return numberFormat.format(mortgage);
    }

}
