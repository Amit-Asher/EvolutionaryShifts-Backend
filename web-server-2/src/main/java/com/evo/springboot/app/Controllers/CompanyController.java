package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import com.evo.springboot.app.DTO.Incoming.CompanyDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CompanyController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "addCompany")
    public @ResponseBody GenericResponseDTO addCompany(@RequestBody CompanyDTO companyDTO) {
        try {
            logger.info("[CompanyController][api/addCompany] received new request to add new company");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.addCompany(companyDTO.getName());

            logger.info("[CompanyController][api/addCompany] add new company completed successfully");
            return new GenericResponseDTO(
                    String.format("add new company '%s' completed successfully", companyDTO.getName()),
                    true
            );

        } catch (Exception err) {
            logger.error("[CompanyController][api/addCompany] add new company failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add new company '%s' failed", companyDTO.getName()),
                    err
            );
        }
    }
}
