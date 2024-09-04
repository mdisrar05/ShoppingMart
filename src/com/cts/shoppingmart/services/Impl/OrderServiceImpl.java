package com.cts.shoppingmart.services.Impl;

import com.cts.shoppingmart.dao.OrderDao;
import com.cts.shoppingmart.exceptions.OrderException;
import com.cts.shoppingmart.model.Order;
import com.cts.shoppingmart.services.OrderService;

import java.util.List;


 //Provides methods for handling order-related operations.

public class OrderServiceImpl implements OrderService {
    // Data Access Object for interacting with the database
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void placeOrder(Order order) throws OrderException {
        try {
            // Delegate the order placement to the OrderDao
            orderDao.placeOrder(order);
        } catch (Exception e) {
            // Wrap and throw the exception as an OrderException
            throw new OrderException("Error placing order: " + e.getMessage(), e);
        }
    }

    @Override
    public Order getLastOrder(int userId) throws OrderException {
        try {
            // Retrieve the list of orders for the specified user
            List<Order> orders = orderDao.getOrdersByUserId(userId);
            // Check if the list is empty and return null if no orders exist
            if (orders.isEmpty()) {
                return null;
            }
            // Return the last order in the list
            return orders.get(orders.size() - 1);
        } catch (Exception e) {
            // Wrap and throw the exception as an OrderException
            throw new OrderException("Error retrieving last order: " + e.getMessage(), e);
        }
    }
}
