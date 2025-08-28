package org.example.controller;

import org.example.model.Product;
import org.example.model.Review;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.1.4:5173"})
// frontend
public class ProductController {
    @Autowired
    private final ProductService service;

   
    @Autowired
    private ProductRepository productRepo;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProductById(id);
    }

    //CHECK THE FORMAT OF THE OUTPUT
    @GetMapping("/reviews/{id}")
    public List<Review> getReviews(@PathVariable Long id) {
        return service.getReviewsByProductId(id);
    }
    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        return productRepo.findByCategory(categoryName);
    }



}
