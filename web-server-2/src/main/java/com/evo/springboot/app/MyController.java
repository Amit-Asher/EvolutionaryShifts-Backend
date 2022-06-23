package com.evo.springboot.app;

import BusinessLogic.BusinessLogic;
import Model.Role;
import com.evo.springboot.app.DTO_Objects.RoleDTO;
import com.evo.springboot.app.DTO_Objects.RolesDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MyController implements ErrorController {

    //not working
    //server.error.path=/error
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
        return new ModelAndView("index.html");//work for static
    }

    @RequestMapping("/api/home")
    public String home() {
        return "Hello world from Spring Boot";//for CheckHTTPResponse
    }

    //http://localhost:8080/api/addEmployee/?name=Andrio&phoneNumber=050&fitRoles=manager,theacer
    @RequestMapping(value = "/api/addEmployee",
            params = {"name", "phoneNumber", "fitRoles"}, method = GET)
    public @ResponseBody String addEmployee(
            @RequestParam String name,
            @RequestParam String phoneNumber,
            RolesDTO fitRoles) {

        Set<Role> roles = new HashSet<>();

        for(int i = 0;i < fitRoles.getFitRoles().length;i++)
            roles.add(new Role(fitRoles.getFitRoles()[i]));

        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.addEmployee(name, BusinessLogic.company,
                phoneNumber, roles);

        return "Success addEmployee: " + name;
    }

    //http://localhost:8080/api/removeEmployee/?id=232
    @RequestMapping(value = "/api/removeEmployee",
            params = "id", method = GET)
    public @ResponseBody String removeEmployee(@RequestParam String id) {

        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.removeEmployee(id, BusinessLogic.company);

        return "removeEmployee: " + id;
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
    @RequestMapping(value = "api/addNewRole", params = "name",
            method = GET)
    public @ResponseBody String addNewRole(RoleDTO roleDTO){

//        JsonObject json = new JsonObject();
//        ObjectMapper mapper = new ObjectMapper();
//        RoleDTO roleDTO;
//
//        json.addProperty("name", name);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        try {
//            roleDTO = mapper.readValue(json.toString(), RoleDTO.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.addNewRole(roleDTO.getName(), BusinessLogic.company);

        return "Success addNewRole: " + roleDTO.getName();
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
