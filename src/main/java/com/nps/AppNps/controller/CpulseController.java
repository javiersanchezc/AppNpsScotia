package com.nps.AppNps.controller;
import com.nps.AppNps.repository.ICPulseRepository;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class CpulseController {

    private final ICPulseRepository service;

    public CpulseController(ICPulseRepository service) {
        this.service = service;
    }

    @GetMapping({"/cPulseInvitation"})
    public String getLoad_cPulse_Invitation_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_cpulse_invitations_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_cpulse_invitations_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_Invitation_Export(inputFilePath, outputFilePath);
        return "cPulse_Invitation_Export";
    }

    @GetMapping({"/cPulseResponse"})
    public String getLoad_cPulse_Response_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_cpulse_response_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_cpulse_response_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_Response_Export(inputFilePath, outputFilePath);
        return "cPulse_Response_Export";
    }

    @GetMapping({"/cPulseCallback"})
    public String getLoad_cPulseCallbacks_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_cpulse_callback_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_cpulse_callback_to_vm_modified.csv") String outputFilePath) throws CsvValidationException {
        this.service.Load_cPulse_Callback_Export(inputFilePath, outputFilePath);
        return "cPulse_Response_Export";
    }

    @GetMapping({"/cPulseinlineResponse"})
    public String getLoad_cPulse_inlineResponse_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/sb_cpulse_digital_inline_responses_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/sb_cpulse_digital_inline_responses_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_inlineResponse_Export(inputFilePath, outputFilePath);
        return "cPulse_Response_Export";
    }
}
