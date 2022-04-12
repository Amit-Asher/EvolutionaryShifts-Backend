package com.evolution.springboot.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainResolver {

    // install spring cli from here: https://docs.spring.io/spring-boot/docs/current/reference/html/cli.html
    // run the script: spring run web-server/src/main/java/com/evolution/springboot/app/MainResolver.java

    @GetMapping("/api/v1/addEmployee")
    String addEmployee() {
        return "addEmployee";
    }

    @GetMapping("/api/v1/removeEmployee")
    String removeEmployee() {
        return "removeEmployee";
    }

    @GetMapping("/api/v1/setAsManager")
    String setAsManager() {
        return "setAsManager";
    }

    @GetMapping("/api/v1/removeManager")
    String removeManager() {
        return "removeManager";
    }

    @GetMapping("/api/v1/addNewRole")
    String addNewRole() {
        return "addNewRole";
    }

    @GetMapping("/api/v1/removeRole")
    String removeRole() {
        return "removeRole";
    }

    @GetMapping("/api/v1/setArrangementProperties")
    String setArrangementProperties() {
        return "setArrangementProperties";
    }

    @GetMapping("/api/v1/setEmployeePreference")
    String setEmployeePreference() {
        return "setEmployeePreference";
    }

    @GetMapping("/api/v1/blockEmployeesToSetPref")
    String blockEmployeesToSetPref() {
        return "blockEmployeesToSetPref";
    }

    @GetMapping("/api/v1/startAlgorithm")
    String startAlgorithm() {
        return "startAlgorithm";
    }

    @GetMapping("/api/v1/getSolution")
    String getSolution() {
        return "getSolution";
    }

    @GetMapping("/api/v1/publishArrangement")
    String publishArrangement() {
        return "publishArrangement";
    }

    @GetMapping("/api/v1/approveArrangement")
    String approveArrangement() {
        return "approveArrangement";
    }

    @GetMapping("/api/v1/declineArrangement")
    String declineArrangement() {
        return "declineArrangement";
    }

    @GetMapping("/api/v1/setArrangement")
    String setArrangement() {
        return "setArrangement";
    }

    @GetMapping("/api/v1/finishArrangement")
    String finishArrangement() {
        return "finishArrangement";
    }

    @GetMapping("/api/v1/reopenArrangement")
    String reopenArrangement() {
        return "reopenArrangement";
    }
}
