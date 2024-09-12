/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Autumn Martinez-White
 * Last Updated: 9/11/2024
 */
package martinezwhiten;

/**
 * An exception thrown when a die has no current value
 */
public class DieNotRolledException extends RuntimeException {
    /**
     * Gets the exceptions error message
     * @return the error message
     */
    public String getMessage() {
        return "The die has not been rolled";
    }
}
