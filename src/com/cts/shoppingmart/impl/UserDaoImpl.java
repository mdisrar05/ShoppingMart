package com.cts.shoppingmart.impl;

import com.cts.shoppingmart.dao.UserDao;
import com.cts.shoppingmart.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    // Database connection instance
    private Connection connection;

    // Constructor to initialize the connection
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override // Fetches user details based on username and password
    public User getUserByUsernameAndPassword(String username, String password) {
        // Variable to hold the user object
        User user = null;
        try {
            // SQL query to retrieve user details based on username and password
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
            ps.setString(1, username); // Sets the username parameter
            ps.setString(2, password); // Sets the password parameter
            ResultSet rs = ps.executeQuery(); // Executes the query
            
            // Process the result set
            if (rs.next()) {
                user = new User(); // Creates a new User object
                user.setUserId(rs.getInt("user_id")); // Sets user ID
                user.setUsername(rs.getString("username")); // Sets username
                user.setPassword(rs.getString("password")); // Sets password
                user.setRole(rs.getString("role")); // Sets user role
            }
        } catch (Exception e) {
            e.getMessage(); // Prints exception message for any SQL or connection errors
        }
        return user; // Returns the user object (or null if no match found)
    }
}
