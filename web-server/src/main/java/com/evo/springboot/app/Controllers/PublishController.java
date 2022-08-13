package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"publish", ""})
public class PublishController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "publishArrangement")
    @PostMapping(value = "publishArrangement")
    public @ResponseBody
    GenericResponseDTO publishArrangement() {
        try {
            logger.info("[PublishController][api/publishArrangement] received new request to publish arrangement");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.publishArrangement(BusinessLogic.staticCompName);
            logger.info("[PublishController][api/publishArrangement] publish arrangement completed successfully");
            return new GenericResponseDTO(
                    String.format("publish arrangement completed successfully"),
                    true
            );
        } catch (Exception err) {
            logger.error("[PublishController][api/publishArrangement] publish arrangement failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("publish arrangement failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "finishArrangement")
    @PostMapping(value = "finishArrangement")
    public @ResponseBody
    GenericResponseDTO finishArrangement() {
        try {
            logger.info("[PublishController][api/finishArrangement] received new request to finish arrangement");
            BusinessLogic businessLogic = BusinessLogic.getInstance();
            businessLogic.finishArrangement(BusinessLogic.staticCompName);
            logger.info("[PublishController][api/finishArrangement] finish arrangement completed successfully");
            return new GenericResponseDTO(
                    String.format("finish arrangement completed successfully"),
                    true
            );
        } catch (Exception err) {
            logger.error("[PublishController][api/finishArrangement] finish arrangement failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("finish arrangement failed"),
                    err
            );
        }
    }
}
