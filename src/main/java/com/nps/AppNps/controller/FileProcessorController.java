package com.nps.AppNps.controller;

import com.nps.AppNps.bach.FileProcessorThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileProcessorController {

    private final FileProcessorThread fileProcessorThread;

    @Autowired
    public FileProcessorController(FileProcessorThread fileProcessorThread) {
        this.fileProcessorThread = fileProcessorThread;
    }

    @GetMapping("/startFileProcessing")
    public void startFileProcessing() {
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
