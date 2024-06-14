package com.nps.AppNps.service;

import com.nps.AppNps.loadProces.*;


import org.springframework.stereotype.Service;

@Service
public class BPulseServiceimpl implements IBPulseService {
    public void Load_bPulseResponseExport(String inputFilePath, String outputFilePath) {
        String headerFileout = "Surveyid,Response_Date_EST,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened,Current_status_of_alert,All_log_notes_combined_if_any,Email,First_name,Last_name,BSC_Agent_ID,BSC_Agent_Name,BSC_Type,B2B_Channel_Description,Client_ID,Client_Name,Title,Customer_Type,Coverage_Team_ID,Coverage_Team_Name,B2B_Channel_Code,Email_Client_ID_Concatenate,Intended_Survey_Send_Date,Reference_ID,Relationship_Unique_ID,Callback_Role,Category_Code_Description,Closure_Status,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Issue_Pending,Customer_Issue_Raised,Customer_Suggestions_Follow_up_Actions_Additional_Comments,Number_of_callbacks_attempted,Outcome,Outcome_Note,Team_Owner_CCC,Team_Owner_Digital,Transaction_Employee_1,Transaction_Employee_2,Transaction_Employee_3,Transaction_Employee_4,Transaction_Employee_5,Contact_Method_Description,Team_Member,Survey_Type,Account_Type,Activity_Description,Activity_ID,Status_Description,Status_ID,Category,Contact_Method,Business_Phone,Country,Country_Description,Customer_Preferred_Language,Interaction_Date,Mobile_Phone,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Product_Code,Transit_ID,Transit_Name,Translations_Coverage_Bsc_CSS_Team_Comment,Translations_Coverage_Bsc_CSS_Team_Reason,Translations_LTR_Comment,Translations_Relationship_Drivers_Comment,BSC_CSS_Team_ID,BSC_CSS_Team_Name,Relationship_Manager,Translations_Relationship_Comment,Translations_Other_Financial_Institutions_Comment,Translations_Other_Comments,Relationship_Team_ID,Callback_Form_Up,Rapid_Response_Sent,Relationship_Team_Name,Agent_OSAT,Comment,Banco_BBVA,Banco_BBVA_NPS,Banco_Davivienda,Banco_Davivienda_NPS,Banco_de_Bogot";
        CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
        csvTransformer.transformCsv();
        CsvToSqlServerBPulsescotiabank_b2b_responses_export b2b_responses_export = new CsvToSqlServerBPulsescotiabank_b2b_responses_export();
        b2b_responses_export.convertCsvToSqlServer();
    }

    @Override
    public void loadMasive() {
        CsvToSqlServerBPulse_digital_inline_export digital_inline_export = new CsvToSqlServerBPulse_digital_inline_export();
        digital_inline_export.convertCsvToSqlServer();
        CsvToSqlServerBPulse_Invitation_Export Invitation_Export = new CsvToSqlServerBPulse_Invitation_Export();
        Invitation_Export.convertCsvToSqlServer();
        CsvToSqlServerBPulsescotiabank_b2b_callback callback = new CsvToSqlServerBPulsescotiabank_b2b_callback();
        callback.convertCsvToSqlServer();
    }

    public void Load_BPulse_digital_inline_export(String inputFilePath, String outputFilePath) {
        String headerFileout = "Digital_Form,Digital_Form_Name,First_name,Last_name,Property_name,Property_identifier,Email,Gender,Activity_Description,Activity_ID,Agent_ID,Business_Phone,Category,Channel_Code,Contact_Method,Country,Customer_ID,Survey_viewed_mobile_device,All_Segments,Home_Phone,Interaction_Date,Mobile_Phone,Customer_Preferred_Language,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Salutation,Status_Description,Status_ID,Survey_Method,Channel,Business_ID,Transit_ID,Transit_Name,Response_Date_EST datetime,Invitation_Date_EST datetime,Contact_Status,Closure_Status,Outcome,Outcome_Note,Resolution,Resolution_Note,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Reached,Customer_Issue_Escalated,Customer_Issue_Pending,Customer_Suggestions,Coaching_Comments,Additional_Comments,Followup_Actions,Current_status_alert,All_log_notes_combined,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened int,Interaction_Date_Timestamp,Number_callbacks_attempted,Team_Member,Team_Owner_CCC,Team_Owner_Digital,Callback_Form_Updated,Rapid_Response_Sent,Site_Name,Team_Name,Customer_Issue_Raised,Customer_Suggestions_Followup_Actions_Additional_Comments,Branch_Roles,Customer_Country,App_ID_Mobile,App_Version_Mobile,Browser,Browser_Language,Browser_Version,City,ClickTale_Session_URL,Country_1,Country_Code varchar(10),Decibel_Insight_Session_URL,Device,Device_ID_Mobile,Device_Marketing_Name,Device_Model,Device_Model_Mobile,Device_Vendor,Digital_Program,Form_ID,IP_Address,Is_Mobile_Device,Is_Mobile_Phone,Is_Tablet,Language_Locale,Operating_System,Origin_Type,OS_Type_Mobile,OS_Version,OS_Version_Mobile,Region,Response_ID,Screen_Capture_URL,Screen_Resolution,SDK_Version_Mobile,Trigger_Type,URL,Usable_Screen_Resolution,UUID,Transaction_Types,Visit_Reason,Visit_Reason_Comment,Improvement_Comment,Complete_Purpose_Comment,Recent_Interaction_Impact_Reason_Comment,Impact_Recent_Interaction,Complete_Purpose,Likelihood_Recommend,bPulse_Always_NPS_Segment,bPulse_Behavioral_Intercept_NPS_Segment,bPulse_General_Intercept_NPS_Segment,Digital_Form_NPS_Segment,Digital_Inline_Overall_LTR,LTR_Comment,Always_On_Complete_Purpose,Always_On_Complete_Purpose_Comment,Always_On_Improvement_Comment,Always_On_LTR,Always_On_LTR_Comment,Always_On_Recent_Interaction_Impact,Always_On_Recent_Interaction_Impact_Comment,Always_On_Visit_Reason,Always_On_Visit_Reason_Other_Comment,Behavioral_Intercept_Ease_Task,Behavioral_Intercept_Ease_Task_Comment,Behavioral_Intercept_Improvement_Comment,Behavioral_Intercept_LTR,Behavioral_Intercept_LTR_Comment,Behavioral_Intercept_Recent_Interaction_Impact,Behavioral_Intercept_Recent_Interaction_Impact_Comment,General_Intercept_Complete_Purpose,General_Intercept_Complete_Purpose_Comment,General_Intercept_Improvement_Comment,General_Intercept_LTR,General_Intercept_LTR_Comment,General_Intercept_Recent_Interaction_Impact,General_Intercept_Recent_Interaction_Impact_Comment,General_Intercept_Visit_Reason,General_Intercept_Visit_Reason_Other_Comment,Digital_Inline_Segment,Innovation_Thumbs_Up_Down,Innovation_Comment,Survey_id,Client_Type,General_Intercept_Research_Interest,General_Intercept_Financial_Institution,General_Intercept_Financial_Institution_List";
        CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
        csvTransformer.transformCsv();
        CsvToSqlServerBPulse_digital_inline_export digital_inline_export = new CsvToSqlServerBPulse_digital_inline_export();
        digital_inline_export.convertCsvToSqlServer();
    }

