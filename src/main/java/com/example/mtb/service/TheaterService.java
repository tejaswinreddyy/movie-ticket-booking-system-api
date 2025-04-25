package com.example.mtb.service;

import com.example.mtb.dto.TheaterRequest;
import com.example.mtb.dto.TheaterResponse;

public interface TheaterService {

    TheaterResponse addTheater(String email, TheaterRequest theaterRequest);

    TheaterResponse findTheater(String theaterId);

    TheaterResponse updateTheater(String theaterId, TheaterRequest registerationRequest);
}
