package com.nps.AppNps.controller;
import com.nps.AppNps.repository.IOptOutRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class OptOutController {

    private final IOptOutRepository service;

    public OptOutController(IOptOutRepository service) {
        this.service = service;
    }

    @GetMapping({"/OutExport"})
    public String getLoadOutExport(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_optout_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_optout_to_vm_modified.csv") String outputFilePath) {
        this.service.LoadScotiabankOutExport(inputFilePath, outputFilePath);
        return "OutExport";
    }
}
