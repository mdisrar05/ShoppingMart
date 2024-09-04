package com.cts.shoppingmartjdbc;

import com.cts.shoppingmart.model.Product;
import com.cts.shoppingmart.model.User;
import com.cts.shoppingmart.model.Order; 
import com.cts.shoppingmart.services.ProductService;
import com.cts.shoppingmart.services.UserService;
import com.cts.shoppingmart.services.OrderService;
import com.cts.shoppingmart.services.Impl.ProductServiceImpl;
import com.cts.shoppingmart.services.Impl.UserServiceImpl;
import com.cts.shoppingmart.services.Impl.OrderServiceImpl;
import com.cts.shoppingmart.dao.ProductDao;
import com.cts.shoppingmart.dao.UserDao;
import com.cts.shoppingmart.dao.OrderDao;
import com.cts.shoppingmart.impl.ProductDaoImpl;
import com.cts.shoppingmart.impl.UserDaoImpl;
import com.cts.shoppingmart.impl.OrderDaoImpl;
import com.cts.shoppingmart.exceptions.UserException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Shoppingmartcogni {
    // Database connection instance
    private static Connection connection;
    // Service instances for product, order, and user operations
    private static ProductService productService;
    private static OrderService orderService;
    private static Cart cart;

    public static void main(String[] args) {
        try {
            // Establish database connection
            connection = JdbcConnection.getConnection();
            // Initialize DAO implementations
            ProductDao productDao = new ProductDaoImpl(connection);
            UserDao userDao = new UserDaoImpl(connection);
            OrderDao orderDao = new OrderDaoImpl(connection);
            
            // Initialize service implementations
            productService = new ProductServiceImpl(productDao);
            orderService = new OrderServiceImpl(orderDao);
            UserService userService = new UserServiceImpl(userDao);
            
            // Initialize the cart
            cart = new Cart();

            // Display welcome message and show login menu
            System.out.println("Welcome to ShoppingMartCogni!");
            showLoginMenu(userService);
        } catch (SQLException e) {
            // Handle SQL exceptions (e.g., connection issues)
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    // Method to handle user login and navigate to the main menu
    private static void showLoginMenu(UserService userService) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Authenticate user
            User user = userService.login(username, password);

            // Navigate to the main menu for the user
            if (user != null) {
                showMainMenu();
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (UserException e) {
            // Handle user authentication exceptions
            System.out.println("An error occurred during login: " + e.getMessage());
        }
        
    }

    // Method to display the main menu for regular users
    private static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Delete Cart");
            System.out.println("5. Proceed to Checkout");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    deleteCart();
                    break;
                case 5:
                    proceedToCheckout();
                    break;
                case 6:
                    running = false;
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
    }

    // Method to display products based on the selected category
    private static void viewProducts() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product category (Laptops, Smartphones, Headphones): ");
        String category = scanner.nextLine();
        
        try {
            // Retrieve products by category
            List<Product> products = productService.viewProducts(category);
            System.out.println("\nProducts in " + category + ":");
            for (Product product : products) {
                System.out.println(product.getProductId() + ". " + product.getName() + " - ₹" + product.getPrice() + " (" + product.getQuantity() + " available)");
            }
            selectProduct(products); // Allow user to select a product
        } catch (Exception e) {
            // Handle exceptions while fetching products
            System.out.println("An error occurred while fetching products: " + e.getMessage());
        }
        
    }

    // Method to handle product selection and add to cart
    private static void selectProduct(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to select: ");
        int productId = scanner.nextInt();

        Product selectedProduct = products.stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst()
                .orElse(null);

        if (selectedProduct != null) {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            addProductToCart(selectedProduct, quantity); // Add selected product to cart
        } else {
            System.out.println("Invalid Product ID.");
        }
        
    }

    // Method to add a product to the cart with specified quantity
    private static void addProductToCart(Product product, int quantity) {
        if (quantity <= product.getQuantity()) {
            cart.addProduct(product, quantity);
            System.out.println("Product added to cart!");
        } else {
            System.out.println("Insufficient quantity available.");
        }
    }

    // Overloaded method to add a product to the cart based on Product ID
    private static void addProductToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        try {
            Product product = productService.getProductById(productId); // Retrieve product by ID
            if (product != null && quantity <= product.getQuantity()) {
                cart.addProduct(product, quantity);
                System.out.println("Product added to cart!");
            } else {
                System.out.println("Invalid product ID or insufficient quantity available.");
            }
        } catch (Exception e) {
            // Handle exceptions while adding product to cart
            System.out.println("An error occurred while adding product to cart: " + e.getMessage());
        }
   
    }

    // Method to display the current state of the cart
    private static void viewCart() {
        cart.displayCart(); // Show all items in the cart
    }

    // Method to remove all items from the cart
    private static void deleteCart() {
        cart.clear(); // Clear the cart
        System.out.println("All items removed from the cart!");
    }

    // Method to handle the checkout process
    private static void proceedToCheckout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add products before proceeding to checkout.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your shipping address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter your name: "); // Prompt for customer name
        String customerName = scanner.nextLine(); // Read customer name

        try {
            Order order = cart.generateOrder(address); // Generate order from cart
            order.setOrderDate(new java.util.Date()); // Set current date as order date
            order.setCustomerName(customerName); // Set customer name in order

            orderService.placeOrder(order); // Place the order
            System.out.println("Order placed successfully!");
            cart.clear(); // Clear cart after successful order
        } catch (Exception e) {
            // Handle exceptions while placing order
            System.out.println("An error occurred while placing your order: " + e.getMessage());
        }
        
    }
}
