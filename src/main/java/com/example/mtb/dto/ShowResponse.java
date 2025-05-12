package com.example.mtb.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ShowResponse(

        String showId,
        Instant startsAt,
        Instant endsAt

) {
}
