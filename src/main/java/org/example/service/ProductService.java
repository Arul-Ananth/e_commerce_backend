package org.example.service;

import org.example.model.Product;
import org.example.model.Review;
import org.example.repository.ProductRepository;
import org.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;

    public ProductService(ProductRepository productRepo, ReviewRepository reviewRepo) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepo.findByProductId(productId);
    }

    public List<String> getAllCategories() {
        return productRepo.findAll()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }
}





