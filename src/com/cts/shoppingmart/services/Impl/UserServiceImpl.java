package com.cts.shoppingmart.services.Impl;

import com.cts.shoppingmart.dao.UserDao;
import com.cts.shoppingmart.exceptions.UserException;
import com.cts.shoppingmart.model.User;
import com.cts.shoppingmart.services.UserService;


public class UserServiceImpl implements UserService {
    // DAO object for accessing user data
    private final UserDao userDao;

    // Constructor to initialize the UserDao
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // Method to handle user login
    @Override
    public User login(String username, String password) throws UserException {
        try {
            // Fetch user details from the DAO using the provided username and password
            return userDao.getUserByUsernameAndPassword(username, password);
        } catch (Exception e) {
            // Wrap SQLException into a UserException and rethrow it
            throw new UserException("Error during user login for username: " + username, e);
        }
    }
}
