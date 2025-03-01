package com.example.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepo.deleteById(id);
    }
}
