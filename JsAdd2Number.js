function addTwoNumbers(num1, num2) {
  if (num1 <= 0 || num2 <= 0) {
    return "Please enter positive natural numbers.";
  } else {
    return num1 + num2;
  }
}

// Example usage:
let number1 = parseInt(prompt("Enter the first natural number:"));
let number2 = parseInt(prompt("Enter the second natural number:"));

let result = addTwoNumbers(number1, number2);

if (typeof result === 'string') {
  console.log(result);
} else {
  console.log(`The sum is: ${result}`);
}
