package com.evo.springboot.app.Controllers;

import Algorithm.AlgorithmConfig;
import Arrangement.ArrangementProperties;
import com.evo.springboot.bl.BusinessLogic;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Role;
import Model.Slot.ReqSlot;
import Rule.IRule;
import com.evo.springboot.db.Users.UsersRepository;
import com.evo.springboot.app.Converters.AlgorithmConfigConverter;
import com.evo.springboot.app.Converters.EmployeeConverter;
import com.evo.springboot.app.Converters.PreferencesConverter;
import com.evo.springboot.app.Converters.PropertiesConverter;
import com.evo.springboot.app.DTO.Incoming.DbDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dev")
@Api(value = "", tags = {"loadb", ""})
public class LoadDbController {

    @Autowired
    BusinessLogic businessLogic;

    @Autowired
    EmployeeConverter employeeConverter;

    @Autowired
    PropertiesConverter propertiesConverter;

    @Autowired
    PreferencesConverter preferencesConverter;

    @Autowired
    AlgorithmConfigConverter algorithmConfigConverter;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    // TODO: REQUIRE PERMISSIONS!
    @ApiOperation(value = "", nickname = "loadCompanyDb")
    @PostMapping(value = "loadCompanyDb")
    public @ResponseBody GenericResponseDTO loadCompanyDb(@RequestBody DbDTO companyDb) {
        try {
            logger.info("[LoadDbController][api/loadCompanyDb] received new request to load company db");

            // load roles
            companyDb.getRoles().forEach(role -> {
                businessLogic.addNewRole(BusinessLogic.staticCompName, new Role(role.getRole()));
            });

            UsersRepository usersRepository = UsersRepository.getInstance();


            // load employees
            companyDb.getEmployees().forEach(employeeDTO -> {
                Employee newEmployee = employeeConverter.convert(employeeDTO);
                businessLogic.addEmployee(BusinessLogic.staticCompName, newEmployee);
                usersRepository.register(newEmployee.getFullName(), newEmployee.getPassword());
            });


            logger.info("[LoadDbController][api/loadCompanyDb] load company db completed successfully");
            return new GenericResponseDTO(
                    String.format("load company db completed successfully"),
                    true
            );
        } catch (Exception err) {
            logger.error("[LoadDbController][api/loadDb] load company db failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("load company db failed"),
                    err
            );
        }
    }

    // TODO: REQUIRE PERMISSIONS!
    @ApiOperation(value = "", nickname = "loadArrangement")
    @PostMapping(value = "loadArrangement")
    public @ResponseBody GenericResponseDTO loadArrangement(@RequestBody DbDTO companyDb) {
        try {
            logger.info("[LoadDbController][api/loadArrangement] received new request to loadArrangement");

            // load properties
            List<ReqSlot> reqSlots = propertiesConverter.buildReqSlots(companyDb.getProperties());
            List<Employee> activeEmployees = businessLogic.getAllEmployees(BusinessLogic.staticCompName);
            Map<IRule, Double> ruleWeights = PropertiesConverter.buildRuleWeights(companyDb.getProperties());

            ArrangementProperties arrangementProperties = new ArrangementProperties(
                    reqSlots,
                    activeEmployees,
                    ruleWeights
            );

            businessLogic.startNewArrangement(BusinessLogic.staticCompName);
            businessLogic.setArrangementProperties(
                    BusinessLogic.staticCompName,
                    arrangementProperties
            );

            // add preferences
            companyDb.getPreferences().forEach(preferencesDTO -> {
                try {
                    EmployeePreferences employeePreferences = preferencesConverter.convert(preferencesDTO);
                    businessLogic.setEmployeePreference(
                            BusinessLogic.staticCompName,
                            employeePreferences
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

            businessLogic.blockEmployeesToSetPref(BusinessLogic.staticCompName);
            AlgorithmConfig algorithmConfig = algorithmConfigConverter.convert(
                    companyDb.getEvolution()
            );
            businessLogic.startAlgorithm(BusinessLogic.staticCompName, algorithmConfig);

            logger.info("[LoadDbController][api/loadArrangement] load arrangement completed successfully");
            return new GenericResponseDTO(
                    String.format("load arrangement completed successfully"),
                    true
            );
        } catch (Exception err) {
            logger.error("[LoadDbController][api/loadDb] load arrangement failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("load arrangement failed"),
                    err
            );
        }
    }


    // TODO: REQUIRE PERMISSIONS! ONLY FOR DEVELOPMENT
    @ApiOperation(value = "", nickname = "cleanDb")
    @DeleteMapping(value = "cleanDb")
    public @ResponseBody GenericResponseDTO cleanDb() {
        try {
            logger.info("[LoadDbController][api/cleanDb] received new request to cleanDb");
            businessLogic.cleanDb(BusinessLogic.staticCompName);
            logger.info("[LoadDbController][api/cleanDb] cleanDb completed successfully");
            return new GenericResponseDTO(
                    String.format("cleanDb completed successfully"),
                    true
            );

        } catch (Exception err) {
            logger.error("[LoadDbController][api/cleanDb] cleanDb failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("loadDb failed"),
                    err
            );
        }
    }


    @ApiOperation(value = "", nickname = "com/evo/springboot/db")
    @PostMapping(value = "test")
    public String testRoute(@RequestBody Map<String, Object> json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            System.out.println(jsonObject.get("field1"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
