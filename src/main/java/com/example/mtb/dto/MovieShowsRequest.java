package com.example.mtb.dto;

import com.example.mtb.enums.ScreenType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.ZoneId;

public record MovieShowsRequest(LocalDate date,
                                String zoneId,
                                ScreenType screenType,
                                int size,
                                int page)
{}

