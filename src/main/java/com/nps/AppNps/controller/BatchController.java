package com.nps.AppNps.controller;


import com.nps.AppNps.repository.*;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")// Permite solicitudes desde localhost:3000

public class BatchController {

    private final IBPulseRepository service;
    private final IOptOutRepository serviceOut;
    private final INPSRepository INPSservice;
    private final IHuddlesRepository Huddleservice;
    private final ICPulseRepository CPulseservice;
    private final ICardifRepository Cardifservice;
    private final IBPulseRepository BPulseservice;

    public BatchController(IBPulseRepository service, IOptOutRepository serviceOut, INPSRepository inpSservice, IHuddlesRepository huddleservice, ICPulseRepository cPulseservice, ICardifRepository cardifservice, IBPulseRepository bPulseservice) {
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
