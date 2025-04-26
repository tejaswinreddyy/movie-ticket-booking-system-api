package com.example.mtb.dto;

import com.example.mtb.entity.Seat;
import lombok.Builder;

import java.util.List;

@Builder
public record TheaterResponse(
        String theaterId,
        String name,
        String address,
        String city,
        String landmark

)
{}
