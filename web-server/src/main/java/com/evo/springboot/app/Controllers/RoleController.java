package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import Model.Role;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import com.evo.springboot.app.DTO.Incoming.RoleDTO;
import com.evo.springboot.app.DTO.Outgoing.RolesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"role", ""})
public class RoleController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "addNewRole")
    @PostMapping(value = "addNewRole")
    public @ResponseBody GenericResponseDTO addNewRole(@RequestBody RoleDTO roleDTO)
    {
        try {
            logger.info("[RoleController][api/addNewRole] received new request to add new role");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.addNewRole(BusinessLogic.staticCompName, new Role(roleDTO.getRole()));

            logger.info("[RoleController][api/addNewRole] add new role completed successfully");
            return new GenericResponseDTO(
                    String.format("add new role '%s' completed successfully", roleDTO.getRole()),
                    true
            );

        } catch (Exception err) {
            logger.error("[RoleController][api/addNewRole] add new role failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add new role '%s' failed", roleDTO.getRole()),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "removeRole")
    @DeleteMapping(value = "removeRole")
    public @ResponseBody GenericResponseDTO removeRole(@RequestParam String roleName) {
        // TODO: REPLACE THE PARAMETER FROM STRING TO GUID
        try {
            logger.info("[RoleController][api/removeRole] received new request to remove role");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.removeRole(BusinessLogic.staticCompName, new Role(roleName));

            logger.info("[MainController][api/removeRole] remove role completed successfully");
            return new GenericResponseDTO(
                    String.format("remove role '%s' completed successfully.", roleName),
                    true
            );

        } catch (Exception err) {
            logger.error("[MainController][api/removeRole] remove role failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("remove role '%s' failed", roleName),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "getAllRoles")
    @GetMapping(value = "getAllRoles")
    public @ResponseBody RolesDTO getAllRoles(HttpServletRequest request) {
        try {
            logger.info("[RoleController][api/getAllRoles] received new request to get all roles");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            Set<Role> roles = businessLogic.getAllRoles(BusinessLogic.staticCompName);

            logger.info("[RoleController][api/getAllRoles] get all roles completed successfully");
            return new RolesDTO(roles.stream().map(role -> role.name).collect(Collectors.toList()));

        } catch (Exception err) {
            logger.error("[RoleController][api/getAllRoles] get all roles failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "get all roles failed",
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "removeRoleFromEmp")
    @DeleteMapping(value = "removeRoleFromEmp")
    public @ResponseBody GenericResponseDTO removeRoleFromEmp(
            @RequestParam String roleName, @RequestParam String employeeID) {
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(employeeID)).findFirst().get().getFullName();

        try {
            logger.info("[RoleController][api/removeRoleFromEmp] received new request to remove role from employee");
            businessLogic.removeRoleFromEmp(BusinessLogic.staticCompName, new Role(roleName), employeeID);
            logger.info("[MainController][api/removeRoleFromEmp] remove role from employee completed successfully");
            return new GenericResponseDTO(
                    String.format("remove role '%s' from employee '%s' completed successfully.", roleName, empName),
                    true
            );

        } catch (Exception err) {
            logger.error("[MainController][api/removeRoleFromEmp] remove role from employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("remove role '%s' from employee '%s' failed", roleName, empName),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "addRoleToEmp")
    @PostMapping(value = "addRoleToEmp")
    public @ResponseBody GenericResponseDTO addRoleToEmp(
            @RequestParam String roleName, @RequestParam String employeeID) {
        BusinessLogic businessLogic = BusinessLogic.getInstance();
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(employeeID)).findFirst().get().getFullName();

        try {
            logger.info("[RoleController][api/addRoleToEmp] received add role to employee");
            businessLogic.addRoleToEmp(BusinessLogic.staticCompName, new Role(roleName), employeeID);
            logger.info("[MainController][api/addRoleToEmp] add role to employee completed successfully");
            return new GenericResponseDTO(
                    String.format("add role '%s' from employee '%s' completed successfully.", roleName, empName),
                    true
            );

        } catch (Exception err) {
            logger.error("[MainController][api/addRoleToEmp] remove role from employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add role '%s' from employee '%s' failed", roleName, empName),
                    err
            );
        }
    }
}
