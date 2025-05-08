package com.example.mtb.mapper;

import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.UserDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {


    public UserResponse userDetailsResponseMapper(UserDetails userDetails){
        if(userDetails == null)
            return null;

        return UserResponse.builder()
                .userId(userDetails.getUserId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .phoneNumber(userDetails.getPhoneNumber())
                .userRole(userDetails.getUserRole())
                .build();
    }

}
