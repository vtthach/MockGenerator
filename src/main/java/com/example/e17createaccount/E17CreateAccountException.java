package com.example.e17createaccount;

import com.example.CommonData;
import com.example.TemplateUtils;

import java.util.Arrays;
import java.util.List;

import static com.example.CommonData.EXT_JSON;
import static com.example.CommonData.FILE_NAME_MOCK_METHOD_BANK_MEM_NEW_ACCOUNT;
import static com.example.CommonData.FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS;
import static com.example.CommonData.FILE_NAME_MOCK_METHOD_CREATE_SESSION;
import static com.example.CommonData.FILE_NAME_MOCK_METHOD_MULTIPLE_CHOICE_CONFIRM;
import static com.example.CommonData.KEY_CALL_TIME;
import static com.example.CommonData.KEY_DHA;
import static com.example.CommonData.KEY_HANIS;
import static com.example.CommonData.KEY_ID_NUMBER;
import static com.example.CommonData.KEY_SAFPS;
import static com.example.CommonData.KEY_SESSION;
import static com.example.CommonData.KEY_STATUS_CODE;
import static com.example.CommonData.SESSION_PREFIX_MULTIPLE_CHOICE;

public class E17CreateAccountException {

    public static final String[] STATUS_CODE = new String[]{CommonData.ApiCode.CREATE_ACCOUNT_EXCEPTION_MISSING};
    public static final String[] SESSION_ID = new String[]{getSession()};

    public static final List<String> GENERIC_EXCEPTION = Arrays.asList("500", "400");
    public static final String DHA = "D0002";
    public static final String SAFPS = "S0002";
    public static final String HANIS = "H0002";
    public static final List<String> IDV_CALL_TIME = Arrays.asList("First"/*, "Second"*/);
    public static final List<String> MOCK_ID_NUMBER = Arrays.asList("7208235300003");

    static TemplateUtils templateSession = new TemplateUtils(FILE_NAME_MOCK_METHOD_CREATE_SESSION + EXT_JSON);
    static TemplateUtils templateCheckIdVStatus = new TemplateUtils(FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + EXT_JSON);
    static TemplateUtils templateMultipleChoice = new TemplateUtils(FILE_NAME_MOCK_METHOD_MULTIPLE_CHOICE_CONFIRM + EXT_JSON);
    static TemplateUtils templateCreateGn6Account = new TemplateUtils(FILE_NAME_MOCK_METHOD_BANK_MEM_NEW_ACCOUNT+ EXT_JSON);

    public static void main(String... arg) {
        if (STATUS_CODE.length != MOCK_ID_NUMBER.size()) {
            throw new RuntimeException("MultipleChoiceMockGenerate - Invalid mock data!!");
        }
        int idIndex = 0;
        for (String statusCode : STATUS_CODE) {
            String sessionId = getSession();
            String idNumber = getMockIdNumber(idIndex);

            // Generate session file
            templateSession.prepare()
                    .replace(KEY_SESSION, sessionId)
                    .replace(KEY_ID_NUMBER, idNumber)
                    .build(getSessionFileName(idNumber));

            // Generate mock data for bank mem create account
            templateCreateGn6Account.prepare()
                    .replace(KEY_STATUS_CODE, statusCode)
                    .build(getCreateGn6FileName(sessionId));

            // Generate check idv status file
            for (String callTime : IDV_CALL_TIME) {
                templateCheckIdVStatus.prepare()
                        .replace(KEY_SESSION, sessionId)
                        .replace(KEY_DHA, DHA)
                        .replace(KEY_SAFPS, SAFPS)
                        .replace(KEY_CALL_TIME, callTime)
                        .replace(KEY_HANIS, HANIS)
                        .build(getCheckIdVStatusFileName(sessionId, callTime));
            }

            // Next item index
            idIndex++;
        }

    }

    private static String getCreateGn6FileName(String sessionId) {
        return FILE_NAME_MOCK_METHOD_BANK_MEM_NEW_ACCOUNT + "/" + FILE_NAME_MOCK_METHOD_BANK_MEM_NEW_ACCOUNT + "_" + sessionId + EXT_JSON;
    }

    private static String getMultipleChoiceFileName(String idNumber, String statusCode) {
        String prefix = idNumber + (GENERIC_EXCEPTION.contains(statusCode) ? String.format("_#%s#", statusCode) : "");
        return FILE_NAME_MOCK_METHOD_MULTIPLE_CHOICE_CONFIRM + "/" + FILE_NAME_MOCK_METHOD_MULTIPLE_CHOICE_CONFIRM + "_" + prefix + EXT_JSON;
    }

    private static String getCheckIdVStatusFileName(String sessionId, String callTime) {
        return FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + "/" + FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + "_" + sessionId +"_" + callTime+ EXT_JSON;
    }

    private static String getMockIdNumber(int idIndex) {
        return MOCK_ID_NUMBER.get(idIndex);
    }

    private static String getSessionFileName(String idNumber) {
        return FILE_NAME_MOCK_METHOD_CREATE_SESSION + "/" + FILE_NAME_MOCK_METHOD_CREATE_SESSION + "_" + idNumber + EXT_JSON;
    }

    private static String getSession() {
        return SESSION_PREFIX_MULTIPLE_CHOICE + String.format("%1$08d", CommonData.Session.CREATE_E17_ACCOUNT_EXCEPTION_B001);
    }
}
