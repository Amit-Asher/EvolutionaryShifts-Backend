package com.evo.springboot.app;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerAdvisor//not using
{

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("error/404");

        return mv;
    }
}