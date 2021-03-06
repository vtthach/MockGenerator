package com.example.multiplechoice;

import com.example.TemplateUtils;

import java.util.Arrays;
import java.util.List;

import static com.example.CommonData.EXT_JSON;
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

public class MultipleChoiceMockGenerate {

    public static final String[] STATUS_CODE = new String[]{"4", "-1", "C0006", "C0007", "99", "C0002", "500", "400"};
    public static final List<String> GENERIC_EXCEPTION = Arrays.asList("500", "400");
    public static final String DHA = "D0001";
    public static final String SAFPS = "S0003";
    public static final String HANIS = "H0008";
    public static final List<String> IDV_CALL_TIME = Arrays.asList("First"/*, "Second"*/);
    public static final List<String> MOCK_ID_NUMBER = Arrays.asList("7309191485190", "7309175823069",
            "7308025029000", "7304155252157",
            "7304025245043", "7303145825106",
            "7302035543159", "7301265123013");

    static TemplateUtils templateSession = new TemplateUtils(FILE_NAME_MOCK_METHOD_CREATE_SESSION + EXT_JSON);
    static TemplateUtils templateCheckIdVStatus = new TemplateUtils(FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + EXT_JSON);
    static TemplateUtils templateMultipleChoice = new TemplateUtils(FILE_NAME_MOCK_METHOD_MULTIPLE_CHOICE_CONFIRM + EXT_JSON);

    public static void main(String... arg) {
        if (STATUS_CODE.length != MOCK_ID_NUMBER.size()) {
            throw new RuntimeException("MultipleChoiceMockGenerate - Invalid mock data!!");
        }
        int idIndex = 0;
        for (String statusCode : STATUS_CODE) {
            String sessionId = getSession(idIndex);
            String idNumber = getMockIdNumber(idIndex);

            // Multiple choice (IdPostVerification)
            templateMultipleChoice.prepare()
                    .replace(KEY_SESSION, sessionId)
                    .replace(KEY_STATUS_CODE, statusCode)
                    .replace(KEY_ID_NUMBER, idNumber)
                    .build(getMultipleChoiceFileName(idNumber, statusCode));

            // Generate session file
            templateSession.prepare()
                    .replace(KEY_SESSION, sessionId)
                    .replace(KEY_ID_NUMBER, idNumber)
                    .build(getSessionFileName(idNumber));

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

    private static String getSession(int idIndex) {
        return SESSION_PREFIX_MULTIPLE_CHOICE + String.format("%1$03d", idIndex);
    }
}
