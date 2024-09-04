package com.cts.shoppingmart.services;
import com.cts.shoppingmart.exceptions.UserException;
import com.cts.shoppingmart.model.User;

//Interface for user-related services

public interface UserService {
    User login(String username, String password) throws UserException;
}
