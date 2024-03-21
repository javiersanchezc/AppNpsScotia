package com.nps.AppNps.service;

public interface IWPulseService {
    void Load_wPulse_Invitation_Export();

    void Load_wPulse_Callbacks_Export();

    void Load_wPulse_Response_Export();

    void Load_wPulse_Callbacks_Export(String paramString1, String paramString2);

    void Load_wPulse_Response_Export(String paramString1, String paramString2);

    void Load_wPulse_Invitation_Export(String paramString1, String paramString2);
}
