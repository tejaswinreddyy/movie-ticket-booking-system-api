package com.example.mtb.service;

import com.example.mtb.dto.TheaterRegisterationRequest;
import com.example.mtb.dto.TheaterResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface TheaterService {

    TheaterResponse addTheater(String email, TheaterRegisterationRequest theaterRegisterationRequest);

    TheaterResponse findTheater(String theaterId);
}
