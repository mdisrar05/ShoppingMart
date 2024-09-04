# ShoppingMartCogni Application

This is a Java-based console application for a shopping cart system, developed using Core Java, MySQL, and JDBC. The application includes user and admin functionalities, allowing users to browse products, add items to their cart, and place orders. Admins can update product quantities and manage inventory.

## Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK)
- MySQL Server
- JDBC Driver for MySQL
- Eclipse IDE (or any Java IDE)

## Setting Up the MySQL Database

1. **Start the MySQL Server:**
   - Ensure your MySQL server is running.

2. **Create the Database:**
   - Open your MySQL command line or MySQL Workbench.
   - Run the following command to create the `shoppingmartdb` database:

   CREATE DATABASE shoppingmartdb;

3. **Import the Tables:**

Use the provided shoppingmartdb.sql file to create the necessary tables.
In your MySQL command line or MySQL Workbench, run:

  USE shoppingmartdb;
  SOURCE path/to/shoppingmartdb.sql;


Java Project Structure:

The project is organized into several packages, each serving a specific purpose:

com.cts.shoppingmart.model: Contains the model classes representing the entities in the system, such as Product, Cart, and Order.

com.cts.shoppingmart.dao: Contains DAO (Data Access Object) interfaces for performing database operations.

com.cts.shoppingmart.impl: Contains implementations of the DAO interfaces.

com.cts.shoppingmart.services: Contains service interfaces that define the business logic of the application.

com.cts.shoppingmart.services.impl: Contains implementations of the service interfaces.

com.cts.shoppingmart.exceptions: Contains custom exception classes used throughout the application.

com.cts.shoppingmartjdbc: Contains the main application logic, including user interaction through the console.

RUNNING THE APPLICATION:
1. Open the Project in Eclipse:
      Import the project into your Eclipse IDE.
2. Configure the JDBC Connection:
      Make sure the JDBC connection string in your JdbcConnection.java file is correctly configured to connect to your MySQL database. The connection string should look something like this:
           String url = "jdbc:mysql://localhost:3306/shoppingmartdb";
           String username = "your_mysql_username";
           String password = "your_mysql_password";
3. Run the Application:
      Navigate to Shoppingmartcogni.java in the com.cts.shoppingmartjdbc package.
      Right-click on the file and select Run As > Java Application.
4. Interacting with the Application:
      Follow the on-screen prompts to log in as a user or admin, browse products, manage the cart, and place orders.
   
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Database Structure
The shoppingmartdb.sql file defines the structure of the following tables:

1. Product Table
        product_id (Primary Key): Unique identifier for each product.
        name: Name of the product.
        description: Description of the product.
        price: Price of the product.
        stock_quantity: Quantity of the product available in stock.
2. Cart Table
        cart_id (Primary Key): Unique identifier for each cart.
        product_id (Foreign Key references Product Table): The ID of the product added to the cart.
        quantity: The quantity of the product in the cart.
3. Order Table
        order_id (Primary Key): Unique identifier for each order.
        cart_id (Foreign Key references Cart Table): The ID of the cart associated with the order.
        customer_name: The name of the customer who placed the order.
        shipping_address: The shipping address for the order.
        order_date: The date the order was placed.
        total_price: The total price of the order.

SQL queries for creation is maintained in separate 'shoppingcartdb.sql' file....
