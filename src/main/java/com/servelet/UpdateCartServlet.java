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

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CartItemDAO cartItemDAO;

    @Override
    public void init() throws ServletException {
        cartItemDAO = new CartItemDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("product");
        String action = request.getParameter("action");
        double price = Double.parseDouble(request.getParameter("price"));

        // Retrieve session
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        if (cart != null && cart.containsKey(productName)) {
            CartItem item = cart.get(productName);

            // Update quantity based on action
            if ("increase".equals(action)) {
                item.setQuantity(item.getQuantity() + 1);
            } else if ("decrease".equals(action) && item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            }

            // Update the item in the database
            try {
                cartItemDAO.updateCartItem(sessionId, item);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error updating cart item in the database", e);
            }

            // Update the cart in the session
            session.setAttribute("cart", cart);
        }

        // Redirect back to the cart page
        response.sendRedirect("cart.jsp");
    }
}
