package com.nps.AppNps.controller;

import com.nps.AppNps.service.ITrasformfiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class ModifiedFiles {
    private final ITrasformfiles service;

    public ModifiedFiles(ITrasformfiles service) {
        this.service = service;
    }

    @GetMapping("/GetFiles/{fileName}")
    public ResponseEntity<String> getFileContent(@PathVariable String fileName) {
        try {
            String fileContent = service.getFiles(fileName);
            System.out.println("file = " + fileName);
            return ResponseEntity.ok(fileContent);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
