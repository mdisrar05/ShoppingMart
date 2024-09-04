package com.cts.shoppingmart.exceptions;

// Custom exception class for handling user-related errors
public class UserException extends Exception {

    // Constructor to create a UserException 
    public UserException(String message) {
        super(message); // Pass the message to the parent Exception class
    }

    // Constructor to create a UserException 
    public UserException(String message, Throwable cause) {
        super(message, cause); // Pass the message and cause to the parent Exception class
    }
}
