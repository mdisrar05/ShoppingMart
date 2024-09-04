package com.cts.shoppingmart.services;

import com.cts.shoppingmart.exceptions.OrderException;
import com.cts.shoppingmart.model.Order;

//Interface for order-related services

public interface OrderService {
    void placeOrder(Order order) throws OrderException;
    Order getLastOrder(int userId) throws OrderException;
}
