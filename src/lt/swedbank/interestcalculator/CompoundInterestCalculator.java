package lt.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CompoundInterestCalculator {

    //I would not suggest using global variables here.
    //Better solution would be to create separate methods for every input: ex.: getAmount(), getInterestRate(), etc.

    //M: I agree. But on monday we "didn't know" these methods so I was trying to get the result without getters and setters.
    //Should I change now?
    static int amount;
    static int interestRate;
    static int periodLength;
    static String compoundFrequency;

    //Application is not behaving as expected.
    //If "periodLength" = 3 and "compoundFrequency" = "M", it is expected that program would print:
    //  3 "Interest amount after year ..." lines
    //  +
    //  "InterestAmounts" array containing 36 elements (3*12)
    //
    // Since in task's description this is not clearly stated, I'll let it slide this time :)

    //M: I tried to fix it. Is it behaving as should be right now?

    public static void main(String[] args) {
        //Do not declare your local variables separately from their initialization.
        //Variables should be declared where they're first used/needed.
        //M: Mindaugas told me completely opposite, I don't understand.
        int compoundFrequencyNumber;
        int arrayLength ;
        int index;
        double compoundingFrequency = 0;
        double[] interestAmountAfterYear;
        double[] interestAmounts;
        double[] interestAmountAfterIteration;
        String arrayString;

        readInput();

        compoundFrequencyNumber = findFrequencyNumber(compoundFrequency);

        arrayLength = compoundFrequencyNumber * periodLength;
        interestAmounts = new double[arrayLength];
        interestAmountAfterYear = new double[periodLength + 1];
        interestAmountAfterIteration = new double[arrayLength];

        for (index = 0 ; index < arrayLength; index++) {
            compoundingFrequency = calculateCompoundingFrequency(compoundFrequencyNumber, index + 1);
            interestAmountAfterIteration[index] = compoundingFrequency - amount;

            if (index == 0) {
                interestAmounts[0] = interestAmountAfterIteration[index];
            }
            else {
                interestAmounts[index] = interestAmountAfterIteration[index] - interestAmountAfterIteration[index - 1];
            }

        }

        for (index = 1; index <= periodLength; index++ ) {
            compoundingFrequency = calculateCompoundingFrequency(compoundFrequencyNumber, index);
            interestAmountAfterYear[index] = compoundingFrequency - amount;

            //M: Does this print correctly?
            System.out.print("Interest amount after year " + (index));
            System.out.printf(": %.2f\n", interestAmountAfterYear[index]);
        }

        arrayString = Arrays.toString(interestAmounts);

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
            //"case "Y":" can be removed, since "default:" is already returning "1".
            //M: Now I switched it to default if someone would like to run the code with 'Y' but don't know that 1 year is default. Or is it also wrong?
            default:
                case "Y":
                return 1;
        }
    }

}
