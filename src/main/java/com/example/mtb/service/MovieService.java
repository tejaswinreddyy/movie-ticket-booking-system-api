package com.example.mtb.service;

import com.example.mtb.dto.MovieResponse;

import java.util.List;
import java.util.Set;

public interface MovieService {
    MovieResponse getMovie(String movieId);

    Set<MovieResponse> searchMovies(String search);
}
