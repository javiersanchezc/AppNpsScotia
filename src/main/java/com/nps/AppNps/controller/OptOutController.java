package com.nps.AppNps.controller;
import com.nps.AppNps.service.IBPulseService;
import com.nps.AppNps.service.IOptOutService;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class OptOutController {

    private final IOptOutService service;

    public OptOutController(IOptOutService service) {
        this.service = service;
    }

    @GetMapping({"/OutExport"})
    public String getLoadOutExport(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_optout_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/sscotiabank_optout_to_vm_modified.csv") String outputFilePath) {
        this.service.LoadScotiabankOutExport(inputFilePath, outputFilePath);
        return "OutExport";
    }
}
