package com.evo.springboot.app.Controllers;

import BusinessLogic.BusinessLogic;
import com.evo.springboot.app.DTO.Incoming.CredentialsDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import com.evo.springboot.app.Services.AuthService;
import com.evo.springboot.app.Services.RequestContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"login", ""})
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String EVO_TOKEN = "evo-token";
    private static final int TOKEN_TIMEOUT_SEC = 300; // 5 minutes

    private ResponseCookie createCookie(String key, String value, Integer expSeconds) {
        return ResponseCookie.from(key, value)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(expSeconds)
                .build();
    }

    @ApiOperation(value = "", nickname = "doLogin")
    @PostMapping(value = "doLogin")
    public ResponseEntity<GenericResponseDTO> doLogin(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            logger.info("[LoginController][api/doLogin] received new request to do login");
            boolean success = BusinessLogic.getInstance().doLogin(credentialsDTO.getUsername(), credentialsDTO.getPassword());
            if (!success) {
                logger.error(String.format("[LoginController][api/doLogin] invalid credentials."));
                throw new RuntimeException("invalid credentials");
            }
            String token = AuthService.generateToken(BusinessLogic.staticCompName, credentialsDTO.getUsername());
            ResponseCookie springCookie = createCookie(EVO_TOKEN, token, 180);
            logger.info("[LoginController][api/doLogin] do login completed successfully");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body((new GenericResponseDTO("login completed successfully", true)));
        } catch (Exception err) {
            logger.error(String.format("[LoginController][api/doLogin] do login failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("do login failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "doSignup")
    @PostMapping(value = "doSignup")
    public ResponseEntity<GenericResponseDTO> doSignup(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            logger.info("[LoginController][api/doSignup] received new request to do signup");
            BusinessLogic.getInstance().doSignup(credentialsDTO.getUsername(), credentialsDTO.getPassword());
            String token = AuthService.generateToken(BusinessLogic.staticCompName, credentialsDTO.getUsername());
            ResponseCookie springCookie = createCookie(EVO_TOKEN, token, TOKEN_TIMEOUT_SEC);
            logger.info("[LoginController][api/doSignup] do signup completed successfully");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body((new GenericResponseDTO("signup completed successfully", true)));
        } catch (Exception err) {
            logger.error(String.format("[LoginController][api/doSignup] do signup failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("do signup failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "doLogout")
    @PostMapping(value = "doLogout")
    public ResponseEntity<GenericResponseDTO> doLogout() {
        try {
            // NOTE: we dont clean the cookie on the frontend because we cant!
            // javascript cant access http-only cookie for security reasons :)
            logger.info("[LoginController][api/doLogout] received new request to do logout");
            ResponseCookie springCookie = createCookie(EVO_TOKEN, null, 0); // delete cookie
            logger.info("[LoginController][api/doLogout] do logout completed successfully");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body((new GenericResponseDTO("logout completed successfully", true)));
        } catch (Exception err) {
            logger.error(String.format("[LoginController][api/doLogout] do logout failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("do logout failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "doSignout")
    @PostMapping(value = "doSignout")
    public ResponseEntity<GenericResponseDTO> doSignout(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            logger.info("[LoginController][api/doSignout] received new request to do signout");
            BusinessLogic.getInstance().doSignout(credentialsDTO.getUsername());
            ResponseCookie springCookie = createCookie(EVO_TOKEN, null, 0); // delete cookie
            logger.info("[LoginController][api/doSignout] do signout completed successfully");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body((new GenericResponseDTO("signout completed successfully", true)));
        } catch (Exception err) {
            logger.error(String.format("[LoginController][api/doSignout] do signout failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("do signout failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "doSilentLogin")
    @PostMapping(value = "doSilentLogin")
    public ResponseEntity<GenericResponseDTO> doSilentLogin() {
        try {
            return ResponseEntity
                    .ok()
                    .body((new GenericResponseDTO("silent login completed successfully", true)));
        } catch (Exception err) {
            logger.error(String.format("[LoginController][api/doSilentLogin] do silent login failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("do silent login failed"),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "keepAlive")
    @PostMapping(value = "keepAlive")
    public ResponseEntity<GenericResponseDTO> keepAlive(HttpServletRequest request) {
        try {
            RequestContext requestContext = AuthService.extractRequestContext(request);
            String companyName = requestContext.getCompany(); // should be companyId
            String employeeName = requestContext.getEmployee(); // should be employeeId

            String token = AuthService.generateToken(BusinessLogic.staticCompName, employeeName);
            ResponseCookie springCookie = createCookie(EVO_TOKEN, token, TOKEN_TIMEOUT_SEC); // 3:5 minutes (KA:invalidation)
            logger.info("[LoginController][api/doLogin] do keepAlive completed successfully");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body((new GenericResponseDTO("login keepAlive successfully", true)));
        } catch (Exception err) {
            logger.error(String.format("[LoginController][api/keepAlive] do keepAlive failed: %s", err));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("do keepAlive failed"),
                    err
            );
        }
    }
}
