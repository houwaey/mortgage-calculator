package com.mortgage.calculator;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Advanced {
    public static final int MIN_YEARS = 1;
    public static final int MAX_YEARS = 30;
    public static final int MIN_INTEREST = 1;
    public static final int MAX_INTEREST = 30;

    /*
    * Principal: 100000
    * Annual Interest Rate: 3.92
    * Period (Years): 30
    * Mortgage: US$472.81
    * */

    public static void main(String[] args) {

        int principal;
        float annualInterest;
        int years;

        Scanner scanner = new Scanner(System.in);

        principal = getPrincipal(scanner);
        annualInterest = getAnnualInterest(scanner);
        years = getYears(scanner);

        String mortgage = formatMortgage(calculateMortgage(principal, annualInterest, years));
        System.out.println("Mortgage: " + mortgage);
    }

    private static int getYears(Scanner scanner) {
        int years;
        while (true) {
            System.out.print("Period (Years) [1 - 30]: ");
            years = scanner.nextByte();
            if (years >= MIN_YEARS && years <= MAX_YEARS)
                break;
            System.out.println("Enter a value between 1 and 30.");
        }
        return years;
    }

    private static float getAnnualInterest(Scanner scanner) {
        float annualInterest;
        while (true) {
            System.out.print("Annual Interest Rate [1.00 - 30.00]: ");
            annualInterest = scanner.nextFloat();
            if (annualInterest >= MIN_INTEREST && annualInterest <= MAX_INTEREST)
                break;
            System.out.println("Enter a value between 1 and 30");
        }
        return annualInterest;
    }

    private static int getPrincipal(Scanner scanner) {
        int principal;
        while (true) {
            System.out.print("Principal [$1k - $1M]: ");
            principal = scanner.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1,000 and 1,000,000");
        }
        return principal;
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
