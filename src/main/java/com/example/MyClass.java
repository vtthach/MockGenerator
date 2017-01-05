package com.example;

public class MyClass {
    static String KEY_SESSION = "#KEY_SESSION";
    static String KEY_ID_NUMBER = "#KEY_ID_NUMBER";
    static String KEY_DHA = "#KEY_DHA";
    static String KEY_SAFPS = "#KEY_SAFPS";
    static String KEY_HANIS = "#KEY_HANIS";

    private static final String FILE_NAME_MOCK_METHOD_CREATE_SESSION = "OriginationCreateNewSession";
    private static final String FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS = "CheckIdVStatus";
    private static final String EXT_JSON = ".json";

    static TemplateUtils templateSession = new TemplateUtils(FILE_NAME_MOCK_METHOD_CREATE_SESSION + EXT_JSON);
    static TemplateUtils templateCheckIdVStatus = new TemplateUtils(FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + EXT_JSON);

    public static void main(String... arg) {
        int idIndex = 0;
        for (String dha : Data.DHA) {
            for (String safps : Data.SAFPS) {
                for (String hanis : Data.HANIS) {

                    // Generate session file
                    String sessionId = getSession(idIndex);
                    String idNumber = getMockIdNumber(idIndex);
                    templateSession.prepare()
                            .replace(KEY_SESSION, sessionId)
                            .replace(KEY_ID_NUMBER, idNumber)
                            .build(getSessionFileName(idNumber));

                    // Generate check status file
                    templateCheckIdVStatus.prepare()
                            .replace(KEY_SESSION, sessionId)
                            .replace(KEY_DHA, dha)
                            .replace(KEY_SAFPS, safps)
                            .replace(KEY_HANIS, hanis)
                            .build(getCheckIdVStatusFileName(sessionId));

                    // Next item index
                    idIndex++;
                }
            }
        }
    }

    private static String getCheckIdVStatusFileName(String session) {
        return FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + "/" + FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + "_" + session + EXT_JSON;
    }

    private static String getMockIdNumber(int idIndex) {
        return Data.MockIdNumber.get(idIndex);
    }

    private static String getSessionFileName(String idNumber) {
        return FILE_NAME_MOCK_METHOD_CREATE_SESSION + "/" + FILE_NAME_MOCK_METHOD_CREATE_SESSION + "_" + idNumber + EXT_JSON;
    }

    private static String getSession(int idIndex) {
        return "8c33750c-4210-4a9d-b6df-131188ff6" + String.format("%1$03d", idIndex);
    }
}
