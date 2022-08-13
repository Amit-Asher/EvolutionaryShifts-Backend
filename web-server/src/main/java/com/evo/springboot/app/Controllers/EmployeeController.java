package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import Model.Employee.Employee;
import com.evo.springboot.app.Converters.EmployeeConverter;
import com.evo.springboot.app.DTO.Incoming.NewEmployeeDTO;
import com.evo.springboot.app.DTO.Outgoing.EmployeeDTO;
import com.evo.springboot.app.DTO.Outgoing.EmployeesDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"employee", ""})
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "addEmployee")
    @PostMapping(value = "addEmployee")
    public @ResponseBody GenericResponseDTO addEmployee(@RequestBody NewEmployeeDTO employeeDTO) {
        try {
            logger.info("[EmployeeController][api/addEmployee] received new request to add new employee");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            Employee newEmployee = EmployeeConverter.convert(employeeDTO);
            businessLogic.addEmployee(BusinessLogic.staticCompName, newEmployee);

            logger.info("[EmployeeController][api/addEmployee] add new employee completed successfully");
            return new GenericResponseDTO(
                    String.format("add new employee '%s' completed successfully", employeeDTO.getFullName()),
                    true
            );

        } catch (Exception err) {
            logger.error("[EmployeeController][api/addEmployee] add new employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add new employee '%s' failed", employeeDTO.getFullName()),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "removeEmployee")
    @DeleteMapping(value = "removeEmployee")
    public @ResponseBody GenericResponseDTO removeEmployee(@RequestParam String employeeId) {
        try {
            logger.info("[EmployeeController][api/removeEmployee] received new request to remove employee");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.removeEmployee(BusinessLogic.staticCompName, employeeId);

            logger.info("[EmployeeController][api/removeEmployee] remove employee completed successfully");
            return new GenericResponseDTO(
                    String.format("remove employee '%s' completed successfully.", employeeId),
                    true
            );

        } catch (Exception err) {
            logger.error("[EmployeeController][api/removeEmployee] remove employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("remove employee '%s' failed", employeeId),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "getAllEmployees")
    @GetMapping(value = "getAllEmployees")
    public @ResponseBody EmployeesDTO getAllEmployees() {
        try {
            logger.info("[EmployeeController][api/getAllEmployees] received new request to get all employees");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            List<Employee> employees = businessLogic.getAllEmployees(BusinessLogic.staticCompName);

            logger.info("[EmployeeController][api/getAllEmployees] get all employees completed successfully");
            return new EmployeesDTO(employees.stream().map(employee -> {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(employee.getID());
                employeeDTO.setFirstName(employee.getFirstName());//todo
                employeeDTO.setLastName(employee.getLastName());
                employeeDTO.setEmail("email");
                employeeDTO.setPassword("password");
                employeeDTO.setPhoneNumber(employee.getPhoneNumber());
                List<String> roles = new ArrayList<>();
                employee.getFitRoles().forEach(role -> {
                    roles.add(role.name);
                });
                employeeDTO.setRoles(roles);
                return employeeDTO;
            }).collect(Collectors.toList()));

        } catch (Exception err) {
            logger.error("[EmployeeController][api/getAllEmployees] get all employees failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("get all employees failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "setAsManager")
    @PostMapping(value = "setAsManager")
    public @ResponseBody GenericResponseDTO setAsManager(@RequestParam String employeeId)
    {
        try {
            logger.info("[EmployeeController][api/setAsManager] received new request to set manager");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.setAsManager(BusinessLogic.staticCompName, employeeId);

            logger.info("[EmployeeController][api/setAsManager] set manager completed successfully");
            return new GenericResponseDTO(
                    String.format("set manager '%s' completed successfully", employeeId),
                    true
            );
        } catch (Exception err) {
            logger.error("[EmployeeController][api/setAsManager] set manager failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "set manager failed",
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "removeAsManager")
    @PostMapping(value = "removeAsManager")
    public @ResponseBody GenericResponseDTO removeAsManager(@RequestParam String employeeId)
    {
        try {
            logger.info("[EmployeeController][api/removeAsManager] received new request to set manager");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.setAsManager(BusinessLogic.staticCompName, employeeId);

            logger.info("[EmployeeController][api/removeAsManager] set manager completed successfully");
            return new GenericResponseDTO(
                    String.format("set manager '%s' completed successfully", employeeId),
                    true
            );
        } catch (Exception err) {
            logger.error("[EmployeeController][api/removeAsManager] set manager failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "set manager failed",
                    err
            );
        }
    }
}
