package com.example.mtb.mapper;

import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.dto.MovieResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.Movie;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class MovieMapper {

    public MovieResponse movieResponseMapper(Movie movie, double avgRatings) {
        if (movie == null)
            return null;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedRatings = df.format(avgRatings);

        return new MovieResponse(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDescription(),
                formattedRatings,
                movie.getRuntime(),
                movie.getCertificate(),
                movie.getGenre(),
                movie.getCastList()
        );
    }

}
