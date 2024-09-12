/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Autumn Martinez-White
 * Last Updated: 9/11/2024
 */
package martinezwhiten;

import java.util.Random;

/**
 * An object class for a die
 */
public class Die {
    /**
     * Minimum number of sides a die may have
     */
    public static final int MIN_SIDES = 2;
    /**
     * Maximum number of sides a die may have
     */
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random;

    /**
     * Creates a new die and the object Random
     * @param numSides the die has
     */
    public Die(int numSides) {
        this.numSides = numSides;
        random = new Random();
    }

    /**
     * Gets the Die's right side up value
     * @return the right side up value
     * @throws DieNotRolledException if it has not been rolled, has no current value
     */
    public int getCurrentValue() {
        if (currentValue == 0) {
            throw new DieNotRolledException();
        }
        return currentValue;
    }

    /**
     * Rolls the die, gives it a random value from 1 to number of sides
     */
    public void roll() {
        currentValue = random.nextInt(1, numSides + 1);
    }

}