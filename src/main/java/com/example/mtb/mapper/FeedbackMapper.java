package com.example.mtb.mapper;

import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.Screen;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {


    public FeedbackResponse feedbackResponseMapper(Feedback feedback) {
        if (feedback == null)
            return null;
        return new FeedbackResponse(
                feedback.getFeedbackId(),
                feedback.getRating(),
                feedback.getReview()
        );
    }



}
