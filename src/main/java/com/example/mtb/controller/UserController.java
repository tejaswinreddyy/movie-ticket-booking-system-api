package com.example.mtb.controller;

import com.example.mtb.entity.UserDetails;
import com.example.mtb.service.UserService;
import com.example.mtb.util.ResponseStructure;
import com.example.mtb.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserDetails>> addUser(@RequestBody UserDetails user){
        UserDetails userDetails = userService.addUser(user);
        return responseBuilder.sucess(HttpStatus.OK,"New User Details Has been added", userDetails);
    }


}
