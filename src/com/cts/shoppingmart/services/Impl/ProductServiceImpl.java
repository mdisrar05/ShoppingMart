package com.cts.shoppingmart.services.Impl;

import com.cts.shoppingmart.dao.ProductDao;
import com.cts.shoppingmart.exceptions.ProductException;
import com.cts.shoppingmart.model.Product;
import com.cts.shoppingmart.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    // DAO object for accessing product data
    private final ProductDao productDao;

    // Constructor to initialize the ProductDao
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    // Method to view products by category
    @Override
    public List<Product> viewProducts(String category) throws ProductException {
        try {
            // Delegate the task of fetching products by category to the DAO
            return productDao.getProductsByCategory(category);
        } catch (Exception e) {
            // Wrap the SQLException into a ProductException and rethrow it
            throw new ProductException("Error fetching products for category: " + category, e);
        }
    }

    // Method to get a product by its ID
    @Override
    public Product getProductById(int productId) throws ProductException {
        try {
            // Delegate the task of fetching a product by ID to the DAO
            return productDao.getProductById(productId);
        } catch (Exception e) {
            // Wrap the SQLException into a ProductException and rethrow it
            throw new ProductException("Error fetching product with ID: " + productId, e);
        }
    }

    // Method to update the quantity of a product
    @Override
    public void updateProductQuantity(int productId, int quantity) throws ProductException {
        try {
            // Delegate the task of updating product quantity to the DAO
            productDao.updateProductQuantity(productId, quantity);
        } catch (Exception e) {
            // Wrap the SQLException into a ProductException and rethrow it
            throw new ProductException("Error updating quantity for product with ID: " + productId, e);
        }
    }
}
