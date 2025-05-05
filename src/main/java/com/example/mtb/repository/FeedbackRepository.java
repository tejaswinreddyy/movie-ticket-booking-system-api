package com.example.mtb.repository;

import com.example.mtb.entity.Feedback;
import com.example.mtb.service.FeedbackService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
