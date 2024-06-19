package com.nps.AppNps.service;

public interface IBPulseService {
    void Load_BPulse_digital_inline_export(String paramString1, String paramString2);

    void Load_bPulse_Invitation_Export(String paramString1, String paramString2);

    void Load_scotiabank_b2b_callback(String paramString1, String paramString2);

    void Load_bPulseResponseExport(String paramString1, String paramString2);

    void loadMasive();
    void loadMasivebach();
}
