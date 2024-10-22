package com.example.demo.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param productRepository Repository for product data access.
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        // Save product
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        // Find product by its id
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        // Get all products
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        // Return products that contain this keyword
        return productRepository.findByNameContaining(keyword);
    }

    @Override
    public void deleteProduct(Long id) {
        // Delete product by id
        productRepository.deleteById(id);
    }
}
