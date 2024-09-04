package com.cts.shoppingmart.exceptions;

// Custom exception class for handling order-related errors
public class OrderException extends Exception {

    // Constructor to create an OrderException
    public OrderException(String message) {
        super(message); // Pass the message to the parent Exception class
    }

    // Constructor to create an OrderException 
    public OrderException(String message, Throwable cause) {
        super(message, cause); // Pass the message and cause to the parent Exception class
    }
}
