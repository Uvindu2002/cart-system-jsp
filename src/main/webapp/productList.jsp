<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">Product List</h2>

    <div class="row justify-content-center">
        <!-- Product 1 -->
        <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
                <!-- Product Image -->
                <img src="https://via.placeholder.com/300" class="card-img-top" alt="Product 1 Image">

                <div class="card-body">
                    <!-- Product Name -->
                    <h5 class="card-title">Product 1</h5>

                    <!-- Product Price -->
                    <p class="card-text">Price: $10.00</p>

                    <!-- Add to Cart Button -->
                    <form action="/cart/CartServlet" method="post">
                        <input type="hidden" name="product" value="Product 1">
                        <input type="hidden" name="price" value="10.00"> <!-- Hidden field for price -->
                        <div class="form-group">
                            <label for="quantity1">Quantity:</label>
                            <input type="number" class="form-control" id="quantity1" name="quantity" min="1" value="1">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Product 2 -->
        <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
                <!-- Product Image -->
                <img src="https://via.placeholder.com/300" class="card-img-top" alt="Product 2 Image">

                <div class="card-body">
                    <!-- Product Name -->
                    <h5 class="card-title">Product 2</h5>

                    <!-- Product Price -->
                    <p class="card-text">Price: $20.00</p>

                    <!-- Add to Cart Button -->
                    <form action="/cart/CartServlet" method="post">
                        <input type="hidden" name="product" value="Product 2">
                        <input type="hidden" name="price" value="20.00"> <!-- Hidden field for price -->
                        <div class="form-group">
                            <label for="quantity2">Quantity:</label>
                            <input type="number" class="form-control" id="quantity2" name="quantity" min="1" value="1">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Product 3 -->
        <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
                <!-- Product Image -->
                <img src="https://via.placeholder.com/300" class="card-img-top" alt="Product 3 Image">

                <div class="card-body">
                    <!-- Product Name -->
                    <h5 class="card-title">Product 3</h5>

                    <!-- Product Price -->
                    <p class="card-text">Price: $30.00</p>

                    <!-- Add to Cart Button -->
                    <form action="/cart/CartServlet" method="post">
                        <input type="hidden" name="product" value="Product 3">
                        <input type="hidden" name="price" value="30.00"> <!-- Hidden field for price -->
                        <div class="form-group">
                            <label for="quantity3">Quantity:</label>
                            <input type="number" class="form-control" id="quantity3" name="quantity" min="1" value="1">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
