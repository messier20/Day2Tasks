package lt.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CompoundInterestCalculator {

    static int amount;
    static int interestRate[] = new int[1];
    static int periodLength;
    static String compoundFrequency;
    static int i = 0;

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
            System.out.println(interestRate.length + " " + interestRate[interestRate.length - 1]);
        }

        for (interestRateIndex = 0; interestRateIndex < interestRate.length; interestRateIndex++) {
            for (index = 0; index < arrayLength; index++) {
                compoundingFrequency =
                        calculateCompoundingFrequency(compoundFrequencyNumber, index + 1, interestRateIndex);

                interestAmountAfterIteration[interestRateIndex][index] = compoundingFrequency - amount;

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

        System.out.print("Amount: ");
        amount = scanner.nextInt();

        do {
            System.out.print("Interest rate (%): ");
            interestRate = Arrays.copyOf(interestRate, i + 1);
            interestRate[i] = scanner.nextInt();
            i++;

        }
        while (!(interestRate[i - 1] == 0));
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
            default:
            case "Y":
                return 1;
        }
    }

}
