package com.nps.AppNps.controller;
import com.nps.AppNps.repository.ICardifRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class CardifController {

    private final ICardifRepository service;

    public CardifController(ICardifRepository service) {
        this.service = service;
    }

    @GetMapping({"/CardifCallbackExport"})
    public String getLoad_cPulseInsuranceCardifCallbackExport(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/sb_insurance_cardif_callback_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/sb_insurance_cardif_callback_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_Insurance_Cardif_Callback_Export(inputFilePath, outputFilePath);
        return "cPulseInsuranceCardifCallbackExport";
    }

    @GetMapping({"/CardifInvitationExport"})
    public String getLoad_cPulseInsuranceCardifInvitationExport(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/sb_insurance_cardif_Invitations_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/sb_insurance_cardif_Invitations_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_Insurance_Cardif_Invitations_Export(inputFilePath, outputFilePath);
        return "cPulseInsuranceCardifInvitationExport";
    }

    @GetMapping({"/CardifResponsesExport"})
    public String getLoad_cPulse_Insurance_Cardif_Responses_Export(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/sb_insurance_cardif_responses_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/sb_insurance_cardif_responses_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_Insurance_Cardif_Responses_Export(inputFilePath, outputFilePath);
        return "cPulse_Insurance_Cardif_Responses_Export";
    }

    @GetMapping({"/cPulseInsuranceCardiffOptOutExport"})
    public String getLoad_cPulseInsuranceCardiffOptOutExport(@RequestParam(name = "inputFilePath", defaultValue = "C:/data/sb_insurance_cardif_optout_to_vm.csv") String inputFilePath, @RequestParam(name = "outputFilePath", defaultValue = "C:/data/sb_insurance_cardif_optout_to_vm_modified.csv") String outputFilePath) {
        this.service.Load_cPulse_Insurance_Cardif_OptOut_Export(inputFilePath, outputFilePath);
        return "cPulseInsuranceCardiffOptOutExport";
    }
}
