using System;

public class AddTwoNumbers
{
    public static void Main(string[] args)
    {
        Console.WriteLine("Enter the first natural number:");
        int num1 = Convert.ToInt32(Console.ReadLine());

        Console.WriteLine("Enter the second natural number:");
        int num2 = Convert.ToInt32(Console.ReadLine());

        if (num1 <= 0 || num2 <= 0)
        {
            Console.WriteLine("Please enter positive natural numbers.");
        }
        else
        {
            int sum = num1 + num2;
            Console.WriteLine($"The sum is: {sum}");
        }
    }
}
