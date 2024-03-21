package com.nps.AppNps.controller;
import com.nps.AppNps.service.IBPulseService;
import com.nps.AppNps.service.IHuddlesService;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping({"/api"})
public class HuddlesController {

    private final IHuddlesService service;

    public HuddlesController(IHuddlesService service) {
        this.service = service;
    }

    @GetMapping({"/huddles_export"})
    public String getLoad_huddles_export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_huddle_callbacks_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_huddle_callbacks_to_vm.csv") String outputFilePath) {
        this.service.Load_huddles_export(inputFilePath, outputFilePath);
        return "huddles_export";
    }
}
