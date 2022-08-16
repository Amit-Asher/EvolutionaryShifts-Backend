package com.evo.springboot.app.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class AuthService {

    private static final Algorithm SECRET = Algorithm.HMAC256("secret");
    private static final String COMPANY = "company";
    private static final String EMPLOYEE = "employee";
    private static final int EXPIRATION_TIME_MILLIS = 1000 * 60 * 5; // 5 minutes
    public static final String TOKEN_NAME = "evo-token";

    public static String generateToken(String company, String employee) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(COMPANY, company);
        payload.put(EMPLOYEE, employee);
        return JWT.create()
                .withPayload(payload)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
                .sign(SECRET);
    }

    public static VerifyResult isTokenVerified(String token) {
        try {
            JWTVerifier verifier = JWT.require(SECRET).build();
            DecodedJWT jwt = verifier.verify(token); // if invalid then exception raise here
            return new VerifyResult(true, jwt);
        } catch (Exception err) {
            System.out.println("unauthorized (401)");
            return new VerifyResult(false, null);
        }
    }

    public static VerifyResult verifyRequest(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                // auth route with no cookie (ui redirects to login)
                throw new RuntimeException("unauthorized request with no cookie");
            }

            Cookie cookie = Arrays.stream(request.getCookies())
                    .filter(c -> Objects.equals(c.getName(), AuthService.TOKEN_NAME))
                    .findFirst()
                    .orElse(null);

            if (cookie == null) {
                // auth route with no token (ui redirects to login)
                throw new RuntimeException("unauthorized request with no token");
            }

            String token = cookie.getValue();
            VerifyResult verifyResult = AuthService.isTokenVerified(token);
            if (!verifyResult.isSuccess()) {
                // auth route with no valid token (ui redirects to login)
                throw new RuntimeException("unauthorized request with invalid token");
            }

            return verifyResult;

        } catch (Exception err) {
            return new VerifyResult(false, null);
        }
    }

    public static RequestContext extractRequestContext(HttpServletRequest request) {
        try {
            VerifyResult verifyResult = verifyRequest(request);
            if (!verifyResult.isSuccess()) {
                throw new RuntimeException("invalid request. please log in.");
            }

            Map<String, Claim> claims = verifyResult.getJwt().getClaims();
            String company = claims.get(COMPANY).asString();
            String employee = claims.get(EMPLOYEE).asString();
            return new RequestContext(company, employee);

        } catch (Exception err) {
            throw err;
        }
    }
}
