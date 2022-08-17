package com.evo.springboot.app.Middlewares;

import com.evo.springboot.app.Services.AuthService;
import com.evo.springboot.app.Services.VerifyResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class UserServiceInterceptor implements HandlerInterceptor {

    private static final List<String> openRoutes = new ArrayList<String>() {{
        add("/api/doLogin");
        add("/api/doSignup");
        add("/docs");
        add("/dev/loadCompanyDb"); // for development only
        add("/dev/loadArrangement");  // for development only
    }};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (openRoutes.contains(request.getRequestURI()) || Objects.equals(request.getMethod(), "OPTIONS")) {
                return true;
            }

            VerifyResult verifyResult = AuthService.verifyRequest(request);
            if (!verifyResult.isSuccess()) {
                throw new RuntimeException("invalid request. please log in.");
            }

            return true;
        } catch (Exception exception){
            response.setStatus(401); // 401- Unauthorized
            return false;
        }
    }
}