    public void Load_bPulse_Invitation_Export(String inputFilePath, String outputFilePath) {
        String headerFileout = "Surveyid,Responsedate,Survey_Status,Invitation_date,First_name,Last_name,Salutation,Customer_Type,Title,Email,Business_Phone,Mobile_Phone,Customer_Preferred_Language,Relationship_Unique_ID,Reference_ID,Intended_Survey_Send_Date,Interaction_Date_Timestamp,BSC_Agent_ID,BSC_Agent_Name,Client_ID,Client_Name,Email_Client_ID_Concatenate,Survey_Type,BSC_Type,Transit_ID,Transit_Name,Coverage_Team_ID,Coverage_Team_Name,Transaction_Employee_1,Transaction_Employee_2,Transaction_Employee_3,Transaction_Employee_4,Transaction_Employee_5,Activity_ID,Activity_Description,Status_ID,Status_Description,Product_ID,Product_Description,Product_Code,Product_Feature_ID,Product_Feature_Description,B2B_Channel_Code,B2B_Channel_Description,Country,Country_Description,Category,Category_Code_Description,Contact_Method,Contact_Method_Description,Account_Type";
        CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
        csvTransformer.transformCsv();
        CsvToSqlServerBPulse_Invitation_Export Invitation_Export = new CsvToSqlServerBPulse_Invitation_Export();
        Invitation_Export.convertCsvToSqlServer();
    }

    public void Load_scotiabank_b2b_callback(String inputFilePath, String outputFilePath) {
        String headerFileout = "Surveyid,Response_Date_EST,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened,All_log_notes_combined_if_any,Current_status_of_alert,Account_Type,Activity_Description,Activity_ID,B2B_Channel_Code,B2B_Channel_Description,BSC_Agent_ID,BSC_Agent_Name,BSC_Type,Business_Phone,Callback_Role,Category,Category_Code_Description,Client_ID,Client_Name,Closure_Status,Contact_Method,Contact_Method_Description,Country,Country_Description,Coverage_Team_ID,Coverage_Team_Name,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Issue_Pending,Customer_Issue_Raised,Customer_Preferred_Language,Customer_Suggestions_Follow_up_Actions_Additional_Comments,Customer_Type,Email,Email_Client_ID_Concatenate,First_name,Intended_Survey_Send_Date,Interaction_Date   date,Last_name,Mobile_Phone,Number_of_callbacks_attempted,Outcome,Outcome_Note,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Relationship_Unique_ID,Responsedate,Select_feedback_for_Huddles,Status_Description,Status_ID,Survey_Type,Team_Member,Team_Owner_CCC,Team_Owner_Digital,Title,Transaction_Employee_1,Transaction_Employee_2,Transaction_Employee_3,Transaction_Employee_4,Transaction_Employee_5,Transit_ID,Transit_Name,BSC_CSS_Team_ID,Callback_by,Callback_Form_Updated,Callback_Updated_EST,Competitors,Customer_reached_via_callback,LTR_Comment,Other_Financial_Institutions_Client_Works_With,Rapid_Response_Sent,Recent_Interaction_Impact,Relationship_Manager,Relationship_Team_Name,Agent_OSAT,Banco_BBVA_NPS,Banco_Davivienda_NPS,Banco_de_Bogot";
        CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
        csvTransformer.transformCsv();
        CsvToSqlServerBPulsescotiabank_b2b_callback callback = new CsvToSqlServerBPulsescotiabank_b2b_callback();
        callback.convertCsvToSqlServer();
    }
}

