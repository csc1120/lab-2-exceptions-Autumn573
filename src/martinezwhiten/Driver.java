/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Autumn Martinez-White
 * Last Updated: 9/11/2024
 */
package martinezwhiten;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver class for die, rolls a specified number of dice
 * a specified number of times, then records how many times
 * every combination was rolled.
 */
public class Driver {
    /**
     * The minimum number of dice
     */
    public static final int MIN_DICE = 2;
    /**
     * The maximum number of dice
     */
    public static final int MAX_DICE = 10;

    public static void main(String[] args) {
        int[] input = getInput();
        Die[] dice = createDice(input[0], input[1]);
        int[] rollsStats = rollDice(dice, input[1], input[2]);
        int max = findMax(rollsStats);
        report(input[0], rollsStats, max);
    }

    private static int[] getInput() {
        Scanner in = new Scanner(System.in);
        boolean active = true;
        int[] configArr = new int[3];
        while (active) {
            System.out.println("Please enter the number of dice to roll, "
                    + "how many sides the dice have,"
                    + "\nand how many rolls to complete, separating the values by a space.\n"
                    + "Example: \"2 6 1000\"\n");
            System.out.print("Enter configuration: ");
            try {
                configArr[0] = in.nextInt();
                configArr[1] = in.nextInt();
                configArr[2] = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
                in.nextLine();
            }
            if (configArr[0] <= MAX_DICE && configArr[0] >= MIN_DICE
                    && configArr[1] >= Die.MIN_SIDES
                    && configArr[1] <= Die.MAX_SIDES
                    && configArr[2] != 0) {
                active = false;
            } else if (configArr[0] < MIN_DICE || configArr[0] > MAX_DICE) {
                System.out.println("Bad die creation: Illegal number of dice: " + configArr[0] +
                            "\nMust be between " + MIN_DICE + " and " + MAX_DICE + ".");
            } else if (configArr[1] < Die.MIN_SIDES || configArr[1] > Die.MAX_SIDES) {
                System.out.println("Bad die creation: Illegal number of sides: " + configArr[1]);
            }
        }
        return configArr;
    }

    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int minValue = dice.length;
        int maxValue = (dice.length * numSides) + 1;
        int[] index = new int[maxValue - minValue];
        int totals = 0;
        for (int i = 0; i < numRolls; i++) {
            for (Die die : dice) {
                die.roll();
                totals += die.getCurrentValue();
                //index[die.getCurrentValue()]++;
            }
            index[totals - minValue]++;
            totals = 0;
        }
        return index;
    }

    private static int findMax(int[] rolls) {
        int maxNumRolls = rolls[0];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] > maxNumRolls) {
                maxNumRolls = rolls[i];
            }
        }
        return maxNumRolls;
    }

    private static void report(int numDice, int[] rolls, int max) {
        int current;
        final int tenPercent = 10;
        float scale = (float) max / tenPercent;
        String stars = "";
        for (int i = 0; i < rolls.length; i++) {
            current = i + numDice;
            if (rolls[i] == max) {
                String status = current + ": " + rolls[i];
                System.out.printf("%-12s", status);
                System.out.println("**********");
            } else {
                int numStars = Math.round(rolls[i] / scale);
                for (int j = 0; j < numStars; j++) {
                    stars += "*";
                }
                String status = current + ": " + rolls[i];
                System.out.printf("%-12s", status);
                System.out.println(stars);
                stars = "";
            }
        }
    }
}