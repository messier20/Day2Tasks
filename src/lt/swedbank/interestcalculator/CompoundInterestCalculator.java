package lt.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CompoundInterestCalculator {

    static float amount;
    static float interestRate[] = new float[1];
    static int periodLength;
    static String compoundFrequency;
    static int i = 0;
    static String check;

    public static void main(String[] args) {

        int compoundFrequencyNumber;
        int arrayLength;
        int index;
        int interestRateIndex;
        double compoundingFrequency = 0;
        double[][] interestAmounts;
        double[][] interestAmountAfterIteration;

        readInput();

        compoundFrequencyNumber = findFrequencyNumber(compoundFrequency);

        arrayLength = compoundFrequencyNumber * periodLength;
        interestAmounts = new double[arrayLength][interestRate.length];
        interestAmountAfterIteration = new double[arrayLength][interestRate.length];

        if (interestRate[interestRate.length - 1] == 0) {
            interestRate = Arrays.copyOf(interestRate, interestRate.length - 1);
        }

        for (interestRateIndex = 0; interestRateIndex < interestRate.length; interestRateIndex++) {
            for (index = 0; index < arrayLength; index++) {
                compoundingFrequency =
                        calculateCompoundingFrequency(compoundFrequencyNumber, index + 1, interestRateIndex);

                interestAmountAfterIteration[interestRateIndex][index] =
                        compoundingFrequency - amount;

                if (index == 0) {
                    interestAmounts[interestRateIndex][0] = interestAmountAfterIteration[interestRateIndex][index];

                } else {
                    interestAmounts[interestRateIndex][index] =
                            interestAmountAfterIteration[interestRateIndex][index] - interestAmountAfterIteration[interestRateIndex][index - 1];
                }

                System.out.printf(" %.2f", interestAmounts[interestRateIndex][index]);
            }

            System.out.println();

        }

    }

    private static double calculateCompoundingFrequency(int compoundFrequencyNumber, int year, int interestRateIndex) {
        double powerBase = 1 + (interestRate[interestRateIndex] * 0.01) / compoundFrequencyNumber;
        double powerExponent = year * compoundFrequencyNumber;

        return amount * Math.pow(powerBase, powerExponent);
    }

    private static void readInput() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Amount: ");
            check = scanner.nextLine();
            if (!isAmountValid()) {
                printInvalidInput();
            } else amount = Float.parseFloat(check);

        }
        while (!isAmountValid());


        do {
            System.out.print("Interest rate (%): ");
            check = scanner.nextLine();
            if (isInterestRateValid()) {
                interestRate = Arrays.copyOf(interestRate, i + 1);
                interestRate[i] = Float.parseFloat(check);
                i++;
            } else printInvalidInput();


        }
        while (!isInterestRateValid() || !(interestRate[i - 1] == 0));

        do {
            System.out.print("Period length(years): ");
            check = scanner.nextLine();
            if (!isPeriodLengthValid()) {
                printInvalidInput();
            } else periodLength = Integer.parseInt(check);

        }
        while (!isPeriodLengthValid());

        do {
            System.out.print("Compound frequency: ");
            compoundFrequency = scanner.nextLine();
            if (!isCompoundFrequencyValid()) {
                printInvalidInput();
            }
        }
        while (!isCompoundFrequencyValid());
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
            default:
                return 1;
        }
    }

    private static boolean isAmountValid() throws NumberFormatException {
        try {

            if (Float.parseFloat(check) > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException wrongNumberFormat) {

        }
        return true;
    }

    private static boolean isPeriodLengthValid() throws NumberFormatException {
        try {
            if (Integer.parseInt(check) > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException wrongFormat) {
        }
        return false;
    }

    private static boolean isCompoundFrequencyValid() {
        switch (compoundFrequency) {
            case "D":
                return true;
            case "W":
                return true;
            case "M":
                return true;
            case "Q":
                return true;
            case "H":
                return true;
            case "Y":
                return true;
            default:
                return false;
        }
    }

    private static boolean isInterestRateValid() throws NumberFormatException {
        try {
            if (Float.parseFloat(check) >= 0 && Float.parseFloat(check) <= 100) {
                return true;
            } else return false;
        } catch (NumberFormatException invalidNumber) {
        }


        return false;
    }

    private static void printInvalidInput() {
        System.out.println("Invalid input! Try again!");
    }

}
