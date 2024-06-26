package com.nps.AppNps.bach;

import com.nps.AppNps.service.*;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final IBPulseService service;
    private final IOptOutService serviceOut;
    private final INPSService INPSservice;
    private final IHuddlesService Huddleservice;
    private final ICPulseService CPulseservice;
    private final ICardifService Cardifservice;
    private final IBPulseService BPulseservice;

    @Autowired
    public DataLoader(IBPulseService service, IOptOutService serviceOut, INPSService INPSservice,
                      IHuddlesService Huddleservice, ICPulseService CPulseservice,
                      ICardifService Cardifservice, IBPulseService BPulseservice) {
        this.service = service;
        this.serviceOut = serviceOut;
        this.INPSservice = INPSservice;
        this.Huddleservice = Huddleservice;
        this.CPulseservice = CPulseservice;
        this.Cardifservice = Cardifservice;
        this.BPulseservice = BPulseservice;
    }

    public void loadAllData() throws CsvValidationException {
        service.loadMasive();
        serviceOut.loadMasive();
        Huddleservice.loadMasive();
        CPulseservice.loadMasive();
        Cardifservice.loadMasive();
        BPulseservice.loadMasive();
    }
}
