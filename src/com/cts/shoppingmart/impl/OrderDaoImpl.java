package com.cts.shoppingmart.impl;

import com.cts.shoppingmart.dao.OrderDao;
import com.cts.shoppingmart.exceptions.OrderException;
import com.cts.shoppingmart.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void placeOrder(Order order) throws OrderException {
        String query = "INSERT INTO `Orders` (user_id, order_date, total_price, shipping_address, customer_name) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, order.getUserId());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getShippingAddress());
            ps.setString(5, order.getCustomerName());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new OrderException("Failed to place order.", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) throws OrderException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `Orders` WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setOrderDate(rs.getDate("order_date"));
                    order.setTotalAmount(rs.getDouble("total_price"));
                    order.setShippingAddress(rs.getString("shipping_address"));
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            throw new OrderException("Failed to retrieve orders for user ID " + userId, e);
        }
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) throws OrderException {
        Order order = null;
        String query = "SELECT * FROM `Orders` WHERE order_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setOrderDate(rs.getDate("order_date"));
                    order.setTotalAmount(rs.getDouble("total_price"));
                    order.setShippingAddress(rs.getString("shipping_address"));
                }
            }
        } catch (Exception e) {
            throw new OrderException("Failed to retrieve order with ID " + orderId, e);
        }
        return order;
    }

    @Override
    public void deleteOrder(int orderId) throws OrderException {
        String query = "DELETE FROM `Orders` WHERE order_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new OrderException("Failed to delete order with ID " + orderId, e);
        }
    }
}
