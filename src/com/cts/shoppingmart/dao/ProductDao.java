package com.cts.shoppingmart.dao;

import com.cts.shoppingmart.model.Product;
import com.cts.shoppingmart.exceptions.ProductException;
import java.util.List;

// Data Access Object (DAO) interface for handling product-related database operations
public interface ProductDao {

    // Method to retrieve a list of products by a specific category
    List<Product> getProductsByCategory(String category) throws ProductException;

    // Method to retrieve a specific product by its ID
    Product getProductById(int productId) throws ProductException;

    // Method to update the quantity of a specific product
    void updateProductQuantity(int productId, int quantity) throws ProductException;
}
