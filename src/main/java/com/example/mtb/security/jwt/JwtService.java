package com.example.mtb.security.jwt;

import com.example.mtb.config.AppEnv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

//    private String secret = "USgd9/hnmR0Ko3MPE0HplPywZc+PuyP8szM4NFCaek0=";

    private final AppEnv env;

    public String createJwtToken(TokenPayLoad tokenPayload) {
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setSubject(tokenPayload.subject())
                .setIssuedAt(new Date(tokenPayload.issuedAt().toEpochMilli()))
                .setExpiration(new Date(tokenPayload.expiration().toEpochMilli()))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getToken().getSecret()));
    }

}
