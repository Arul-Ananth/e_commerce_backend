package org.example.service;



import org.example.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewService extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}
