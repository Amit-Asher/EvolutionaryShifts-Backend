package com.evo.springboot.app.Controllers;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import BusinessLogic.BusinessLogic;
import com.evo.springboot.app.Converters.AlgorithmConfigConverter;
import com.evo.springboot.app.Converters.EvolutionStatusConverter;
import com.evo.springboot.app.DTO.Incoming.AlgorithmConfigDTO;
import com.evo.springboot.app.DTO.Outgoing.EvolutionStatusDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class EvolutionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "solveArrangement")
    public @ResponseBody
    GenericResponseDTO solveArrangement(@RequestBody AlgorithmConfigDTO algorithmConfigDTO) {
        try {
            logger.info("[EvolutionController][api/solveArrangement] received new request to solve arrangement");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.blockEmployeesToSetPref(BusinessLogic.staticCompName);
            AlgorithmConfig algorithmConfig = AlgorithmConfigConverter.convert(algorithmConfigDTO);
            businessLogic.startAlgorithm(BusinessLogic.staticCompName, algorithmConfig);

            logger.info("[EvolutionController][api/solveArrangement] solve arrangement completed successfully");
            return new GenericResponseDTO(
                    String.format("solve arrangement completed successfully"),
                    true
            );

        } catch (Exception err) {
            logger.error("[EvolutionController][api/solveArrangement] solve arrangement failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("solve arrangement failed"),
                    err
            );
        }
    }

    @GetMapping(value = "getSolution")
    public @ResponseBody
    EvolutionStatusDTO getSolution() {
        try {
            logger.info("[EvolutionController][api/getSolution] received new request to get evolution result");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            EvolutionStatus evolutionStatus = businessLogic.getSolution(BusinessLogic.staticCompName);
            EvolutionStatusDTO evolutionStatusDTO = EvolutionStatusConverter.convert(evolutionStatus);
            logger.info("[EvolutionController][api/getSolution] get evolution result completed successfully");
            return evolutionStatusDTO;

        } catch (Exception err) {
            logger.error("[EvolutionController][api/getSolution] get evolution result failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("solve arrangement failed"),
                    err
            );
        }
    }
}
