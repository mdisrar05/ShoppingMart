package com.cts.shoppingmart.model;

import java.util.Date;
import java.util.Map;

public class Order {
    // Unique identifier for the order
    private int orderId;
    // ID of the user who placed the order
    private int userId;
    // Date when the order was placed
    private Date orderDate;
    // Total price of the order
    private double total_price;  
    // Shipping address for the order
    private String shippingAddress;
    // Name of the customer placing the order
    private String customerName;
    // Map containing the ordered products and their quantities
    private Map<Product, Integer> orderItems;

    // Setter for orderItems
    public void setOrderItems(Map<Product, Integer> items) {
        this.orderItems = items;
    }

    // Getter and setter for orderId
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Getter and setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter and setter for orderDate
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // Getter and setter for total_price (totalAmount)
    public double getTotalAmount() {
        return total_price;
    }

    public void setTotalAmount(double totalAmount) {
        this.total_price = totalAmount;
    }

    // Getter and setter for shippingAddress
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    // Getter and setter for customerName
    public String getCustomerName() { 
        return customerName; 
    }
    
    public void setCustomerName(String customerName) { 
        this.customerName = customerName; 
    }
}
