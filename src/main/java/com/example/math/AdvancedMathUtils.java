/**
 * Advanced mathematical utilities that extend the functionality of MathUtils.
 * Provides static methods for power (exponentiation) and square root calculations.
 */
public class AdvancedMathUtils extends MathUtils {

    /**
     * Calculates the power of a number (exponentiation).
     *
     * @param base     the base number
     * @param exponent the exponent
     * @return the result of base raised to the power of exponent
     */
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Calculates the square root of a number.
     *
     * @param number the number to calculate the square root for
     * @return the square root of the number
     * @throws ArithmeticException if the input number is negative
     */
    public static double squareRoot(double number) {
        if (number < 0) {
            throw new ArithmeticException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(number);
    }
}
