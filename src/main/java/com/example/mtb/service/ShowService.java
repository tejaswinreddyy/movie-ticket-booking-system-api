package com.example.mtb.service;


import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.dto.TheaterShowProjection;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public interface ShowService {


    ShowResponse addShow(String theaterId, String screenId, String movieId, @NotNull Long startTime, String zoneId);

    Page<TheaterShowProjection> fetchShows(String movieId, MovieShowsRequest showsRequest, String city);

}
