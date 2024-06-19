package com.nps.AppNps.controller;

import com.nps.AppNps.bach.FileProcessorThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String startFileProcessing() {
        if (!fileProcessorThread.isAlive()) {
            fileProcessorThread.start();
            return "File processing started.";
        } else {
            return "File processing is already running.";
        }
    }
}
