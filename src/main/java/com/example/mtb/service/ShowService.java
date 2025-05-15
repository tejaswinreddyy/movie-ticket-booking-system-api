package com.example.mtb.service;


import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.enums.ScreenType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public interface ShowService {


    ShowResponse addShow(String theaterId, String screenId, String movieId, @NotNull Long startTime);

    String fetchShows(String movieId, MovieShowsRequest showsRequest);
}
