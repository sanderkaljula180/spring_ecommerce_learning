package com.example.demo.Service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

// Q: Should I add update also in here, no?

public interface ProductService {

    /**
     * Saves a new product or updates an existing one.
     *
     * @param product The product to save.
     * @return The saved product.
     */
    Product saveProduct(Product product);

    /**
     * Retrieves a product by its ID.
     *
     * @param id The product ID.
     * @return An Optional containing the product if found.
     */
    Optional<Product> getProductById(Long id);

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    List<Product> getAllProducts();

    /**
     * Searches for products containing the keyword in their name.
     *
     * @param keyword The search keyword.
     * @return A list of matching products.
     */
    List<Product> searchProducts(String keyword);

    /**
     * Deletes a product by its ID.
     *
     * @param id The product ID.
     */
    void deleteProduct(Long id);

}
