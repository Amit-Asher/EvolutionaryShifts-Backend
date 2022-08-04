package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
