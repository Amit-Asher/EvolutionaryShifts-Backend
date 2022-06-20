package com.evo.springboot.app;

import BusinessLogic.BusinessLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MyController implements ErrorController {

    //install spring cli from here: https://docs.spring.io/spring-boot/docs/current/reference/html/cli.html
    //run the script: spring run web-server/src/main/java/com/evolution/springboot/app/MainResolver.java

    //not working
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
        }

        return "error";
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @RequestMapping("/api/home")
    public String home() {
        return "Hello world from Spring Boot";
    }

    //http:localhost:8080/api/v1/addEmployee/?name=Andrio2
    @RequestMapping(value = "/api/addEmployee", params = "name", method = GET)
    public @ResponseBody String addEmployee(
            @RequestParam String name) {


        return "Success addEmployee";
    }


    @GetMapping("/api/removeEmployee")
    String removeEmployee() {
        return "removeEmployee";
    }

    @GetMapping("/api/setAsManager")
    String setAsManager() {
        return "setAsManager";
    }

    @GetMapping("/api/removeManager")
    String removeManager() {
        return "removeManager";
    }

    //http://localhost:8080/api/addNewRole/?name=manager
    @RequestMapping(value = "api/addNewRole", params = "name", method = GET)
    public @ResponseBody String addNewRole(@RequestParam String name){

        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        RoleDTO roleDTO;
        try {
            roleDTO = mapper.readValue(json.toString(), RoleDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.addNewRole(roleDTO.name, BusinessLogic.company);
          return "Success addNewRole: " + name;
    }

    @GetMapping("/api/removeRole")
    String removeRole() {
        return "removeRole";
    }

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

    @GetMapping("/api/getSolution")
    String getSolution() {
        return "getSolution";
    }

    @GetMapping("/api/publishArrangement")
    String publishArrangement() {
        return "publishArrangement";
    }

    @GetMapping("/api/approveArrangement")
    String approveArrangement() {
        return "approveArrangement";
    }

    @GetMapping("/api/declineArrangement")
    String declineArrangement() {
        return "declineArrangement";
    }

    @GetMapping("/api/setArrangement")
    String setArrangement() {
        return "setArrangement";
    }

    @GetMapping("/api/finishArrangement")
    String finishArrangement() {
        return "finishArrangement";
    }

    @GetMapping("/api/reopenArrangement")
    String reopenArrangement() {
        return "reopenArrangement";
    }
}
