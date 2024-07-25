package com.nps.AppNps.bach;

import com.nps.AppNps.repository.*;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final IBPulseRepository service;
    private final IOptOutRepository serviceOut;
    private final INPSRepository INPSservice;
    private final IHuddlesRepository Huddleservice;
    private final ICPulseRepository CPulseservice;
    private final ICardifRepository Cardifservice;
    private final IBPulseRepository BPulseservice;

    @Autowired
    public DataLoader(IBPulseRepository service, IOptOutRepository serviceOut, INPSRepository INPSservice,
                      IHuddlesRepository Huddleservice, ICPulseRepository CPulseservice,
                      ICardifRepository Cardifservice, IBPulseRepository BPulseservice) {
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
