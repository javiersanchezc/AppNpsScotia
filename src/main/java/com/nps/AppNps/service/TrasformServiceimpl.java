package com.nps.AppNps.service;

import com.nps.AppNps.loadProces.CSV;
import com.nps.AppNps.repository.ITrasformfilesRepository;
import org.springframework.stereotype.Service;

@Service
public class TrasformServiceimpl implements ITrasformfilesRepository {

    @Override
    public String getFiles(String fileName) {
        String inputFilePath = "C:/data/" + fileName;

        // Crear el nombre del archivo de salida con el sufijo _modified.csv
        String outputFilePath = getOutputFilePath(fileName);
        try {
            if (fileName.startsWith("scotiabank_wm_callback_to_vm")) {
                String scotiabank_wm_callback_to_vm = "Name,Surveyid,Email,Invitation_date,Responsedate,All_Segments,Number_of_callbacks_attempted,Closure_Status,Customer_Issue_Pending,Customer_Issue_Raised,Customer_Suggestions_Follow,Outcome,Outcome_Note,Customer_Experience_Triggers_Note,Customer_Experience_Triggers,Select_feedback_for_Huddles_,Investment_Specialist,Age_Group,Assests_Under_Administration_Integer,Assets_Under_Administration_Bucket,Customer_Branch_Region_Name,Callback_Role,Client_City,Client_Id,Client_Open_Date,Client_Province,Client_Street_Address,Customer_Branch_District_Id,Customer_Branch_District_Name,Customer_Branch_Id,Customer_Branch,Customer_Branch_Region_Id,Investable_Asset_Tier,Client_Postal_Code,Reference_ID,Risk_Tolerance,Share_of_Wallet_Bucket,Share_of_Wallet_Fraction,Tenure,Development_Cycle,Survey_Type,Country_Description,Country,Customer_Country,Customer_ID,First_Name,Last_Name,Business_Phone,Customer_Preferred_Language,Survey_Method,Investment_Specialist_ID,Survey_Status,Investment_Specialist_Branch,Branch_Banking_Territory,Branch_Banking_Region,Head_ID,Head,Region_ID,Region,Territory_ID,Territory,Invitation_Date_EST varchar(100) NULL,Response_Date_EST,Additional_Feedback,Investment_Advisor_OSAT,Why_WM_Other_Banks,Consolidating_accounts,BanBif_LTR,BanBif,Banco_de_Chile_LTR,Banco_de_Chile,BBVA_LTR,BBVA,BCI_LTR,BCI,BCP_LTR,BCP,BICE_LTR,BICE,BMO_LTR,BMO,Expected_Business,CIBC_LTR,CIBC,Client_Value,Fulfilled_Commitments,Relationship_Drivers_Comment,Ease_of_Contact,Effectiveness,Partner_Engagement,Investment_Specialist_OSAT_Comment,None,Flexible_survey_length_chosen,Intelligo_LTR,Intelligo,Itau_LTR,Itau,Do_you_know_your_Scotiabank_Investment_Banker,Know_My_Business,LTR_Comment,Relationship_LTR,None2,OpsOpsOther_Financial_Institution_Name,Other_Financial_Institution,Other_Financial_Institutions_Comment,Other_LTR,Other_Financial_Institution2,Other,Financial_plan_review,Portfolio_Performance_Investment_Solutions,Is_Scotiabank_your_Primary_Banking_Partner,Timely_Advice,RBC_LTR,RBC,Investment_Specialists_Contact_Frequency,Retirement,Santander_LTR,Santander,Education_savings,Security_LTR,Security,Investment_Specialist_OSAT,TD_LTR,TD,Relationship_Trust,Understanding_Needs,Advice_and_Solutions,Callback_Updated_EST,Callback_by,Accival_Citibanamex,Citibanamex_(Banca_Patrimonial_Privada),BBVA_Banca_Patrimonial_Privada,Banorte_IXE_(Banca_Patrimonial_Privada),HSBC_Banca_Patrimonial_Privada,Inbursa_(Banca_Patrimonial_Privada),Santander_(Banca_Patrimonial_Privada),UBS,Bank_Of_America,JP_Morgan,Credit_Suisse,Goldman_Sachs,Accival_Citibanamex_LTR,Citibanamex_Banca_Patrimonial_Privada,BBVA_Banca_Patrimonial_Privada_LTR,Banorte_IXE_Banca_Patrimonial_Privada,HSBC_Banca_Patrimonial_Privada_LTR,Inbursa_Banca_Patrimonial_Privada,Santander_Banca_Patrimonial_Privada,UBS_LTR,Bank_Of_America_LTR,JP_Morgan_LTR,Credit_Suisse_LTR,Goldman_Sachs_LTR,MEXICO_Secondary_Is_Scotiabank_your_Primary_Banking_Partner";
                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, scotiabank_wm_callback_to_vm);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_wm_response_to_vm")) {
                String scotiabank_wm_response_to_vm = "Name,SurveyID,AgeGroup,AllSegments,AssetsUnderAdministrationInteger,AssetsUnderAdministrationBucket,BusinessPhone,ClientCity,ClientId,ClientStreetAddress,ClientOpenDate,ClientPostalCode,ClientProvince,Country,CountryDescription,CustomerCountry,CustomerID,Email,InvestableAssetTier,InvestmentSpecialist,InvitationDate,ReferenceID,RiskTolerance,ShareOfWalletBucket,ShareOfWalletFraction,Tenure,InvitationDateEST,CustomerBranch,CustomerBranchDistrictId,CustomerBranchDistrictName,CustomerBranchId,CustomerBranchRegionId,CustomerBranchRegionName,CustomerPreferredLanguage,DevelopmentCycle,FirstName,LastName,SurveyMethod,SurveyType,Head,HeadID,InvestmentSpecialistID,Region,RegionID,Territory,TerritoryID,ResponseDate,BranchBankingRegion,Branch,BranchBankingTerritory,InvestmentSpecialistBranch,ResponseDateEST,SurveyStatus,KnowYourScotiabankInvestmentBanker,InvestmentAdvisorOSAT,LTRComment,RelationshipLTR,AdviceAndSolutions,ClientValue,EaseOfContact,FlexibleSurveyLengthChosen,FulfilledCommitments,nvestmentSpecialistOSAT,InvestmentSpecialistOSATComment,InvestmentSpecialistsContactFrequency,IsScotiabankYourPrimaryBankingPartner,KnowMyBusiness,PartnerEngagement,PortfolioPerformanceInvestmentSolutions,UnderstandingNeeds,BanBif,BBVA,BCP,BMO,CIBC,Intelligo,OtherFinancialInstitution,RBC,TD,BanBifLTR,BBVALTR,BCPLTR,BMOLTR,CIBCLTR,ConsolidatingAccounts,EducationSavings,FinancialPlanReview,IntelligoLTR,None,Other,OtherFinancialInstitution2,OpsOpsOtherFinancialInstitutionName,OtherFinancialInstitutionsComment,OtherLTR,RBC_LTR,RelationshipDriversComment,Retirement,TD_LTR,AdditionalFeedback,BancoDeChile,BancoDeChileLTR,BCI,BCILTR,BICE,BICELTR,Effectiveness,ExpectedBusiness,Itau,ItauLTR,RelationshipTrust,Santander,SantanderLTR,Security,SecurityLTR,TimelyAdvice,Why_WM_OtherBanks,AccivalCitibanamex,CitibanamexBancaPatrimonialPrivada,BBVABancaPatrimonialPrivada,BanorteIXEBancaPatrimonialPrivada,HSBCBancaPatrimonialPrivada,InbursaBancaPatrimonialPrivada,SantanderBancaPatrimonialPrivada,UBS,BankOfAmerica,JPMorgan,CreditSuisse,GoldmanSachs,AccivalCitibanamexLTR,CitibanamexBancaPatrimonialPrivadaLTR,BBVABancaPatrimonialPrivadaLTR,BanorteIXEBancaPatrimonialPrivadaLTR,HSBCBancaPatrimonialPrivadaLTR,InbursaBancaPatrimonialPrivadaLTR,SantanderBancaPatrimonialPrivadaLTR,UBSLTR,BankOfAmericaLTR,JPMorganLTR,CreditSuisseLTR,GoldmanSachsLTR,MexicoSecondaryIsScotiabankYourPrimaryBankingPartner";
                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, scotiabank_wm_response_to_vm);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_wm_invitations_to_vm")) {
                String scotiabank_wm_invitations_to_vm = "Surveyid,Name,Client_Id,Client_Open_Date,Email,Investment_Specialist,Invitation_date,Reference_ID,Invitation_Date_EST,All_Segments,Assests_Under_Administration_Integer,Assets_Under_Administration_Bucket,Business_Phone,Client_City,Client_Street_Address,Client_Postal_Code,Client_Province,Country,Country_Description,Customer_Country,Investable_Asset_Tier,Risk_Tolerance,Share_of_Wallet_Bucket,Share_of_Wallet_Fraction,Age_Group,Customer_Branch,Customer_Branch_District_Id,Customer_Branch_District_Name,Customer_Branch_Id,Customer_Branch_Region_Id,Customer_Branch_Region_Name,Customer_ID,Customer_Preferred_Language,Development_Cycle,First_Name,Last_Name,Survey_Method,Survey_Type,Tenure,Branch_Banking_Region,Branch_Banking_Territory,Head,Head_ID,Investment_Specialist_Branch,Investment_Specialist_ID,Region,Region_ID,Survey_Status,Territory,Territory_ID";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, scotiabank_wm_invitations_to_vm);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_b2b_callback_to_vm")) {
                String headerFileout = "Surveyid,Response_Date_EST,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened,All_log_notes_combined_if_any,Current_status_of_alert,Account_Type,Activity_Description,Activity_ID,B2B_Channel_Code,B2B_Channel_Description,BSC_Agent_ID,BSC_Agent_Name,BSC_Type,Business_Phone,Callback_Role,Category,Category_Code_Description,Client_ID,Client_Name,Closure_Status,Contact_Method,Contact_Method_Description,Country,Country_Description,Coverage_Team_ID,Coverage_Team_Name,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Issue_Pending,Customer_Issue_Raised,Customer_Preferred_Language,Customer_Suggestions_Follow_up_Actions_Additional_Comments,Customer_Type,Email,Email_Client_ID_Concatenate,First_name,Intended_Survey_Send_Date,Interaction_Date   date,Last_name,Mobile_Phone,Number_of_callbacks_attempted,Outcome,Outcome_Note,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Relationship_Unique_ID,Responsedate,Select_feedback_for_Huddles,Status_Description,Status_ID,Survey_Type,Team_Member,Team_Owner_CCC,Team_Owner_Digital,Title,Transaction_Employee_1,Transaction_Employee_2,Transaction_Employee_3,Transaction_Employee_4,Transaction_Employee_5,Transit_ID,Transit_Name,BSC_CSS_Team_ID,Callback_by,Callback_Form_Updated,Callback_Updated_EST,Competitors,Customer_reached_via_callback,LTR_Comment,Other_Financial_Institutions_Client_Works_With,Rapid_Response_Sent,Recent_Interaction_Impact,Relationship_Manager,Relationship_Team_Name,Agent_OSAT,Banco_BBVA_NPS,Banco_Davivienda_NPS,Banco_de_Bogot";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_b2b_digital_inline_to_vm")) {
                String headerFileout = "Digital_Form,Digital_Form_Name,First_name,Last_name,Property_name,Property_identifier,Email,Gender,Activity_Description,Activity_ID,Agent_ID,Business_Phone,Category,Channel_Code,Contact_Method,Country,Customer_ID,Survey_viewed_mobile_device,All_Segments,Home_Phone,Interaction_Date,Mobile_Phone,Customer_Preferred_Language,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Salutation,Status_Description,Status_ID,Survey_Method,Channel,Business_ID,Transit_ID,Transit_Name,Response_Date_EST datetime,Invitation_Date_EST datetime,Contact_Status,Closure_Status,Outcome,Outcome_Note,Resolution,Resolution_Note,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Reached,Customer_Issue_Escalated,Customer_Issue_Pending,Customer_Suggestions,Coaching_Comments,Additional_Comments,Followup_Actions,Current_status_alert,All_log_notes_combined,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened int,Interaction_Date_Timestamp,Number_callbacks_attempted,Team_Member,Team_Owner_CCC,Team_Owner_Digital,Callback_Form_Updated,Rapid_Response_Sent,Site_Name,Team_Name,Customer_Issue_Raised,Customer_Suggestions_Followup_Actions_Additional_Comments,Branch_Roles,Customer_Country,App_ID_Mobile,App_Version_Mobile,Browser,Browser_Language,Browser_Version,City,ClickTale_Session_URL,Country_1,Country_Code varchar(10),Decibel_Insight_Session_URL,Device,Device_ID_Mobile,Device_Marketing_Name,Device_Model,Device_Model_Mobile,Device_Vendor,Digital_Program,Form_ID,IP_Address,Is_Mobile_Device,Is_Mobile_Phone,Is_Tablet,Language_Locale,Operating_System,Origin_Type,OS_Type_Mobile,OS_Version,OS_Version_Mobile,Region,Response_ID,Screen_Capture_URL,Screen_Resolution,SDK_Version_Mobile,Trigger_Type,URL,Usable_Screen_Resolution,UUID,Transaction_Types,Visit_Reason,Visit_Reason_Comment,Improvement_Comment,Complete_Purpose_Comment,Recent_Interaction_Impact_Reason_Comment,Impact_Recent_Interaction,Complete_Purpose,Likelihood_Recommend,bPulse_Always_NPS_Segment,bPulse_Behavioral_Intercept_NPS_Segment,bPulse_General_Intercept_NPS_Segment,Digital_Form_NPS_Segment,Digital_Inline_Overall_LTR,LTR_Comment,Always_On_Complete_Purpose,Always_On_Complete_Purpose_Comment,Always_On_Improvement_Comment,Always_On_LTR,Always_On_LTR_Comment,Always_On_Recent_Interaction_Impact,Always_On_Recent_Interaction_Impact_Comment,Always_On_Visit_Reason,Always_On_Visit_Reason_Other_Comment,Behavioral_Intercept_Ease_Task,Behavioral_Intercept_Ease_Task_Comment,Behavioral_Intercept_Improvement_Comment,Behavioral_Intercept_LTR,Behavioral_Intercept_LTR_Comment,Behavioral_Intercept_Recent_Interaction_Impact,Behavioral_Intercept_Recent_Interaction_Impact_Comment,General_Intercept_Complete_Purpose,General_Intercept_Complete_Purpose_Comment,General_Intercept_Improvement_Comment,General_Intercept_LTR,General_Intercept_LTR_Comment,General_Intercept_Recent_Interaction_Impact,General_Intercept_Recent_Interaction_Impact_Comment,General_Intercept_Visit_Reason,General_Intercept_Visit_Reason_Other_Comment,Digital_Inline_Segment,Innovation_Thumbs_Up_Down,Innovation_Comment,Survey_id,Client_Type,General_Intercept_Research_Interest,General_Intercept_Financial_Institution,General_Intercept_Financial_Institution_List";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_b2b_responses_to_vm")) {
                String headerFileout = "Surveyid,Response_Date_EST,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened,Current_status_of_alert,All_log_notes_combined_if_any,Email,First_name,Last_name,BSC_Agent_ID,BSC_Agent_Name,BSC_Type,B2B_Channel_Description,Client_ID,Client_Name,Title,Customer_Type,Coverage_Team_ID,Coverage_Team_Name,B2B_Channel_Code,Email_Client_ID_Concatenate,Intended_Survey_Send_Date,Reference_ID,Relationship_Unique_ID,Callback_Role,Category_Code_Description,Closure_Status,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Issue_Pending,Customer_Issue_Raised,Customer_Suggestions_Follow_up_Actions_Additional_Comments,Number_of_callbacks_attempted,Outcome,Outcome_Note,Team_Owner_CCC,Team_Owner_Digital,Transaction_Employee_1,Transaction_Employee_2,Transaction_Employee_3,Transaction_Employee_4,Transaction_Employee_5,Contact_Method_Description,Team_Member,Survey_Type,Account_Type,Activity_Description,Activity_ID,Status_Description,Status_ID,Category,Contact_Method,Business_Phone,Country,Country_Description,Customer_Preferred_Language,Interaction_Date,Mobile_Phone,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Product_Code,Transit_ID,Transit_Name,Translations_Coverage_Bsc_CSS_Team_Comment,Translations_Coverage_Bsc_CSS_Team_Reason,Translations_LTR_Comment,Translations_Relationship_Drivers_Comment,BSC_CSS_Team_ID,BSC_CSS_Team_Name,Relationship_Manager,Translations_Relationship_Comment,Translations_Other_Financial_Institutions_Comment,Translations_Other_Comments,Relationship_Team_ID,Callback_Form_Up,Rapid_Response_Sent,Relationship_Team_Name,Agent_OSAT,Comment,Banco_BBVA,Banco_BBVA_NPS,Banco_Davivienda,Banco_Davivienda_NPS,Banco_de_Bogot";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_b2b_invitations_to_vm")) {
                String headerFileout = "Surveyid,Responsedate,Survey_Status,Invitation_date,First_name,Last_name,Salutation,Customer_Type,Title,Email,Business_Phone,Mobile_Phone,Customer_Preferred_Language,Relationship_Unique_ID,Reference_ID,Intended_Survey_Send_Date,Interaction_Date_Timestamp,BSC_Agent_ID,BSC_Agent_Name,Client_ID,Client_Name,Email_Client_ID_Concatenate,Survey_Type,BSC_Type,Transit_ID,Transit_Name,Coverage_Team_ID,Coverage_Team_Name,Transaction_Employee_1,Transaction_Employee_2,Transaction_Employee_3,Transaction_Employee_4,Transaction_Employee_5,Activity_ID,Activity_Description,Status_ID,Status_Description,Product_ID,Product_Description,Product_Code,Product_Feature_ID,Product_Feature_Description,B2B_Channel_Code,B2B_Channel_Description,Country,Country_Description,Category,Category_Code_Description,Contact_Method,Contact_Method_Description,Account_Type";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_cpulse_response_to_vm")) {
                String headerFileout = "Alert_closed_by,Alert_close_time,Alert_description,Alert_reopened,Current_status_of_alert,All_log_notes_combined_if_any,Survey_viewed_on_mobile_device_on_any_page,Surveyid,Property_identifier,Property_name,Email,First_name,Last_name,All_Segments,cNPS_Survey_Program,Fraud_Activity_Description,Fraud_Activity_ID,Fraud_Type,Insurance_Activity_Description,Insurance_Activity_ID,Insurance_Claim_Status,Insurance_Sales_Team,Insurance_Type,Insurance_Post_Sale_and_Claim,Follow_up_Actions,Advisor_Id,Advisor_Name,Interaction_Date_Timestamp,Survey_Type,Account_Type,Activity_Description,Activity_ID,Status_Description,Status_ID,Agent_ID,Employee,Category,Contact_Method,Country,Customer_Platform,Segment,Customer_Country,Customer_Integration,Customer_ID,Gender,Insurance_Flag,Channel_Code,Business_Phone,Home_Phone,Mobile_Phone,Customer_Preferred_Language,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Product_Code,Reference_ID,Salutation,Survey_Method,Channel,Transit_ID,Transit_Name,Age_Group,What_could_we_do_to_serve_you_better,Translations_What_could_we_do_to_serve_you_better,Retail_Everyday_Banking_Journey_Step,Retail_Help_Advice_Journey_Step,Retail_Journey,Retail_New_Product_Sign_up_Journey_Step,Retail_Product_Renewal_Journey_Step,District_ID,District_Name,Impact_of_Recent_Interaction,Why,Translations_Why,Invitation_Date_Peru_Time,Rapid_Response_Sent,Region_ID,Region_Name,Site_ID,Site_Name,Team_ID,Team_Name,Invitation_Date_EST,Response_Date_EST,Response_Date_Peru_Time,Likelihood_to_Recommend,Interaction_Date,Other_Bank_s_LTR,Advice_Given_Verbatim,How_did_you_receive_this_advice,Other_Advice_Given,Were_you_provided_advice,Agent_OSAT,During_your_recent_interaction_with_the_Contact_Centre_were_your_needs_resolved,Times_Interacted,Insurance_Level_of_effort_to_get_an_answer,Effort,Claim_report,Permission_to_Share,Permission_to_Survey_Reason,Permission_to_Survey,Clarity_in_the_final_answer,Information_received,Process_deadlines,Simplicity_of_the_process,Insurance_Provide_clear_answers,Insurance_Keep_customer_informed,Assistance_received_throughout_the_process,Insurance_Inform_expected_response_times,Empathy,Insurances_offer_Tailor_to_customer_needs,Product_Reason_For_Rating,Contact_Frequency,Other_Comments,Easy_to_understand_advice,Expected_Business,Do_you_know_your_Financial_Advisor,Advisor_Comment,Knowledge,Level_of_Confidence,Good_Listener,Preparation,Empathic_Interaction,Valued_customer,Confidence_Level_Comment,Contact_Frequency2,Ease_of_Contact,Tailored_Advice,Fulfilled_Commitments,Relationship_Drivers_Comment,Flexible_Survey_Length_Chosen,OSAT_Comment,Advisor_OSAT,BanBif,BanBif_LTR,Banco_de_Bogotá,Bancolombia,Banco_Itaú,Banco_Popular,Banco_Popular_LTR,Banco_BHD_Leon,Banco_BHD_Leon_LTR,BBVA,BBVA_LTR,BCP,BCP_LTR,Banco_Promerica,Banco_Promerica_LTR,Banco_de_Reservas,Banco_de_Reservas_LTR,Banco_de_Santa_Cruz,Banco_de_Santa_Cruz_LTR,Banco_de_Chile,Banco_de_Chile_LTR,BCI,BCI_LTR,BICE,BICE_LTR,Itaú,Itaú_LTR,Santander,Santander_LTR,Security,Security_LTR,CIBC,CIBC_LTR,Banco_de_Bogotá_LTR,Bancolombia_LTR,Banco_Itaú_LTR,Davivienda_LTR,Davivienda,First_Citizens,First_Citizens_LTR,Interbank,Interbank_LTR,JMMB,JMMB_LTR,JN,JN_LTR,Other_Financial_Institutions_Comment,NCB,NCB_LTR,Other_Bank,Others,RBC,RBC_LTR,Republic_Bank,Republic_Bank_LTR,Sagicor,Sagicor_LTR,Is_Scotiabank_your_Primary_Bank,Attention_Flag,Attention_Label_Churn_Intent_Flag,Attention_Label_Eroding_Faith_Flag,Attention_Label_Legal_Action_Flag,Attention_Label_positive_opportunity_Flag,Attention_type,Customer_effort_bucket_name,Customer_Effort_Score,Negative_Recognition_Flag,Neutral_Recognition_Flag,Positive_Recognition_Flag,Recognition_Flag,Recognition_sentiment_types,Combined_Topics_and_Sentiments_Tagged_Original,Topics_Tagged_Original,Provider,Retail_Premium_Beyond_Banker_Premium_Relationship_Officer,Quarantine_Rule_to_Exclude_Mortgage,Transaction_Types,Customer_Effort_Bucket,Persona,Customer_reached_via_callback,Customer_Assignment,First_name2,Advice,Advisor_Fulfillment,Ease_of_Process,Journey,Product_Satisfaction,Translations_Product_Reason_For_Rating,Callback_Updated_EST,Sales_Advisor_Satisfaction,Flex,Information,Membership,Sales_Channel_Satisfaction,Product_Acquisition_reason,Product_Satisfaction2,Delivery_Response,Service_Time_Response,Agent_OSAT_Comment,Why_Mortgage_Program,Effective_communication,Scotiabank_provides_advice_to_help_me,Scotiabank_s_Consistent_Services,Relevant_Communication_and_Updates_during_the_COVID_19_crisis,I_Trust_Scotiabank_During_These_Difficult_Times,Effectiveness,What_else_can_we_do_to_improve_your_experience,What_else_can_we_do_to_improve_your_experience_2,What_else_can_we_do_to_improve_your_experience_3,Credit_Card_Benefits,Clarity,Delivery_Satisfaction,Response_times,Simplicity_of_the_process2,Credit_card_Terms_and_conditions,Others_please_specify,Other_please_specify,Competitive_Benefits,Easy_to_use,Establishments_Satisfaction,Flex_Question,Handling_Fee,Effort_Level,Credit_Card_Other_Financial_Institutions,Primary_Card,Redemption_Consultation,Not_Primary_Card_Reasons,Ease_of_contact2,Virtual_Satisfaction,Why_Credit_Card,Flexible_Survey_Length_Chosen2,Why_2,Living_Lens_Feedback_type,Audio_Feedback_for_Why_Comment,Video_Feedback_for_Why_Comment,Why_HVC_Affluent_Primary_Bank,Banco_Azteca,Banco_Azteca_LTR,Banorte,Banorte_LTR,BROU,BROU_LTR,Banco_Estado,Banco_Estado_LTR,Banco_Internacional,Banco_Internacional_LTR,Citibanamex,Citibanamex_LTR,HSBC,HSBC_LTR,Inbursa,Inbursa_LTR,Mi_Banco,Mi_Banco_LTR,Official_responsiveness,Understanding_your_business_needs";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("scotiabank_cpulse_invitations_to_vm")) {
                String headerFileout = "First_name,Last_name,Name,Salutation,Property_name,Property_identifier,Email,Gender,Account_Type,Activity_Description,Activity_ID,Agent_ID,Employee,Business_Phone,Category,Category_Code_Description,Channel_Code,Contact_Method,Country,Customer_ID,Customer_Platform,Survey_viewed_mobile_device,Segment,All_Segments,Home_Phone,Interaction_Date,Mobile_Phone,Customer_Preferred_Language,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Status_Description,Status_ID,Survey_Method,Contact_Method_Description,Channel,Channel_Description,Transit_ID,Transit_Name,Agent_Team,Brand,Survey_Type,Response_Date_EST,Invitation_Date_EST,Survey_completed_after_reminder,Survey_Status,Survey_opted_out,Region_Name,District_Name,Region_ID,District_ID,Site_Name,Site_ID,Team_Name,Team_ID,Customer_Country,Customer_Integration,Fraud_Type,Fraud_Activity_ID,Fraud_Activity_Description,Advisor_Name,Advisor_Id,cNPS_Survey_Program,Surveyid,Insurance_Activity_Description,Insurance_Activity_ID,Insurance_Claim_Status,Insurance_Flag,Insurance_PostSale_Claim,Insurance_Sales_Team,Insurance_Type,Age_Group,Retail_Journey,Retail_New_Product_SignUp_Journey_Step,Retail_Everyday_Banking_Journey_Step,Retail_Help_Advice_Journey_Step,Retail_Product_Renewal_Journey_Step,Samplingresultexclusioncausedetail,SurveySpec,RawValueAllSegments,AccountOpenDate,CustomerTenure,MortgageActivityDescription,MortgageActivityID,RetailPremium-BeyondBanker/PremiumRelationshipOfficer,Processingfeedfilename,QuarantineRuletoExcludeMortgage,Surveystatus,CustomerTenureGroup,Journey";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("Load_cPulse_Callback_Export")) {
                String headerFileout = "pendiente Load_cPulse_Callback_Export";
                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("sb_insurance_cardif_callback_to_vm")) {
                String headerFileout = "Property_name,Property_identifier,Activity_Description,Activity_ID,Agent_ID,Category,Channel_Code,Contact_Method,Country,Customer_ID,Survey_viewed_on_mobile_device_on_any_page,All_Segments,Interaction_Date,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Status_Description,Status_ID,Survey_Method,Transit_ID,Channel,Transit_Name,Response_Date_EST,Invitation_Date_EST,Likelihood_to_Recommend,Impact_of_Recent_Interaction,Why,Translations_Why,What_could_we_do_to_serve_you_better,Translations_What_could_we_do_to_serve_you_better,Team_Member,Team_Owner_CCC,Site_Name,Team_Name,Customer_Country,Insurances_offer_Tailor_to_customer_needs,Insurance_Keep_customer_informed,Insurance_Provide_clear_answers,Insurance_Inform_expected_response_times,Product_Reason_For_Rating,Translations_Product_Reason_For_Rating,Insurance_Level_of_effort_to_get_an_answer,Simplicity_of_the_process,Information_received,Process_deadlines,Assistance_received_throughout_the_process,Clarity_in_the_final_answer,Empathy,Effort,Claim_report,First_name,Last_name,Email,Gender,Account_Type,Employee,Business_Phone,Customer_ID2,Customer_Platform,Segment,Home_Phone,Mobile_Phone,Customer_Preferred_Language,Salutation,Closure_Status,Outcome,Outcome_Note,Resolution,Resolution_Note,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Reached,Customer_Issue_Escalated,Customer_Issue_Pending,Customer_Suggestions,Coaching_Comments,Additional_Comments,Follow_up_Actions,Current_status_of_alert,All_log_notes_combined_if_any,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened,Interaction_Date_Timestamp,Number_of_callbacks_attempted,Team_Owner_Digital,Callback_Form_Updated,Rapid_Response_Sent,Customer_Issue_Raised,Customer_Suggestions_Follow_up_Actions_Additional_Comments,Branch_Roles,Select_feedback_for_Huddles,Customer_Integration,Customer_At_Risk_Flag,Does_this_customer_seem_at_risk_to_leave_the_Bank,Callback_Updated_EST,Customer_reached_via_callback,Insurance_Flag,Callback_by,Surveyid,First_name2,Customer_Assignment,Insurance_Post_Sale_and_Claim,Insurance_Claim_Status";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("sb_insurance_cardif_Invitations_to_vm")) {
                String headerFileout = "First_name,Last_name,Property_name,Property_identifier,Gender,Account_Type,Activity_Description,Activity_ID,Agent_ID,Employee,Category,Channel_Code,Contact_Method,Country,Customer_ID,Customer_Platform,Survey_viewed_on_mobile_device_on_any_page,Segment,Interaction_Date,Customer_Preferred_Language,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,cNPS_Survey_Program,Salutation,Status_Description,Status_ID,Survey_Method,Channel,Transit_ID,Transit_Name,Invitation_Date_EST,Contact_Status,Outcome,Outcome_Note,Current_status_of_alert,Name,Survey_completed_after_reminder,Survey_status,Survey_Status2,Surveyid,Survey_opted_out,Customer_Integration";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("sb_insurance_cardif_responses_to_vm")) {
                String headerFileout = "Property_name,Property_identifier,Activity_Description,Activity_ID,Agent_ID,Category,Channel_Code,Contact_Method,Country,Customer_ID,Survey_viewed_on_mobile_device_on_any_page,All_Segments,Interaction_Date,Product_Code,Product_Description,Product_Feature_Description,Product_Feature_ID,Product_ID,Reference_ID,Status_Description,Status_ID,Survey_Method,Transit_ID,Channel,Transit_Name,Response_Date_EST,Invitation_Date_EST,Likelihood_to_Recommend,Impact_of_Recent_Interaction,Why,Translations_Why nvarchar,What_could_we_do_to_serve_you_better,Translations_What_could_we_do_to_serve_you_better,Team_Member,Team_Owner_CCC,Site_Name,Team_Name,Customer_Country,Insurances_offer_Tailor_to_customer_needs,Insurance_Keep_customer_informed,Insurance_Provide_clear_answers,Insurance_Inform_expected_response_times,Product_Reason_For_Rating,Translations_Product_Reason_For_Rating,Insurance_Level_of_effort_to_get_an_answer,Simplicity_of_the_process,Information_received,Process_deadlines,Assistance_received_throughout_the_process,Clarity_in_the_final_answer,Empathy,Effort,Claim_report,First_name,Last_name,Email,Gender,Account_Type,Employee,Business_Phone,Customer_ID2,Customer_Platform,Segment,Home_Phone,Mobile_Phone,Customer_Preferred_Language,Salutation,Closure_Status,Outcome,Outcome_Note,Resolution,Resolution_Note,Customer_Experience_Triggers,Customer_Experience_Triggers_Note,Customer_Reached,Customer_Issue_Escalated,Customer_Issue_Pending,Customer_Suggestions,Coaching_Comments,Additional_Comments,Follow_up_Actions,Current_status_of_alert,All_log_notes_combined_if_any,Alert_close_time,Alert_closed_by,Alert_description,Alert_reopened,Interaction_Date_Timestamp,Number_of_callbacks_attempted,Team_Owner_Digital,Callback_Form_Updated,Rapid_Response_Sent,Customer_Issue_Raised,Customer_Suggestions_Follow_up_Actions_Additional_Comments,Branch_Roles,Select_feedback_for_Huddles,Customer_Integration,Customer_At_Risk_Flag,Does_this_customer_seem_at_risk_to_leave_the_Bank,Callback_Updated_EST,Customer_reached_via_callback,Insurance_Flag,Callback_by,Surveyid,First_name2,Customer_Assignment,Insurance_Post_Sale_and_Claim,Insurance_Claim_Status,Age_Group";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
                System.out.println("Processed file: " + fileName);
            } else if (fileName.startsWith("sb_insurance_cardif_optout_to_vm")) {
                String headerFileout = "Invitation_Date_EST,Country,First_name,Last_name,Email,Channel_Code,Branch,Agent,Digital_Channel,Survey_opted_out,Region_Name,District_Name,Region_ID,District_ID,Site_Name,Site_ID,Team_Name,Team_ID,Customer_Country,Customer_Integration,Fraud_Type,Fraud_Activity_ID,Fraud_Activity_Description,Advisor_Name,Advisor_Id,cNPS_Survey_Program,Survey_Type,Surveyid int NOT NULL,Insurance_Activity_Description,Insurance_Activity_ID,Insurance_Claim_Status,Insurance_Flag,Insurance_Post_Sale_and_Claim,Insurance_Sales_Team,Insurance_Type,Survey_Method";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
            } else if (fileName.startsWith("scotiabank_optout_to_vm")) {
                String headerFileout = "Invitation_Date_EST,Country,First_name,Last_name,Email,Channel_Code,Branch,Agent,Digital_Channel,Survey_opted_out,Region_Name,District_Name,Region_ID,District_ID,Site_Name,Site_ID,Team_Name,Team_ID,Customer_Country,Customer_Integration,Fraud_Type,Fraud_Activity_ID,Fraud_Activity_Description,Advisor_Id,Advisor_Name,cNPS_Survey_Program,Survey_Type,Surveyid,Insurance_Activity_Description,Insurance_Activity_ID,Insurance_Claim_Status,Insurance_Flag,Insurance_Post_Sale_and_Claim,Insurance_Sales_Team,Insurance_Type,Survey_Method,Client_Id,All_Segments,Customer_ID,Customer_Branch,Customer_Branch_Id,Customer_Branch_District_Name,Customer_Branch_District_Id,Customer_Branch_Region_Name,Customer_Branch_Region_Id,Territory,Territory_ID,Region,Region_ID2,Head,Head_ID,Branch_Banking_Region,Branch_Banking_Territory,Investment_Specialist_Branch,Survey_Status,BSC_Agent_ID,BSC_Agent_Name,Client_ID2,Client_Name,BSC_Type,Transit_ID,Transit_Name,Coverage_Team_ID,Coverage_Team_Name,BSC_Agent,CSS_Agent,Relationship_Manager,Tier,Relationship_Team_ID,Relationship_Team_Name";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
            } else if (fileName.startsWith("scotiabank_huddle_callbacks_to_vm")) {
                String headerFileout = "Creation_Date_EST,Channel,Huddle_Type,Country,Property_name,Property_identifier,Huddle_Date,Progress_Recap_from_Last_Session,Positive_Customer_Comments_to_Review_as_a_Team,Constructive_Customer_Comments_to_Review_as_a_Team,Pre-Huddle_Notes,Brainstorm_Solutions,Other_Topics,Huddle_Complete,Surveyid,Alert_Status,Case_Edited_By,Employee_Spotlight,Action_Plan_Measurements_of_Success,Deliver_Better_Customer_Experience,Constructive_Customer_Comments_Root_Causes,Action_Item1,Action_Item2,Customer_Issues_to_be_Raised,Escalated_Issues_Details_(If_applicable),Positive_Employee_Comments_to_Review_as_a_Team,Constructive_Employee_Comments_to_Review_as_a_Team,Constructive_Employee_Comments_Root_Causes,Employee_Issues_to_be_Raised,Action_Plan_Issues,Action_Plan_Actions,Items_to_Raise,Huddle_by_Role,Huddle_Updated,Huddle_by,Huddle_Completed,Survey_Type,Line_of_Business,Line_of_Business_1";

                CSV csvTransformer = new CSV(inputFilePath, outputFilePath, headerFileout);
                csvTransformer.transformCsv();
            } else {
                System.out.println("fileName = " + fileName + " no encontrado");
                throw new IllegalArgumentException("Archivo no encontrado: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error procesando el archivo: " + fileName, e);
        }

        return inputFilePath;
    }
    private String getOutputFilePath(String fileName) {
        // Lista de patrones base que deben ser utilizados para cortar el nombre del archivo
        String[] basePatterns = {
                "scotiabank_wm_callback_to_vm",
                "scotiabank_wm_response_to_vm",
                "scotiabank_wm_invitations_to_vm",
                "scotiabank_b2b_callback_to_vm",
                "scotiabank_b2b_digital_inline_to_vm",
                "scotiabank_b2b_responses_to_vm",
                "scotiabank_b2b_invitations_to_vm",
                "scotiabank_cpulse_response_to_vm",
                "scotiabank_cpulse_invitations_to_vm",
                "Load_cPulse_Callback_Export",
                "sb_insurance_cardif_callback_to_vm",
                "sb_insurance_cardif_Invitations_to_vm",
                "sb_insurance_cardif_responses_to_vm",
                "sb_insurance_cardif_optout_to_vm",
                "scotiabank_optout_to_vm",
                "scotiabank_huddle_callbacks_to_vm"
        };

        // Encontrar el patrón base que coincide con el inicio del nombre del archivo
        for (String pattern : basePatterns) {
            if (fileName.startsWith(pattern)) {
                return "C:/data/" + pattern + "_modified.csv";
            }
        }

        // Si no se encuentra un patrón base, retornar el nombre original con _modified.csv
        return "C:/data/" + fileName + "_modified.csv";
    }
}
