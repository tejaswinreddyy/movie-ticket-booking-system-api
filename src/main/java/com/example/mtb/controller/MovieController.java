package com.example.mtb.controller;

import com.example.mtb.dto.MovieResponse;
import com.example.mtb.service.MovieService;
import com.example.mtb.util.ResponseStructure;
import com.example.mtb.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final RestResponseBuilder responseBuilder;

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<ResponseStructure<MovieResponse>> getMovie(@PathVariable String movieId){
        MovieResponse movieResponse = movieService.getMovie(movieId);
        return responseBuilder.sucess(HttpStatus.OK, "Movie has been successfully fetched", movieResponse);
    }

}
