package com.evo.springboot.app.Controllers;

import com.evo.springboot.bl.BusinessLogic;
import com.evo.springboot.db.Users.UsersRepository;
import com.evo.springboot.app.DTO.Incoming.UpdateDataDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import com.evo.springboot.app.DTO.Outgoing.NewPasswordDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"settings", ""})
public class SettingsController {

    @Autowired
    BusinessLogic businessLogic;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "updatePasswordForEmp")
    @PostMapping(value = "updatePasswordForEmp")
    public @ResponseBody GenericResponseDTO updatePasswordForEmp(@RequestBody UpdateDataDTO updateDataDTO) {
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(updateDataDTO.getEmployeeID())).findFirst().get().getFullName();
        try {
            logger.info("[SettingsController][api/updatePasswordForEmp] received new request to update password for employee");
            businessLogic.updateDataForEmp(BusinessLogic.staticCompName, updateDataDTO.getEmployeeID(), "PASSWORD", updateDataDTO.getNewData());
            logger.info("[SettingsController][api/updatePasswordForEmp] update password for employee");
            return new GenericResponseDTO(
                    String.format("update password for employee '%s' completed successfully.", empName),
                    true);

        } catch (Exception err) {
            logger.error("[SettingsController][api/updatePasswordForEmp] update password for employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("update password for employee  '%s' failed", empName),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "updateEmailForEmp")
    @PostMapping(value = "updateEmailForEmp")
    public @ResponseBody GenericResponseDTO updateEmailForEmp(@RequestBody UpdateDataDTO updateDataDTO) {
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(updateDataDTO.getEmployeeID())).findFirst().get().getFullName();
        try {
            logger.info("[SettingsController][api/updateEmailForEmp] received new request to update email for employee");
            businessLogic.updateDataForEmp(BusinessLogic.staticCompName, updateDataDTO.getEmployeeID(), "EMAIL", updateDataDTO.getNewData());
            logger.info("[SettingsController][api/updateEmailForEmp] update email for employee");
            return new GenericResponseDTO(
                    String.format("update email for employee '%s' completed successfully.", empName),
                    true);

        } catch (Exception err) {
            logger.error("[SettingsController][api/updateEmailForEmp] update email for employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("update email for employee  '%s' failed", empName),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "updatePhoneForEmp")
    @PostMapping(value = "updatePhoneForEmp")
    public @ResponseBody GenericResponseDTO updatePhoneForEmp(@RequestBody UpdateDataDTO updateDataDTO) {
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(updateDataDTO.getEmployeeID())).findFirst().get().getFullName();
        try {
            logger.info("[SettingsController][api/updatePhoneForEmp] received new request to update phone for employee");
            businessLogic.updateDataForEmp(BusinessLogic.staticCompName, updateDataDTO.getEmployeeID(), "PHONE", updateDataDTO.getNewData());
            logger.info("[SettingsController][api/updatePhoneForEmp] update phone for employee");
            return new GenericResponseDTO(
                    String.format("update phone for employee '%s' completed successfully.", empName),
                    true);

        } catch (Exception err) {
            logger.error("[SettingsController][api/updatePhoneForEmp] update phone for employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("update phone for employee  '%s' failed", empName),
                    err
            );
        }
    }


    @ApiOperation(value = "", nickname = "updateNameForEmp")
    @PostMapping(value = "updateNameForEmp")
    public @ResponseBody GenericResponseDTO updateNameForEmp(@RequestBody UpdateDataDTO updateDataDTO) {
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(updateDataDTO.getEmployeeID())).findFirst().get().getFullName();
        try {
            logger.info("[SettingsController][api/updateNameForEmp] received new request to update name for employee");
            businessLogic.updateDataForEmp(BusinessLogic.staticCompName, updateDataDTO.getEmployeeID(), "FULL_NAME", updateDataDTO.getNewData());
            logger.info("[SettingsController][api/updateNameForEmp] update name for employee");
            return new GenericResponseDTO(
                    String.format("update name for employee '%s' completed successfully.", empName),
                    true);

        } catch (Exception err) {
            logger.error("[SettingsController][api/updateNameForEmp] update phone for employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("update name for employee  '%s' failed", empName),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "generatePasswordForEmp")
    @PostMapping(value = "generatePasswordForEmp")
    public @ResponseBody NewPasswordDTO generatePasswordForEmp(@RequestParam String employeeID) {
        String empName = businessLogic.getAllEmployees(BusinessLogic.staticCompName).stream()
                .filter(employee -> employee.getID().equals(employeeID)).findFirst().get().getFullName();
        try {
            logger.info("[SettingsController][api/generatePasswordForEmp] received generate password for employee");
            String newPassword = businessLogic.generatePasswordForEmp(BusinessLogic.staticCompName, employeeID);

            UsersRepository usersRepository = UsersRepository.getInstance();
//            usersRepository.changePassword(empName, newPassword);
            businessLogic.changePassword(empName, newPassword);

            logger.info("[SettingsController][api/generatePasswordForEmp] received generate password for employee");
            return new NewPasswordDTO(
                    String.format("received generate password for employee '%s' completed successfully.", empName),
                    true,
                    newPassword);

        } catch (Exception err) {
            logger.error("[SettingsController][api/generatePasswordForEmp] ugenerate password for employee failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("generate password for employee  '%s' failed", empName),
                    err
            );
        }
    }
}
