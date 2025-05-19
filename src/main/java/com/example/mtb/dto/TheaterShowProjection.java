package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;

import java.time.Instant;
import java.util.List;

public record TheaterShowProjection (
        String theaterId,
        String theaterName,
        String address,
        List<ShowResponse> shows
) {}
