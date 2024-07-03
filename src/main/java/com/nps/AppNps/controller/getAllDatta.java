package com.nps.AppNps.controller;

import com.nps.AppNps.Data.ConsultaResultado;
import com.nps.AppNps.repository.loadAllServiceDataTemp;
import com.nps.AppNps.service.loadAllServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class getAllDatta {

    @Autowired
    private loadAllServiceDataTemp loadAllService;
    @Autowired
    private loadAllServiceData loadAllServices;


    @GetMapping("/loadAllData")
    public List<ConsultaResultado> ejecutarConsultasbyDate(@RequestParam(value = "date", required = false) String fechaStr) {
        System.out.println("fechaStr = " + fechaStr);

        LocalDate fechaConsulta;

        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            System.out.println("La fecha es nula o vacía. Usando la fecha actual.");
            fechaConsulta = LocalDate.now();
        } else {
            try {
                // Parsear la fecha con zona horaria si es necesario
                fechaConsulta = OffsetDateTime.parse(fechaStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDate();
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Usando la fecha actual.");
                fechaConsulta = LocalDate.now();
            }
        }

        return loadAllServices.realizarConsultas(fechaConsulta);
    }

    @GetMapping("/loadAllData/{date}")
    public List<ConsultaResultado> ejecutarConsultasbyDatePathVariable(@PathVariable("date") String fechaStr) {
        System.out.println("fechaStr = " + fechaStr);

        LocalDate fechaConsulta;

        try {
            // Parsear la fecha con zona horaria si es necesario
            fechaConsulta = OffsetDateTime.parse(fechaStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDate();
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Usando la fecha actual.");
            fechaConsulta = LocalDate.now();
        }

        return loadAllServices.realizarConsultas(fechaConsulta);
    }
}
