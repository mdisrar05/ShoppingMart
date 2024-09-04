package com.cts.shoppingmart.exceptions;

// Custom exception class for handling product-related errors
public class ProductException extends Exception {

    // Constructor to create a ProductException 
    public ProductException(String message) {
        super(message); // Pass the message to the parent Exception class
    }

    // Constructor to create a ProductException 
    public ProductException(String message, Throwable cause) {
        super(message, cause); // Pass the message and cause to the parent Exception class
    }
}
