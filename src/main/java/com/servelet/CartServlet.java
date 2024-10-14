package com.servelet;

import com.DAO.CartItemDAO;
import com.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DAO instance
    private CartItemDAO cartItemDAO;

    @Override
    public void init() throws ServletException {
        // Initialize DAO
        cartItemDAO = new CartItemDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product name and quantity from the request
        String productName = request.getParameter("product");
        int quantity = Integer.parseInt(request.getParameter("quantity")); // Get quantity from the input field

        // Determine the price based on the product name
        double price = 0.0;
        switch (productName) {
            case "Product 1":
                price = 10.00;
                break;
            case "Product 2":
                price = 20.00;
                break;
            case "Product 3":
                price = 30.00;
                break;
            default:
                throw new IllegalArgumentException("Invalid product name: " + productName);
        }

        // Create CartItem
        CartItem item = new CartItem(1, productName, price, quantity); // Use a static productId for simplicity

        // Retrieve session ID
        HttpSession session = request.getSession();
        String sessionId = session.getId();

        // Retrieve cart from session
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        // Check if the item is already in the cart
        if (cart.containsKey(productName)) {
            // If it exists, update the quantity
            CartItem existingItem = cart.get(productName);
            existingItem.setQuantity(existingItem.getQuantity() + quantity); // Increment quantity
            // Update in database
            try {
                cartItemDAO.updateCartItem(sessionId, existingItem);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error updating cart item in the database", e);
            }
        } else {
            // If it doesn't exist, add the new item
            cart.put(productName, item);
            // Add to database
            try {
                cartItemDAO.addCartItem(sessionId, item);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error adding cart item to the database", e);
            }
        }

        // Save the updated cart back to the session
        session.setAttribute("cart", cart);

        // Redirect to cart page
        response.sendRedirect("cart.jsp");
    }
}
