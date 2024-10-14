package com.DAO;

import com.model.CartItem;
import com.connection.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {

    // SQL Queries
    private static final String INSERT_CART_ITEM_SQL = "INSERT INTO cart (session_id, product_id, quantity) VALUES (?, ?, ?)";
    private static final String SELECT_CART_ITEMS_BY_SESSION = "SELECT c.product_id, p.product_name, p.price, c.quantity FROM cart c JOIN products p ON c.product_id = p.product_id WHERE c.session_id = ?";
    private static final String DELETE_CART_ITEM_SQL = "DELETE FROM cart WHERE session_id = ? AND product_id = ?";
    private static final String UPDATE_CART_ITEM_SQL = "UPDATE cart SET quantity = ? WHERE session_id = ? AND product_id = ?";
    private static final String CLEAR_CART_SQL = "DELETE FROM cart WHERE session_id = ?";

    // Add a cart item to the database
    public void addCartItem(String sessionId, CartItem cartItem) throws SQLException {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_ITEM_SQL)) {
            preparedStatement.setString(1, sessionId);
            preparedStatement.setInt(2, cartItem.getProductId());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error adding cart item to the database");
        }
    }

    // Get all cart items for a specific session
    public List<CartItem> getCartItemsBySession(String sessionId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_ITEMS_BY_SESSION)) {
            preparedStatement.setString(1, sessionId);
            ResultSet rs = preparedStatement.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                cartItems.add(new CartItem(productId, productName, price, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching cart items from the database");
        }

        return cartItems;
    }

    // Update the quantity of a cart item
    public void updateCartItem(String sessionId, CartItem cartItem) throws SQLException {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_ITEM_SQL)) {
            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setString(2, sessionId);
            preparedStatement.setInt(3, cartItem.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating cart item in the database");
        }
    }

    // Remove an item from the cart
    public void deleteCartItem(String sessionId, int productId) throws SQLException {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_ITEM_SQL)) {
            preparedStatement.setString(1, sessionId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error removing cart item from the database");
        }
    }

    // Clear the cart for a specific session
    public void clearCart(String sessionId) throws SQLException {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_CART_SQL)) {
            preparedStatement.setString(1, sessionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error clearing cart in the database");
        }
    }
}
