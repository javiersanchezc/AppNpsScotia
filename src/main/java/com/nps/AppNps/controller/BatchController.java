package com.nps.AppNps.controller;


import com.nps.AppNps.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")// Permite solicitudes desde localhost:3000

public class BatchController {

    private final IBPulseService service;
    private final IOptOutService serviceOut;
    private final INPSService    INPSservice;
    private final IHuddlesService Huddleservice;
    private final ICPulseService CPulseservice;
    private final ICardifService Cardifservice;
    private final IBPulseService BPulseservice;

    public BatchController(IBPulseService service, IOptOutService serviceOut, INPSService inpSservice, IHuddlesService huddleservice, ICPulseService cPulseservice, ICardifService cardifservice, IBPulseService bPulseservice) {
        this.service = service;
        this.serviceOut = serviceOut;
        this.INPSservice = inpSservice;
        this.Huddleservice = huddleservice;
        this.CPulseservice = cPulseservice;
        this.Cardifservice = cardifservice;
        this.BPulseservice = bPulseservice;
    }

    @GetMapping("/loadData")
    public ResponseEntity<String> getLoad_BPulse_digital_inline_export() {
        try {
            service.loadMasive();
            serviceOut.loadMasive();
            Huddleservice.loadMasive();
            CPulseservice.loadMasive();
            Cardifservice.loadMasive();
            BPulseservice.loadMasive();
            return ResponseEntity.ok("Datos cargados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar los datos.");
        }
    }


}
