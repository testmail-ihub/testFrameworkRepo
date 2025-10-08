def add_natural_numbers(num1: int, num2: int) -> int:
    """
    Adds two natural numbers.

    Args:
        num1 (int): The first natural number.
        num2 (int): The second natural number.

    Returns:
        int: The sum of num1 and num2.
    """
    return num1 + num2

def get_natural_number(prompt: str) -> int:
    """
    Prompts the user for a natural number and validates the input.

    Args:
        prompt (str): The prompt to display to the user.

    Returns:
        int: A natural number entered by the user.
    """
    while True:
        try:
            value = int(input(prompt))
            if value > 0:
                return value
            else:
                print("Error: Input must be a natural number greater than 0.")
        except ValueError:
            print("Error: Invalid input. Please enter a natural number.")

def main() -> None:
    """
    Prompts the user for two natural numbers, computes their sum, and displays the result.
    """
    num1 = get_natural_number("Enter the first natural number: ")
    num2 = get_natural_number("Enter the second natural number: ")

    sum_of_numbers = add_natural_numbers(num1, num2)
    print(f"The sum of {num1} and {num2} is: {sum_of_numbers}")

if __name__ == "__main__":
    main()
