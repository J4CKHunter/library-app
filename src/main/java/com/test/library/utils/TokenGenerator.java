package com.test.library.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.library.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;

    public String generateToken(Authentication authentication){
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        long expiresIn = EXPIRES_ACCESS_TOKEN_MINUTE * 60 *1000;
        Date expiresAt = new Date(System.currentTimeMillis() + (expiresIn));

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiresAt)
                .withIssuer(ISSUER)
//                .withClaim("claimTest", "valueTest")
                .sign(algorithm);

    }

    public DecodedJWT verifyJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
//  bu fonksiyon expiring date'i iki saniye geçeceğe kadar izin veriyor, bunun saniye cinsinden olması gerektiği önerilir
//                .acceptExpiresAt(2)
                .build();

        try {
            return verifier.verify(token);
//            var claim = decodedToken.getClaims().get("key");
        } catch (Exception e){
            throw GenericException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorMessage(e.getMessage())
                    .build();
        }

    }
}
