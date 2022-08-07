package com.evo.springboot.app.Jwt;


import com.evo.springboot.app.middleware.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends Interceptor {

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // String userName = request.getParameter("userName");
        //String userPassword = request.getParameter("password");
        // UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userName, userPassword);
        //authenticationManager.authenticate(usernamePasswordAuthenticationToken);


        System.out.println("--jwt--");
        String authorization = request.getHeader("authorization");
        System.out.println("authorization: " + authorization);

        return super.preHandle(request, response, handler);
    }
}
