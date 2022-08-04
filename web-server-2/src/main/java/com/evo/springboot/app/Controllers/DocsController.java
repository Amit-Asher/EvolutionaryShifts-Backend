package com.evo.springboot.app.Controllers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(value = "", tags = {"docs", ""})
public class DocsController {
    @GetMapping("/docs")
    public String docs() {
        return "redirect:/swagger-ui.html";
    }
}
