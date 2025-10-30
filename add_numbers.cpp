#include <iostream>

int main() {
    int num1, num2, sum;

    std::cout << "Enter two natural numbers: ";
    std::cin >> num1 >> num2;

    // Check if numbers are natural (positive)
    if (num1 <= 0 || num2 <= 0) {
        std::cout << "Please enter positive natural numbers." << std::endl;
        return 1; // Indicate an error
    }

    sum = num1 + num2;

    std::cout << "Sum = " << sum << std::endl;

    return 0;
}