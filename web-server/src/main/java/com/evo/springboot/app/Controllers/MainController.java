package com.evo.springboot.app.Controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Api(value = "", tags = {"main", ""})
public class MainController implements ErrorController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public ModelAndView getBundle() {
        // send the front bundle
        return new ModelAndView("index.html"); //work for static
    }
}
