import Foundation

func addTwoNumbers(num1: Int, num2: Int) -> String {
    if num1 <= 0 || num2 <= 0 {
        return "Please enter positive natural numbers."
    } else {
        return "The sum is: \(num1 + num2)"
    }
}

// Example usage:
print("Enter the first natural number:")
if let input1 = readLine(), let number1 = Int(input1) {
    print("Enter the second natural number:")
    if let input2 = readLine(), let number2 = Int(input2) {
        let result = addTwoNumbers(num1: number1, num2: number2)
        print(result)
    } else {
        print("Invalid input for the second number.")
    }
} else {
    print("Invalid input for the first number.")
}
