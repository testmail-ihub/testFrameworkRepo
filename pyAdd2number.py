def add_natural_numbers(num1, num2):
    if num1 <= 0 or num2 <= 0:
        return "Please enter positive natural numbers."
    else:
        return num1 + num2

if __name__ == "__main__":
    try:
        number1 = int(input("Enter the first natural number: "))
        number2 = int(input("Enter the second natural number: "))
        result = add_natural_numbers(number1, number2)
        print(f"The sum is: {result}")
    except ValueError:
        print("Invalid input. Please enter integers.")