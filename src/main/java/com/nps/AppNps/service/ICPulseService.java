package com.nps.AppNps.service;

import com.opencsv.exceptions.CsvValidationException;

public interface ICPulseService {
    void Load_cPulse_Callback_Export(String paramString1, String paramString2) throws CsvValidationException;

    void Load_cPulse_Response_Export(String paramString1, String paramString2);

    void Load_cPulse_Invitation_Export(String paramString1, String paramString2);
    void loadMasive() throws CsvValidationException;
}
