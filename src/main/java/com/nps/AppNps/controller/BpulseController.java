package com.nps.AppNps.controller;


import com.nps.AppNps.repository.IBPulseRepository;
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
public class BpulseController {
    @Autowired
    private Tracer tracer;
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new TracingRestTemplateInterceptor(tracer)));

        return restTemplate;
    }
    private final IBPulseRepository service;

    public BpulseController(IBPulseRepository service) {
        this.service = service;
    }

    @GetMapping({"/BPulseDigitalInline"})
    public String getLoad_BPulse_digital_inline_export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_b2b_digital_inline_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_b2b_digital_inline_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_BPulse_digital_inline_export(inputFilePath, outputFilePath);
        return "cPulse_digital_inline_export";
    }

    @GetMapping({"/BPulseCallback"})
    public String getLoad_bPulse_digital_Callback_export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_b2b_callback_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_b2b_callback_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_scotiabank_b2b_callback(inputFilePath, outputFilePath);
        return "scotiabank_b2b_callback";
    }

    @GetMapping({"/BPulseInvitation"})
    public String getLoad_bPulse_Invitation_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_b2b_invitations_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_b2b_invitations_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_bPulse_Invitation_Export(inputFilePath, outputFilePath);
        return "bPulse_Invitation_Export";
    }

    @GetMapping({"/BPulseResponse"})
    public String getLoad_cPulse_response_export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_b2b_responses_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_b2b_responses_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_bPulseResponseExport(inputFilePath, outputFilePath);
        return "bPulse_response_export";
    }
}
