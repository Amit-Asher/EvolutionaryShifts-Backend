package com.evo.springboot.app.Controllers;

import Arrangement.ArrangementProperties;
import com.evo.springboot.app.Converters.PropertiesConverter;
import com.evo.springboot.bl.BusinessLogic;
import Model.Employee.EmployeePreferences;
import Rule.RuleSlots.RuleSlotsPreference;
import com.evo.springboot.app.Converters.PreferencesConverter;
import com.evo.springboot.app.Converters.SchemaConverter;
import com.evo.springboot.app.DTO.Incoming.EmployeePreferencesDTO;
import com.evo.springboot.app.DTO.Incoming.PropertiesDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import com.evo.springboot.app.DTO.Outgoing.SchemaFamilyDTO;
import com.evo.springboot.app.DTO.Outgoing.SlotsPreferencesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"arrangement", ""})
public class ArrangementController {

    @Autowired
    BusinessLogic businessLogic;

    @Autowired
    PropertiesConverter propertiesConverter;

    @Autowired
    PreferencesConverter preferencesConverter;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "getRulesOptions")
    @GetMapping(value = "getRulesOptions")
    public @ResponseBody
    SchemaFamilyDTO getRulesOptions() {
        try {
            logger.info("[ArrangementController][api/getRulesOptions] received new request to get rules options");
            SchemaFamilyDTO schemaFamilyDTO = SchemaConverter.convert(businessLogic.getRulesOptions());
            logger.info("[ArrangementController][api/getRulesOptions] get rules options completed successfully");
            return schemaFamilyDTO;

        } catch (Exception err) {
            logger.error("[ArrangementController][api/getRulesOptions] get rules options failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("get rules options failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "createArrangement")
    @PostMapping(value = "createArrangement")
    public @ResponseBody
    GenericResponseDTO createArrangement(@RequestBody PropertiesDTO propertiesDTO) {
        try {
            logger.info("[ArrangementController][api/createArrangement] received new request to create new arrangement");
            ArrangementProperties arrangementProperties = propertiesConverter.convert(propertiesDTO);
            businessLogic.startNewArrangement(BusinessLogic.staticCompName);
            businessLogic.setArrangementProperties(
                    BusinessLogic.staticCompName,
                    arrangementProperties
            );

            logger.info("[ArrangementController][api/createArrangement] create new arrangement completed successfully");
            return new GenericResponseDTO(
                    String.format("create new arrangement completed successfully"),
                    true
            );

        } catch (Exception err) {
            logger.error("[ArrangementController][api/createArrangement] create new arrangement failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("create new arrangement failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "getProperties")
    @GetMapping(value = "getProperties")
    public @ResponseBody
    PropertiesDTO getProperties() {
        try {
            logger.info("[ArrangementController][api/getProperties] received new request to get properties");
            ArrangementProperties arrangementProperties = businessLogic.getArrangementProperties(BusinessLogic.staticCompName);
            PropertiesDTO propertiesDTO = propertiesConverter.convert(arrangementProperties);
            logger.info("[ArrangementController][api/getProperties] get properties completed successfully");
            return propertiesDTO;

        } catch (Exception err) {
            logger.error("[ArrangementController][api/getProperties] get properties failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("get properties failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "addPreferences")
    @PostMapping(value = "addPreferences")
    public @ResponseBody
    GenericResponseDTO addPreferences(@RequestBody EmployeePreferencesDTO preferencesDTO) {
        try {
            logger.info("[ArrangementController][api/addPreferences] received new request to add Preferences");
            EmployeePreferences employeePreferences = preferencesConverter.convert(preferencesDTO);
            businessLogic.setEmployeePreference(
                    BusinessLogic.staticCompName,
                    employeePreferences
            );

            logger.info("[ArrangementController][api/addPreferences] add Preferences completed successfully");
            return new GenericResponseDTO(
                    String.format("add Preferences completed successfully"),
                    true
            );

        } catch (Exception err) {
            logger.error("[ArrangementController][api/addPreferences] add Preferences failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add Preferences failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "getPreferences")
    @GetMapping(value = "getPreferences")
    public @ResponseBody
    SlotsPreferencesDTO getPreferences() {
        try {
            logger.info("[ArrangementController][api/getPreferences] received new request to get preferences");
            List<RuleSlotsPreference> preferences = businessLogic.getEmployeeSlotsPreference(BusinessLogic.staticCompName);
            SlotsPreferencesDTO preferencesDTO = preferencesConverter.convert(preferences);

            logger.info("[ArrangementController][api/getPreferences] get preferences completed successfully");
            return preferencesDTO;

        } catch (Exception err) {
            logger.error("[ArrangementController][api/getPreferences] get preferences failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("get preferences failed"),
                    err
            );
        }
    }
}
