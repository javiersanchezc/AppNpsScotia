package com.nps.AppNps.controller;
import com.nps.AppNps.repository.IHuddlesRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class HuddlesController {

    private final IHuddlesRepository service;

    public HuddlesController(IHuddlesRepository service) {
        this.service = service;
    }

    @GetMapping({"/huddles_export"})
    public String getLoad_huddles_export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_huddle_callbacks_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_huddle_callbacks_to_vm.csv") String outputFilePath) {
        this.service.Load_huddles_export(inputFilePath, outputFilePath);
        return "huddles_export";
    }
}
