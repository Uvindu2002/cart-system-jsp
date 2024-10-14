<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.model.CartItem" %>
<%@ page import="com.DAO.CartItemDAO" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Cart</h2>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
            double grandTotal = 0.0;

            if (cart != null && !cart.isEmpty()) {
                for (CartItem item : cart.values()) {
                    double total = item.getPrice() * item.getQuantity();
                    grandTotal += total;
        %>
        <tr>
            <td><%= item.getProductName() %></td>
            <td>$<%= item.getPrice() %></td>
            <td>
                <form action="/cart/UpdateCartServlet" method="post" style="display: inline;">
                    <input type="hidden" name="product" value="<%= item.getProductName() %>">
                    <input type="hidden" name="price" value="<%= item.getPrice() %>">
                    <button type="submit" name="action" value="decrease" class="btn btn-sm btn-danger">-</button>
                    <span class="mx-2"><%= item.getQuantity() %></span>
                    <button type="submit" name="action" value="increase" class="btn btn-sm btn-success">+</button>
                </form>
            </td>
            <td>$<%= total %></td>
            <td>
                <form action="/cart/RemoveCartServlet" method="post" style="display:inline;">
                    <input type="hidden" name="product" value="<%= item.getProductName() %>">
                    <input type="hidden" name="action" value="remove">
                    <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="text-center">Your cart is empty!</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <h3 class="text-right">Grand Total: $<%= grandTotal %></h3>

    <div class="text-right">
        <a href="checkout.jsp" class="btn btn-success">Checkout</a>
        <a href="productList.jsp" class="btn btn-primary">Continue Shopping</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
