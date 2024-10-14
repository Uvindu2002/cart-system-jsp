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
import java.util.Map;

@WebServlet("/RemoveCartServlet")
public class RemoveCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CartItemDAO cartItemDAO;

    @Override
    public void init() throws ServletException {
        cartItemDAO = new CartItemDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("product");

        // Retrieve the session
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        if (cart != null) {
            // Remove item from cart
            CartItem itemToRemove = cart.get(productName);
            if (itemToRemove != null) {
                // Remove from the database
                try {
                    cartItemDAO.deleteCartItem(sessionId, itemToRemove.getProductId());
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ServletException("Error removing cart item from the database", e);
                }
                // Remove from session
                cart.remove(productName);
            }
            // Save the updated cart back to the session
            session.setAttribute("cart", cart);
        }

        // Redirect back to the cart page
        response.sendRedirect("cart.jsp");
    }
}
