package com.example.gearapp.model;

import com.example.gearapp.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManager {
    private static CartManager instance;
    private List<Product> products;
    private Map<Product, Integer> productQuantities; // Map to track quantities

    private CartManager() {
        products = new ArrayList<>();
        productQuantities = new HashMap<>(); // Initialize the productQuantities map
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product, int quantity) {
        if (productQuantities.containsKey(product)) {
            // If product already exists, increase the quantity
            int existingQuantity = productQuantities.get(product);
            productQuantities.put(product, existingQuantity + quantity);
        } else {
            // Add new product with its quantity
            products.add(product);
            productQuantities.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
        productQuantities.remove(product); // Remove from quantity map as well
    }

    public List<Product> getProducts() {
        return products;
    }

    public void clearCart() {
        products.clear();
        productQuantities.clear(); // Clear quantities
    }

    public int getProductQuantity(Product product) {
        // Return the quantity of the product, or 0 if it’s not in the cart
        return productQuantities.getOrDefault(product, 0);
    }
}
