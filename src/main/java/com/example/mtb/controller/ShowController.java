package com.example.mtb.controller;

import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.enums.ScreenType;
import com.example.mtb.service.ShowService;
import com.example.mtb.util.ResponseStructure;
import com.example.mtb.util.RestResponseBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@AllArgsConstructor
@Validated
public class ShowController {

    private final ShowService showService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("theaters/{theaterId}/screens/{screenId}/shows")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<ResponseStructure<ShowResponse>> addShow( @PathVariable String theaterId, @PathVariable String screenId, String movieId , @NotNull Long startTime ){
        ShowResponse showResponse = showService.addShow(theaterId, screenId, movieId, startTime);
        return responseBuilder.sucess(HttpStatus.OK, "Show sucessfully created", showResponse);
    }

    @GetMapping("movies/{movieId}/shows")
    public ResponseEntity<String> fetchShows(@PathVariable String movieId, @RequestBody MovieShowsRequest showsRequest){
        String s = showService.fetchShows(movieId, showsRequest);
        return ResponseEntity.ok("Shows fetched");
    }



}
