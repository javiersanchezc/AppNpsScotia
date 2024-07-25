package com.nps.AppNps.controller;
import com.nps.AppNps.repository.IWPulseRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class WpulseController {

    private final IWPulseRepository service;

    public WpulseController(IWPulseRepository service) {
        this.service = service;
    }

    @GetMapping({"/WResponse"})
    public String getLoad_wPulse_Response_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_wm_response_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_wm_response_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_wPulse_Response_Export(inputFilePath, outputFilePath);
        return "Load_wPulse_Response_Export";
    }

    @GetMapping({"/WCallback"})
    public String getLoad_wPulse_Callbacks_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_wm_callback_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_wm_callback_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_wPulse_Callbacks_Export(inputFilePath, outputFilePath);
        return "Load_wPulse_Callbacks_Exportsss";
    }

    @GetMapping({"/WInvitation"})
    public String getLoad_wPulse_Invitation_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_wm_invitations_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_wm_invitations_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_wPulse_Invitation_Export(inputFilePath, outputFilePath);
        return "Load_wPulse_Invitation_Export";
    }
}
