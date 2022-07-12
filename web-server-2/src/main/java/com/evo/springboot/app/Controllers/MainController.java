package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MainController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html"); //work for static
    }

    @RequestMapping(value = "api/getSolution", method = GET)
    public @ResponseBody String getSolution() {
        return "getSolution";
    }

    @RequestMapping(value = "api/publishArrangement", method = GET)
    public @ResponseBody String publishArrangement() {
        return "publishArrangement";
    }

    @RequestMapping(value = "api/finishArrangement", method = GET)
    public @ResponseBody String finishArrangement() {
        return "finishArrangement";
    }

    ///////////////////////////////////////////

    @GetMapping("/api/setArrangementProperties")
    String setArrangementProperties() {
        return "setArrangementProperties";
    }

    @GetMapping("/api/setEmployeePreference")
    String setEmployeePreference() {
        return "setEmployeePreference";
    }

    @GetMapping("/api/blockEmployeesToSetPref")
    String blockEmployeesToSetPref() {
        return "blockEmployeesToSetPref";
    }

    @GetMapping("/api/startAlgorithm")
    String startAlgorithm() {
        return "startAlgorithm";
    }

    @GetMapping("/api/declineArrangement")
    String declineArrangement() {
        return "declineArrangement";
    }

    @GetMapping("/api/setArrangement")
    String setArrangement() {
        return "setArrangement";
    }
}
