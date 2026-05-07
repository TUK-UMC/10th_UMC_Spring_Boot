package com.example.umc10th_proj.domain.review.repository;

import com.example.umc10th_proj.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}