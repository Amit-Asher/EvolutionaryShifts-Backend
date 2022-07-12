package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import com.evo.springboot.app.DTO.GenericResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "api/setAsManager")
    public @ResponseBody GenericResponseDTO setAsManager(@RequestParam String employeeId)
    {
        try {
            logger.info("[RoleController][api/setAsManager] received new request to set manager");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.setAsManager(BusinessLogic.staticCompName, employeeId);

            logger.info("[RoleController][api/setAsManager] set manager completed successfully");
            return new GenericResponseDTO(
                    String.format("set manager '%s' completed successfully", employeeId),
                    true
            );
        } catch (Exception err) {
            logger.error("[RoleController][api/setAsManager] set manager failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "set manager failed",
                    err
            );
        }
    }

    @PostMapping(value = "api/removeAsManager")
    public @ResponseBody GenericResponseDTO removeAsManager(@RequestParam String employeeId)
    {
        try {
            logger.info("[RoleController][api/removeAsManager] received new request to set manager");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.setAsManager(BusinessLogic.staticCompName, employeeId);

            logger.info("[RoleController][api/removeAsManager] set manager completed successfully");
            return new GenericResponseDTO(
                    String.format("set manager '%s' completed successfully", employeeId),
                    true
            );
        } catch (Exception err) {
            logger.error("[RoleController][api/removeAsManager] set manager failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "set manager failed",
                    err
            );
        }
    }
}
