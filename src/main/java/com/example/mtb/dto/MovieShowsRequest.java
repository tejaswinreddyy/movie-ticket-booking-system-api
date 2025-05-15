package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;

import java.time.LocalDate;

public record MovieShowsRequest(LocalDate date,
                                ScreenType screenType,
                                int size,
                                int page) {
}
