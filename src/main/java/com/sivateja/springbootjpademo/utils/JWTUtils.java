package com.sivateja.springbootjpademo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sivateja.springbootjpademo.config.UserPrincipal;
import com.sivateja.springbootjpademo.dto.LoginResponse;
import com.sivateja.springbootjpademo.entity.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.UUID;

public class JWTUtils {

    public static LoginResponse crateJWTToken(Authentication authentication) {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String accessToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withSubject(user.getUserId().toString())
                .withClaim("roles", "user")
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withSubject(user.getUserId().toString())
                .withClaim("roles", "user")
                .sign(algorithm);
        LoginResponse response = new LoginResponse();
        response.setAccess_token(accessToken);
        response.setRefresh_token(refreshToken);
        return response;
    }

    public static DecodedJWT decodeJWTToken(String bearerToken) {
        String accessToken = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(accessToken);
        return decodedJWT;
    }


}
