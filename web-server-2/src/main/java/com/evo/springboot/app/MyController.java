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

    //http://localhost:8080/api/setAsManager/?id=345
    @RequestMapping(value = "api/setAsManager", params = "id",
            method = GET)
    public @ResponseBody String setAsManager(String id)
    {
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.setAsManager(id, BusinessLogic.company);

        return "setAsManager: " + id;
    }

    //http://localhost:8080/api/removeManager/?id=345
    @RequestMapping(value = "api/removeManager", params = "id",
            method = GET)
    public @ResponseBody String removeManager(String id) {
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.setAsManager(id, BusinessLogic.company);

        return "removeManager: " + id;
    }

    //http://localhost:8080/api/addNewRole/?name=manager
    @RequestMapping(value = "api/addNewRole", params = "name",
            method = GET)
    public @ResponseBody String addNewRole(RoleDTO roleDTO)
    {
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.addNewRole(roleDTO.getName(), BusinessLogic.company);

        return "Success addNewRole: " + roleDTO.getName();
    }

    //http://localhost:8080/api/removeRole/?name=manager
    @RequestMapping(value = "api/removeRole", params = "name",
            method = GET)
    public @ResponseBody String removeRole(RoleDTO roleDTO) {
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.removeRole(roleDTO.getName(), BusinessLogic.company);

        return "Success removeRole: " + roleDTO.getName();
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
