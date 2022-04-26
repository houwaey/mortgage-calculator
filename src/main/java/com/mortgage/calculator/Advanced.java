package com.mortgage.calculator;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Advanced {

    // reduce the lines of code of this method (should be less than 20 lines)
    public static void main(String[] args) {

        int principal = 0;
        float monthlyInterest = 0;
        int numberOfPayments = 0;

        Scanner scanner = new Scanner(System.in);

        // redundant while loop
        while (true) {
            System.out.print("Principal [$1k - $1M]: ");
            principal = scanner.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1,000 and 1,000,000");
        }

        // redundant while loop
        while (true) {
            System.out.print("Annual Interest Rate: ");
            float annualInterest = scanner.nextFloat();
            // magic numbers: Min Annual Interest=1, Max Annual Interest=30
            if (annualInterest >= 1 && annualInterest <= 30) {
                // magic numbers: Percent=100, Months in a Year=12
                monthlyInterest = annualInterest / 100 / 12;
                break;
            }
            System.out.println("Enter a value between 1 and 30");
        }

        // redundant while loop
        while (true) {
            System.out.print("Period (Years) [1 - 30]: ");
            int years = scanner.nextByte();
            // magic numbers: Min Year=1, Max Year=30
            if (years >= 1 && years <= 30) {
                // magic numbers: Months in a Year=12
                numberOfPayments = years * 12;
                break;
            }
            System.out.println("Enter a value between 1 and 30.");
        }

        // this formula can be extracted to another method
        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        // this functionality can be extracted to another method
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(Locale.US));
        String mortgageFormatted = numberFormat.format(mortgage);

        System.out.println("Mortgage: " + mortgageFormatted);
    }
}
