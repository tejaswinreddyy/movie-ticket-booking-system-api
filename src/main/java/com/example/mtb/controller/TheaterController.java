package com.example.mtb.controller;

import com.example.mtb.dto.TheaterRegisterationRequest;
import com.example.mtb.dto.TheaterResponse;
import com.example.mtb.service.TheaterService;
import com.example.mtb.util.ResponseStructure;
import com.example.mtb.util.RestResponseBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/theaters")
    public ResponseEntity<ResponseStructure<TheaterResponse>> addTheater(String email, @Valid @RequestBody  TheaterRegisterationRequest theaterRegisterationRequest){
        TheaterResponse theaterResponse = theaterService.addTheater(email, theaterRegisterationRequest);
        return responseBuilder.sucess(HttpStatus.OK, "Theater has been succesfull created", theaterResponse);
    }

    @GetMapping("theaters/{theaterId}")
    public ResponseEntity<ResponseStructure<TheaterResponse>> findTheater(@PathVariable String theaterId){
        TheaterResponse theaterResponse = theaterService.findTheater(theaterId);
        return responseBuilder.sucess(HttpStatus.OK, "Theater has been sucessfully fetched", theaterResponse);
    }

}
