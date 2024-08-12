package com.nps.AppNps.repository;

import com.opencsv.exceptions.CsvValidationException;

public interface ICPulseRepository {
    void Load_cPulse_Callback_Export(String paramString1, String paramString2) throws CsvValidationException;

    void Load_cPulse_Response_Export(String paramString1, String paramString2);

    void Load_cPulse_Invitation_Export(String paramString1, String paramString2);
    void loadMasive() throws CsvValidationException;

    void Load_cPulse_inlineResponse_Export(String inputFilePath, String outputFilePath);
}
