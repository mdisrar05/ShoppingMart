package com.cts.shoppingmart.dao;

import com.cts.shoppingmart.exceptions.OrderException;
import com.cts.shoppingmart.model.Order;
import java.util.List;

// Data Access Object (DAO) interface for handling order-related database operations
public interface OrderDao {

    // Method to place a new order in the database
    void placeOrder(Order order) throws OrderException;

    // Method to retrieve a list of orders by a specific user ID
    List<Order> getOrdersByUserId(int userId) throws OrderException;

    // Method to retrieve a specific order by its ID
    Order getOrderById(int orderId) throws OrderException;

    // Method to delete an order from the database by its ID
    void deleteOrder(int orderId) throws OrderException;
}
