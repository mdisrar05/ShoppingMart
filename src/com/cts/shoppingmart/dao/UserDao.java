package com.cts.shoppingmart.dao;

import com.cts.shoppingmart.exceptions.UserException;
import com.cts.shoppingmart.model.User;
public interface UserDao {
	User getUserByUsernameAndPassword(String username, String password) throws UserException; // gets username and pwd
}
