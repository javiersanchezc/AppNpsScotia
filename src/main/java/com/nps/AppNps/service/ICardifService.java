package com.nps.AppNps.service;

public interface ICardifService {
    void Load_cPulse_Insurance_Cardif_OptOut_Export(String paramString1, String paramString2);

    void Load_cPulse_Insurance_Cardif_Invitations_Export(String paramString1, String paramString2);

    void Load_cPulse_Insurance_Cardif_Callback_Export(String paramString1, String paramString2);

    void Load_cPulse_Insurance_Cardif_Responses_Export(String paramString1, String paramString2);

    void loadMasive();

}
