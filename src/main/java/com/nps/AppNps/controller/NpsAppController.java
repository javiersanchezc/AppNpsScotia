package com.nps.AppNps.controller;
import com.nps.AppNps.repository.INPSRepository;
import com.nps.AppNps.repository.IWPulseRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class NpsAppController {

    private final INPSRepository service;

    private final IWPulseRepository servicew;

    public NpsAppController(INPSRepository service, IWPulseRepository servicew) {
        this.service = service;
        this.servicew = servicew;
    }

    @GetMapping({"/load"})
    public String getLoadnps() {
        this.servicew.Load_wPulse_Invitation_Export();
        this.servicew.Load_wPulse_Callbacks_Export();
        this.servicew.Load_wPulse_Response_Export();
        return "Load_wPulse_Invitation_Export";
    }

    @GetMapping({"/scotiabank_enps_responses_cx_level"})
    public String getLoad_scotiabank_enps_responses_cx_level(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_wm_invitations_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_wm_invitations_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_scotiabank_enps_responses_cx_level(inputFilePath, outputFilePath);
        return "scotiabank_enps_responses_cx_level";
    }

    @GetMapping({"/scotiabank_enps_responses_team_level"})
    public String getLoad_scotiabank_enps_responses_team_level(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/scotiabank_wm_invitations_to_vm_historical_2022_to_2023_2023-12-08.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/scotiabank_wm_invitations_to_vm_historical_2022_to_2023_2023-12-08_modified.csv") String outputFilePath) {
        this.service.Load_scotiabank_enps_responses_team_level(inputFilePath, outputFilePath);
        return "scotiabank_enps_responses_team_level";
    }
}
