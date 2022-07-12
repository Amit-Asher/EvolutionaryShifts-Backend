package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import Model.Employee.Employee;
import com.evo.springboot.app.Converters.EmployeeConverterDTO;
import com.evo.springboot.app.DTO.EmployeeDTO;
import com.evo.springboot.app.DTO.GenericResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "api/addEmployee")
    public @ResponseBody GenericResponseDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            logger.info("[EmployeeController][api/addEmployee] received new request to add new employee");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            Employee newEmployee = EmployeeConverterDTO.convert(employeeDTO);
            businessLogic.addEmployee(BusinessLogic.staticCompName, newEmployee);

            logger.info("[EmployeeController][api/addEmployee] add new employee completed successfully");
            return new GenericResponseDTO(
                    String.format("add new employee '%s' completed successfully", employeeDTO.name),
                    true
            );

        } catch (Exception err) {
            logger.error("[EmployeeController][api/addEmployee] add new employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add new employee '%s' failed", employeeDTO.name),
                    err
            );
        }
    }

    @DeleteMapping(value = "api/removeEmployee")
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

    @GetMapping(value = "api/getAllEmployees")
    public @ResponseBody GenericResponseDTO getAllEmployees() {
        try {
            logger.info("[EmployeeController][api/getAllEmployees] received new request to get all employees");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.getAllEmployees(BusinessLogic.staticCompName);

            logger.info("[EmployeeController][api/getAllEmployees] get all employees completed successfully");
            return new GenericResponseDTO(
                    String.format("get all employees completed successfully."),
                    true
            );

        } catch (Exception err) {
            logger.error("[EmployeeController][api/getAllEmployees] get all employees failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("get all employees failed"),
                    err
            );
        }
    }
}
