package com.cts.shoppingmart.services;

import com.cts.shoppingmart.exceptions.ProductException;
import com.cts.shoppingmart.model.Product;

import java.util.List;

//Interface for product-related services

public interface ProductService {
    List<Product> viewProducts(String category) throws ProductException;
    Product getProductById(int productId) throws ProductException;
    void updateProductQuantity(int productId, int quantity) throws ProductException;
}
