package lt.swedbank.itacademy.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CompoundInterestCalculator {

    static int amount;
    static int interestRate;
    static int periodLength;
    static String compoundFrequency;

    public static void main(String[] args) {

        int compoundFrequencyNumber;
        int arrayLength;
        int arrayIndex = 0;
        double compoundingFrequency = 0;
        double[] InterestAmountAfterYear;
        double[] InterestAmounts;
        String arrayString;

        readInput();

        compoundFrequencyNumber = findFrequencyNumber(compoundFrequency);

        arrayLength = compoundFrequencyNumber * periodLength;
        InterestAmounts = new double[arrayLength];
        InterestAmountAfterYear = new double[arrayLength];

        for (int i = 1; i <= arrayLength; i++) {

            compoundingFrequency = calculateCompoundingFrequency(compoundFrequencyNumber, i);

            InterestAmountAfterYear[arrayIndex] = compoundingFrequency - amount;

            if (i == 1) {
                InterestAmounts[0] = InterestAmountAfterYear[arrayIndex];
            }

            if (i > 1) {
                InterestAmounts[arrayIndex] = InterestAmountAfterYear[arrayIndex] - InterestAmountAfterYear[arrayIndex - 1];
            }

            System.out.printf("Interest amount after year " + i + ": %.2f\n", InterestAmountAfterYear[arrayIndex]);

            arrayIndex++;
        }

        arrayString = Arrays.toString(InterestAmounts);

        System.out.println(arrayString);
        System.out.printf("Total amount: %.2f\n", compoundingFrequency);

    }

    private static double calculateCompoundingFrequency(int compoundFrequencyNumber, int year) {
        double powerBase = 1 + (interestRate * 0.01) / compoundFrequencyNumber;
        double powerExponent = year * compoundFrequencyNumber;

        return amount * Math.pow(powerBase, powerExponent);
    }

    private static void readInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Amount: ");
        amount = scanner.nextInt();

        System.out.print("Interest rate (%): ");
        interestRate = scanner.nextInt();

        System.out.print("Period length(years): ");
        periodLength = scanner.nextInt();

        System.out.print("Compound frequency: ");
        compoundFrequency = scanner.next();
    }

    private static int findFrequencyNumber(String compoundFrequency) {

        switch (compoundFrequency) {
            case "D":
                return 365;
            case "W":
                return 52;
            case "M":
                return 12;
            case "Q":
                return 4;
            case "H":
                return 2;
            case "Y":
                return 1;
            default:
                return 1;
        }
    }

}
