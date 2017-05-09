package com.example;


public class CommonData {
    // Key
    public static String KEY_STATUS_CODE = "#KEY_STATUS_CODE";
    public static String KEY_SESSION = "#KEY_SESSION";
    public static String KEY_ID_NUMBER = "#KEY_ID_NUMBER";
    public static String KEY_DHA = "#KEY_DHA";
    public static String KEY_SAFPS = "#KEY_SAFPS";
    public static String KEY_HANIS = "#KEY_HANIS";
    public static String KEY_CALL_TIME = "#KEY_CALL_TIME";

    // File name
    public static final String FILE_NAME_MOCK_METHOD_CREATE_SESSION = "OriginationCreateNewSession";
    public static final String FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS = "CheckIdVStatus";
    public static final String FILE_NAME_MOCK_METHOD_MULTIPLE_CHOICE_CONFIRM = "IDNumberPostValidation";
    public static final String FILE_NAME_MOCK_METHOD_BANK_MEM_NEW_ACCOUNT = "BankMemNewAccount";
    public static final String EXT_JSON = ".json";

    // Session prefix
    public static final String SESSION_PREFIX_IDV = "8c33750c-4210-4a9d-b6df-131188ff6";
    public static final String SESSION_PREFIX_MULTIPLE_CHOICE = "13111988-";
    public static class ApiCode{
        public static final String CREATE_ACCOUNT_EXCEPTION_MISSING = "B001";
        public static final String SUCCESS = "0";
    }

    public static class Session {

        public static final int CREATE_E17_ACCOUNT_EXCEPTION_B001 = 1;
        public static final int[] CREATE_GN6_ACCOUNT_EXCEPTION_B001 = {2, 3};
    }
}
