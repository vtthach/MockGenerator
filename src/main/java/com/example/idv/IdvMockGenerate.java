package com.example.idv;

import com.example.CommonData;
import com.example.TemplateUtils;

import java.util.Arrays;
import java.util.List;

import static com.example.CommonData.EXT_JSON;
import static com.example.CommonData.FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS;
import static com.example.CommonData.FILE_NAME_MOCK_METHOD_CREATE_SESSION;
import static com.example.CommonData.KEY_DHA;
import static com.example.CommonData.KEY_HANIS;
import static com.example.CommonData.KEY_ID_NUMBER;
import static com.example.CommonData.KEY_SAFPS;
import static com.example.CommonData.KEY_SESSION;

public class IdvMockGenerate {
    public static final List<String> DHA = Arrays.asList("D0001", "D0002", "D0003");
    public static final List<String> SAFPS = Arrays.asList("S0001", "S0002", "S0003", "S0004");
    public static final List<String> HANIS = Arrays.asList("H0001", "H0002", "H0003", "H0004", "H0005", "H0006", "H0007", "H0008", "H0009", "H0010", "H0011", "H0012", "H0013", "H0014", "H0015", "H0016");

    public static final List<String> MockIdNumber = Arrays.asList("9812161037196", "9810011656124", "9809265354121", "9808191903001", "9808131478171", "9808051817036", "9808035233094", "9807251678180", "9807215126102", "9805311989050", "9804155888056", "9804075446100", "9803151233119", "9803091988178", "9711305122056", "9710261102110", "9709151786107", "9707315949173", "9707215041048", "9707075443003", "9706141364003", "9706075949050", "9705075788054", "9705071192111", "9704045474184", "9703221147028", "9703181191180", "9703021500137", "9702225187006", "9702131946057", "9701261586121", "9701235503160", "9610265488103", "9610225768198", "9610051305180", "9608241698027", "9607111649110", "9604225711019", "9604091574137", "9602281109128", "9602255531158", "9512281358197", "9512085012149", "9511245276040", "9510295836117", "9509225621037", "9508171188181", "9506131355015", "9505275465192", "9505195828123", "9503135701120", "9503035864168", "9501205512013", "9501195984115", "9412311140014", "9412295871063", "9412115657163", "9409295971125", "9408311151118", "9403191584132", "9401071488010", "9311301549160", "9310095885004", "9310085142150", "9310055075117", "9309121410027", "9304191214144", "9304155538041", "9303251375019", "9303121231020", "9302265903105", "9302135693167", "9212291629147", "9212205655022", "9208305169088", "9207251367076", "9207215393069", "9207115297063", "9206305082103", "9206235563107", "9206231986195", "9205161273046", "9204205619017", "9204095127030", "9204025266148", "9203065533094", "9202255125166", "9201291855158", "9201225268049", "9201101149008", "9111091260022", "9111061021024", "9107221594066", "9106195539123", "9106131757052", "9105305996199", "9105175729001", "9102185967176", "9102085571003", "9101215223113", "9101211598096", "9012311663054", "9012141176061", "9011275585030", "9010261737001", "9010141637157", "9009245470051", "9009105215174", "9006245089055", "9004015652186", "9002181100139", "9002055978164", "9001275557030", "9001225632115", "9001015215105", "8909301981076", "8908081317048", "8906241158062", "8905261593042", "8905111817096", "8905095572071", "8903121497124", "8812155984081", "8811211861135", "8810061891184", "8809161147083", "8808251054084", "8805015066164", "8803301127113", "8803155723124", "8803125105196", "8801311235124", "8712111116127", "8712065022198", "8711295689172", "8711295472058", "8711021772136", "8710175931027", "8709161386089", "8708175034156", "8705021301045", "8702161575025", "8612301597021", "8612221193042", "8612165328059", "8612155029196", "8611275594105", "8610315121010", "8610205476003", "8609181085093", "8608171414131", "8607281771059", "8607191826068", "8606251564056", "8606191900121", "8604181354003", "8603045826024", "8602181297115", "8601135110192", "8512175697123", "8510285144002", "8510245231139", "8510201816014", "8509141938110", "8509055729141", "8508125306138", "8507275803159", "8507131579043", "8507045984156", "8506195106198", "8505135765014", "8505105920094", "8504081452081", "8503241559165", "8503195880179", "8503065876026", "8503045170128", "8503031416048", "8503021223057", "8501131756016", "8501035680155", "8411191870022", "8410255374004", "8410211040129", "8410131766142", "8409191358097", "8408245008062", "8407285099106", "8404235845092", "8404155152156", "8404085605042", "8402015282163");

    static TemplateUtils templateSession = new TemplateUtils(FILE_NAME_MOCK_METHOD_CREATE_SESSION + EXT_JSON);
    static TemplateUtils templateCheckIdVStatus = new TemplateUtils(FILE_NAME_MOCK_METHOD_CHECK_ID_V_STATUS + EXT_JSON);

    public static void main(String... arg) {
        int idIndex = 0;
        for (String dha : DHA) {
            for (String safps : SAFPS) {
                for (String hanis : HANIS) {

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
        return MockIdNumber.get(idIndex);
    }

    private static String getSessionFileName(String idNumber) {
        return FILE_NAME_MOCK_METHOD_CREATE_SESSION + "/" + FILE_NAME_MOCK_METHOD_CREATE_SESSION + "_" + idNumber + EXT_JSON;
    }

    private static String getSession(int idIndex) {
        return CommonData.SESSION_PREFIX_IDV + String.format("%1$03d", idIndex);
    }
}
