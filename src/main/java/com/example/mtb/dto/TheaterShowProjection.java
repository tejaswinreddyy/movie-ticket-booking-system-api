package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;

import java.time.Instant;

public interface TheaterShowProjection {
    Long getTheaterId();
    String getTheaterName();
    String getLandmark();
    String getCity();
    Long getScreenId();
    ScreenType getScreenType();
    Long getShowId();
    Instant getShowStartsAt();
    Instant getShowEndsAt();
}
