package utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;

public class MD5Test {

    public static void main(String[] args) {
        String apiAppKey="3cbfd6bfafd4e95356a87815fa66dd28";
        String timestamp="1706079309";
        String traceId="123456";
        String appSecret="5992848297f50126bd48c7450279f1c7";
        String account="1310000000";
        JSONObject entries = new JSONObject();
        entries.put("account",account);
        String body = entries.toString();
        String s = SecureUtil.md5(apiAppKey + timestamp + traceId + appSecret + body);
        System.out.println(s);
    }
}
