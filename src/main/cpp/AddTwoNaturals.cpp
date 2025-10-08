#include <iostream>

/**
 * Adds two natural numbers.
 *
 * @param num1 The first natural number.
 * @param num2 The second natural number.
 * @return The sum of num1 and num2.
 */
int addNaturalNumbers(int num1, int num2) {
    return num1 + num2;
}

int main() {
    int num1, num2;

    // Prompt user for input
    std::cout << "Enter the first natural number: ";
    std::cin >> num1;

    // Validate input to ensure it's a natural number
    while (num1 <= 0) {
        std::cerr << "Error: Input must be a natural number greater than 0." << std::endl;
        std::cout << "Enter the first natural number: ";
        std::cin >> num1;
    }

    std::cout << "Enter the second natural number: ";
    std::cin >> num2;

    // Validate input to ensure it's a natural number
    while (num2 <= 0) {
        std::cerr << "Error: Input must be a natural number greater than 0." << std::endl;
        std::cout << "Enter the second natural number: ";
        std::cin >> num2;
    }

    // Compute and display the sum
    int sum = addNaturalNumbers(num1, num2);
    std::cout << "The sum of " << num1 << " and " << num2 << " is: " << sum << std::endl;

    return 0;
}
