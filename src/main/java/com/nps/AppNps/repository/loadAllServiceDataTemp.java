package com.nps.AppNps.repository;

import com.nps.AppNps.Data.ConsultaResultado;
import com.nps.AppNps.loadProces.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class loadAllServiceDataTemp {

    private final CsvToSqlServerBPulse_digital_inline_export csvToSqlServerBPulse_digital_inline_export;
    private final CsvToSqlServerBPulse_Invitation_Export csvToSqlServerBPulse_Invitation_Export;
    private final CsvToSqlServerBPulsescotiabank_b2b_callback csvToSqlServerBPulsescotiabank_b2b_callback;
    private final CsvToSqlServerBPulsescotiabank_b2b_responses_export csvToSqlServerBPulsescotiabank_b2b_responses_export;
    private final CsvToSqlServerCPulseCallBacksExport csvToSqlServerCPulseCallBacksExport;
    private final CsvToSqlServerCPulseInvitationsExport csvToSqlServerCPulseInvitationsExport;
    private final CsvToSqlServerCPulseResponseExport csvToSqlServerCPulseResponseExport;
    private final CsvToSqlServerCsvToSqlServercPulseCardifCallbackExport csvToSqlServerCsvToSqlServercPulseCardifCallbackExport;
    private final CsvToSqlServerCsvToSqlServercPulseCardiffOptOutExport csvToSqlServerCsvToSqlServercPulseCardiffOptOutExport;
    private final CsvToSqlServerCsvToSqlServercPulseCardifInvitationExport csvToSqlServerCsvToSqlServercPulseCardifInvitationExport;
    private final CsvToSqlServerCsvToSqlServercPulseCardifResponsesExport csvToSqlServerCsvToSqlServercPulseCardifResponsesExport;
    private final CsvToSqlServerHuddlesExport csvToSqlServerHuddlesExport;
    private final CsvToSqlServerOptOutExport csvToSqlServerOptOutExport;
    private final CsvToSqlServerWPulse_Response_Export csvToSqlServerWPulse_Response_Export;
    private final CsvToSqlServerWPulseCallbacksExport csvToSqlServerWPulseCallbacksExport;
    private final CsvToSqlServerWPulseInvitationsExport csvToSqlServerWPulseInvitationsExport;

    public loadAllServiceDataTemp(CsvToSqlServerBPulse_digital_inline_export csvToSqlServerBPulse_digital_inline_export, CsvToSqlServerBPulse_Invitation_Export csvToSqlServerBPulse_invitation_export, CsvToSqlServerBPulsescotiabank_b2b_callback csvToSqlServerBPulsescotiabank_b2b_callback, CsvToSqlServerBPulsescotiabank_b2b_responses_export csvToSqlServerBPulsescotiabank_b2b_responses_export, CsvToSqlServerCPulseCallBacksExport csvToSqlServerCPulseCallBacksExport, CsvToSqlServerCPulseInvitationsExport csvToSqlServerCPulseInvitationsExport, CsvToSqlServerCPulseResponseExport csvToSqlServerCPulseResponseExport, CsvToSqlServerCsvToSqlServercPulseCardifCallbackExport csvToSqlServerCsvToSqlServercPulseCardifCallbackExport, CsvToSqlServerCsvToSqlServercPulseCardiffOptOutExport csvToSqlServerCsvToSqlServercPulseCardiffOptOutExport, CsvToSqlServerCsvToSqlServercPulseCardifInvitationExport csvToSqlServerCsvToSqlServercPulseCardifInvitationExport, CsvToSqlServerCsvToSqlServercPulseCardifResponsesExport csvToSqlServerCsvToSqlServercPulseCardifResponsesExport, CsvToSqlServerHuddlesExport csvToSqlServerHuddlesExport, CsvToSqlServerOptOutExport csvToSqlServerOptOutExport, CsvToSqlServerWPulse_Response_Export csvToSqlServerWPulse_response_export, CsvToSqlServerWPulseCallbacksExport csvToSqlServerWPulseCallbacksExport, CsvToSqlServerWPulseInvitationsExport csvToSqlServerWPulseInvitationsExport) {
        this.csvToSqlServerBPulse_digital_inline_export = csvToSqlServerBPulse_digital_inline_export;
        csvToSqlServerBPulse_Invitation_Export = csvToSqlServerBPulse_invitation_export;
        this.csvToSqlServerBPulsescotiabank_b2b_callback = csvToSqlServerBPulsescotiabank_b2b_callback;
        this.csvToSqlServerBPulsescotiabank_b2b_responses_export = csvToSqlServerBPulsescotiabank_b2b_responses_export;
        this.csvToSqlServerCPulseCallBacksExport = csvToSqlServerCPulseCallBacksExport;
        this.csvToSqlServerCPulseInvitationsExport = csvToSqlServerCPulseInvitationsExport;
        this.csvToSqlServerCPulseResponseExport = csvToSqlServerCPulseResponseExport;
        this.csvToSqlServerCsvToSqlServercPulseCardifCallbackExport = csvToSqlServerCsvToSqlServercPulseCardifCallbackExport;
        this.csvToSqlServerCsvToSqlServercPulseCardiffOptOutExport = csvToSqlServerCsvToSqlServercPulseCardiffOptOutExport;
        this.csvToSqlServerCsvToSqlServercPulseCardifInvitationExport = csvToSqlServerCsvToSqlServercPulseCardifInvitationExport;
        this.csvToSqlServerCsvToSqlServercPulseCardifResponsesExport = csvToSqlServerCsvToSqlServercPulseCardifResponsesExport;
        this.csvToSqlServerHuddlesExport = csvToSqlServerHuddlesExport;
        this.csvToSqlServerOptOutExport = csvToSqlServerOptOutExport;
        csvToSqlServerWPulse_Response_Export = csvToSqlServerWPulse_response_export;
        this.csvToSqlServerWPulseCallbacksExport = csvToSqlServerWPulseCallbacksExport;
        this.csvToSqlServerWPulseInvitationsExport = csvToSqlServerWPulseInvitationsExport;
    }

    public List<ConsultaResultado> realizarConsultas(LocalDate fechaConsulta) {
        List<ConsultaResultado> resultados = new ArrayList<>();
        resultados.addAll(csvToSqlServerBPulse_digital_inline_export.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerBPulse_Invitation_Export.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerBPulsescotiabank_b2b_callback.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerBPulsescotiabank_b2b_responses_export.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCPulseCallBacksExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCPulseInvitationsExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCPulseResponseExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCsvToSqlServercPulseCardifCallbackExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCsvToSqlServercPulseCardiffOptOutExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCsvToSqlServercPulseCardifInvitationExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerCsvToSqlServercPulseCardifResponsesExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerHuddlesExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerOptOutExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerWPulse_Response_Export.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerWPulseCallbacksExport.realizarConsultas(fechaConsulta));
        resultados.addAll(csvToSqlServerWPulseInvitationsExport.realizarConsultas(fechaConsulta));
        return resultados;
    }
}
