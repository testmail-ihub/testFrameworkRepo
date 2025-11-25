package com.example.bankingapp;

public class PaymentTest {
    public static void main(String[] args) {
        Payment[] payments = new Payment[2];
        payments[0] = PaymentFactory.getPayment("creditcard", 100.0, "USD");
        payments[1] = PaymentFactory.getPayment("upi", 50.0, "INR");

        for (Payment payment : payments) {
            payment.processPayment();
        }
    }
}
