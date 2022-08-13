package com.evo.springboot.app.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private static final Algorithm SECRET = Algorithm.HMAC256("secret");
    private static final String COMPANY = "company";
    private static final String EMPLOYEE = "employee";
    private static final int EXPIRATION_TIME_MILLIS = 1000 * 60 * 5; // 5 minutes

    public static String generateToken(String company, String employee) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(COMPANY, company);
        payload.put(EMPLOYEE, employee);
        return JWT.create()
                .withPayload(payload)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
                .sign(SECRET);
    }

    public static boolean isTokenVerified(String token) {
        try {
            JWTVerifier verifier = JWT.require(SECRET).build();
            verifier.verify(token); // if invalid then exception raise here
            return true;
        } catch (Exception err) {
            System.out.println("unauthorized (401)");
            return false;
        }
    }

    public static RequestContext getContext(String token) {
        try {
            if (!isTokenVerified(token)) {
                // just in case... never should get here
                throw new RuntimeException("unauthorized (401)");
            }

            JWTVerifier verifier = JWT.require(SECRET).build();
            DecodedJWT jwt = verifier.verify(token); // possible exception
            Map<String, Claim> claims = jwt.getClaims();
            String company = claims.get(COMPANY).asString();
            String employee = claims.get(EMPLOYEE).asString();
            return new RequestContext(company, employee);

        } catch (Exception err) {
            throw err;
        }
    }
}
