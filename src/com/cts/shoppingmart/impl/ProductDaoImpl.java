package com.cts.shoppingmart.impl;

import com.cts.shoppingmart.model.Product;
import com.cts.shoppingmart.dao.ProductDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    // Database connection instance
    private Connection connection;

    // Constructor to initialize the connection
    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override // Retrieves product details by product ID
    public Product getProductById(int productId) {
        // Variable to hold the product object
        Product product = null;
        // SQL query to fetch product details based on product ID
        String query = "SELECT * FROM product WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Sets the product ID parameter
            preparedStatement.setInt(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    product = new Product(); // Creates a new Product object
                    // Sets product attributes from the result set
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setQuantity(resultSet.getInt("stock_quantity"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Prints exception message for any SQL or connection errors
        }
        return product; // Returns the product object (or null if no product found)
    }

    @Override // Updates the quantity of a product based on product ID
    public void updateProductQuantity(int productId, int quantity) {
        // SQL query to update product quantity
        String query = "UPDATE product SET quantity = ? WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the new quantity and product ID parameters
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productId);
            int rowsAffected = preparedStatement.executeUpdate(); // Execute the update
            // Prints a message based on the update result
            if (rowsAffected > 0) {
                System.out.println("Product quantity updated successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            e.getMessage(); // Prints exception message for any SQL or connection errors
        }
    }

    @Override // Retrieves a list of products based on the category
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>(); // List to hold products
        // SQL query to fetch products based on category
        String query = "SELECT * FROM product WHERE category = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Sets the category parameter
            preparedStatement.setString(1, category);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    Product product = new Product(); // Creates a new Product object
                    // Sets product attributes from the result set
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("name"));
                    product.setCategory(resultSet.getString("category"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setQuantity(resultSet.getInt("stock_quantity"));
                    products.add(product); // Adds the product to the list
                }
            }
        } catch (Exception e) {
            e.getMessage(); // Prints exception message for any SQL or connection errors
        }
        return products; // Returns the list of products
    }
}
