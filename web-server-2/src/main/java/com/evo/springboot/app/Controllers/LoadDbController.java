package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import com.evo.springboot.app.DTO.CompanyDTO;
import com.evo.springboot.app.DTO.DbDTO;
import com.evo.springboot.app.DTO.GenericResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoadDbController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // TODO: REQUIRE PERMISSIONS!
    @PostMapping(value = "api/loadDb")
    public @ResponseBody GenericResponseDTO loadDb(@RequestBody DbDTO companyDb) {
        try {
            logger.info("[CompanyController][api/loadDb] received new request to loadDb");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
//            businessLogic.addCompany(companyDb);

            logger.info("[CompanyController][api/loadDb] loadDb completed successfully");
            return new GenericResponseDTO(
                    String.format("loadDb completed successfully"),
                    true
            );

        } catch (Exception err) {
            logger.error("[CompanyController][api/loadDb] loadDb failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("loadDb failed"),
                    err
            );
        }
    }
}
