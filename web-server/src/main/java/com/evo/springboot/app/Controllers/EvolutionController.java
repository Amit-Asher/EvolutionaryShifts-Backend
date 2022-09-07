package com.evo.springboot.app.Controllers;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import com.evo.springboot.app.DTO.Outgoing.ShiftDTO;
import com.evo.springboot.bl.BusinessLogic;
import Schemas.SchemaFamily;
import com.evo.springboot.app.Converters.AlgorithmConfigConverter;
import com.evo.springboot.app.Converters.EvolutionStatusConverter;
import com.evo.springboot.app.Converters.SchemaConverter;
import com.evo.springboot.app.DTO.Incoming.AlgorithmConfigDTO;
import com.evo.springboot.app.DTO.Outgoing.EvolutionStatusDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import com.evo.springboot.app.DTO.Outgoing.SchemaFamilyDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"evolution", ""})
public class EvolutionController {

    @Autowired
    BusinessLogic businessLogic;

    @Autowired
    AlgorithmConfigConverter algorithmConfigConverter;

    @Autowired
    EvolutionStatusConverter evolutionStatusConverter;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "getSchemas")
    @GetMapping(value = "getSchemas")
    public @ResponseBody List<SchemaFamilyDTO> getSchemas() {
        try {
            logger.info("[EvolutionController][api/getOperatorsSchemas] received new request to get operators schemas");
            List<SchemaFamily> schemaFamilies = businessLogic.getSchemasFamilies();
            List<SchemaFamilyDTO> schemaFamiliesDto = SchemaConverter.covert(schemaFamilies);
            logger.info("[EvolutionController][api/getOperatorsSchemas] get operators schemas completed successfully");
            return schemaFamiliesDto;
        } catch (Exception err) {
            logger.error(String.format("[EvolutionController][api/getOperatorsSchemas] get operators schemas failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("get operators schemas failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "solveArrangement")
    @PostMapping(value = "solveArrangement")
    public @ResponseBody
    GenericResponseDTO solveArrangement(@RequestBody AlgorithmConfigDTO algorithmConfigDTO) {
        try {
            logger.info("[EvolutionController][api/solveArrangement] received new request to solve arrangement");
            businessLogic.blockEmployeesToSetPref(BusinessLogic.staticCompName);
            AlgorithmConfig algorithmConfig = algorithmConfigConverter.convert(algorithmConfigDTO);
            businessLogic.startAlgorithm(BusinessLogic.staticCompName, algorithmConfig);

            logger.info("[EvolutionController][api/solveArrangement] solve arrangement completed successfully");
            return new GenericResponseDTO(
                    String.format("solve arrangement completed successfully"),
                    true
            );

        } catch (Exception err) {
            logger.error(String.format("[EvolutionController][api/solveArrangement] solve arrangement failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("solve arrangement failed"),
                    err
            );
        }
    }

//    int gen = 0;
//    int fit=0;

    @ApiOperation(value = "", nickname = "getSolution")
    @GetMapping(value = "getSolution")
    public @ResponseBody
    EvolutionStatusDTO getSolution() {
        try {
            logger.info("[EvolutionController][api/getSolution] received new request to get evolution result");
            EvolutionStatus evolutionStatus = businessLogic.getSolution(BusinessLogic.staticCompName);
            EvolutionStatusDTO evolutionStatusDTO = evolutionStatusConverter.convert(evolutionStatus);
            logger.info("[EvolutionController][api/getSolution] get evolution result completed successfully");
            return evolutionStatusDTO;

//            gen++;
//            fit++;
//            ShiftDTO s1 = new ShiftDTO("e_id1 ", "ando1 " + gen, "waiter","2022-05-26 08:00", "2022-05-26 16:00");
//            ShiftDTO s2 = new ShiftDTO("e_id2 ", "ando2 " + gen, "waiter","2022-05-25 08:00", "2022-05-25 16:00");
//
//            List<ShiftDTO> l = new ArrayList<>(Arrays.asList(new ShiftDTO[]{s1, s2}));
//
//            if(gen % 25 == 0)
//                return new EvolutionStatusDTO(l,gen,fit,true, gen);
//            else
//            return new EvolutionStatusDTO(l,gen,fit,false, gen);

        } catch (Exception err) {
            logger.error(String.format("[EvolutionController][api/getSolution] get evolution result failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("solve arrangement failed"),
                    err
            );
        }
    }
}
