package com.evo.springboot.app.Controllers;
import com.evo.springboot.bl.BusinessLogic;
import com.evo.springboot.app.DTO.Incoming.CompanyDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
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
@Api(value = "", tags = {"company", ""})
public class CompanyController {
    @Autowired
    BusinessLogic businessLogic;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "addCompany")
    @PostMapping(value = "addCompany")
    public @ResponseBody GenericResponseDTO addCompany(@RequestBody CompanyDTO companyDTO) {
        try {
            logger.info("[CompanyController][api/addCompany] received new request to add new company");
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
