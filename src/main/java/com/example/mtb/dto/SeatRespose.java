package com.example.mtb.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SeatRespose (
        List<String> seatId,
        List<String> name
)
{}
