package com.nps.AppNps.controller;


import com.nps.AppNps.service.*;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
    public void getLoad_BPulse_digital_inline_export() {
        // Ruta al archivo .bat
        String scriptPath = "C:\\data\\correAPIS.bat";

        try {
            // Crear un proceso para ejecutar el script .bat
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Leer la salida del script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            System.out.println("El script terminó con el código de salida: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
