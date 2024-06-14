package com.nps.AppNps.controller;

import com.nps.AppNps.Data.ConsultaResultado;
import com.nps.AppNps.repository.loadAllServiceDataTemp;
import com.nps.AppNps.service.loadAllServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class getAllDatta {

    @Autowired
    private loadAllServiceDataTemp loadAllService;
    @Autowired
    private loadAllServiceData loadAllServices;

    @GetMapping({"/loadAllDatass"})
    public List<ConsultaResultado> ejecutarConsultas(@RequestParam(value = "fecha", required = false) String fechaStr) {
        LocalDate fechaConsulta = (fechaStr != null) ? LocalDate.parse(fechaStr, DateTimeFormatter.ISO_DATE) : LocalDate.now();
        return loadAllService.realizarConsultas(fechaConsulta);
    }

    @GetMapping("/loadAllData")
    public List<ConsultaResultado> ejecutarConsultasbyDate(@RequestParam(value = "fecha", required = false) String fechaStr) {
        LocalDate fechaConsulta = (fechaStr != null) ? LocalDate.parse(fechaStr, DateTimeFormatter.ISO_DATE) : LocalDate.now();
        return loadAllServices.realizarConsultas(fechaConsulta);
    }


}
