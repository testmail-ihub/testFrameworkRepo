/**
 * Provides basic mathematical operations.
 */
public class MathUtils {

    /**
     * Adds two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the sum of num1 and num2
     */
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * Subtracts num2 from num1.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the difference of num1 and num2
     */
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    /**
     * Multiplies two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the product of num1 and num2
     */
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * Divides num1 by num2.
     *
     * @param num1 the dividend
     * @param num2 the divisor
     * @return the quotient of num1 and num2
     * @throws ArithmeticException if num2 is zero
     */
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return num1 / num2;
    }
}
