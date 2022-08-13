package com.evo.springboot.app.Middlewares;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceInterceptor implements HandlerInterceptor {

    private static final List<String> openRoutes = new ArrayList<String>() {{
        add("/api/doLogin");
        add("/api/doSignup");
    }};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (openRoutes.contains(request.getRequestURI())) {
                return true;
            }

            // if missing token return 401 (unauthorized)

            // verify token:
            // if token is invalid then return 401 (unauthorized)
            // if token is valid then extract the employeeId + companyId from payload

            return true;
        } catch (Exception exception){
            System.out.println("error in jwt");
            return false;
        }
    }
}
