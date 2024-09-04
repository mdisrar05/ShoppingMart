package com.cts.shoppingmartjdbc;

import com.cts.shoppingmart.model.Product;
import com.cts.shoppingmart.model.Order;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    // Map to store products and their quantities in the cart
    private Map<Product, Integer> cartItems;

    // Constructor to initialize the cart
    public Cart() {
        cartItems = new HashMap<>();
    }

    // Method to add a product to the cart with the specified quantity
    public void addProduct(Product product, int quantity) {
        // If the product is already in the cart, update the quantity
        // Otherwise, add the product with the specified quantity
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
    }

    // Method to display the contents of the cart
    public void displayCart() {
        System.out.println("\nYour Cart:");
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            double totalAmount = 0;
            // Iterate over the cart items and calculate the total amount
            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double subtotal = product.getPrice() * quantity;
                System.out.println(product.getName() + " - ₹" + product.getPrice() + " x " + quantity + " = ₹" + subtotal);
                totalAmount += subtotal;
            }
            System.out.println("Total: ₹" + totalAmount);
        }
    }

    // Method to generate an order from the cart items
    public Order generateOrder(String shippingAddress) {
        Order order = new Order();
        order.setOrderItems(new HashMap<>(cartItems));  // Sets the cart items in the order
        order.setTotalAmount(calculateTotal());  // Sets the total amount in the order
        order.setShippingAddress(shippingAddress);  // Sets the shipping address
        order.setOrderDate(new java.util.Date());  // Sets the current date as the order date
        return order;
    }

    // Helper method to calculate the total amount of the cart
    private double calculateTotal() {
        double totalAmount = 0;
        // Iterate over the cart items and accumulate the total amount
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalAmount += product.getPrice() * quantity;
        }
        return totalAmount;
    }

    // Method to clear all items from the cart
    public void clear() {
        cartItems.clear();
    }

    // Method to check if the cart is empty
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
