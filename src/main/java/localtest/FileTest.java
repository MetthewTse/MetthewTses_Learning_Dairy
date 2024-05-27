package localtest;

import cn.hutool.crypto.digest.MD5;

import java.nio.charset.StandardCharsets;

public class FileTest {
    public static void main(String[] args) {
        String signBeforeStr="7e26914e34c8a26e8ac3c7fd137315dc1706079309123468348f7b62484e3d2e9413a7b40cb4302aabe9b110090c33c4c2b7cd260d03c43e{\"mobileNumber\":\"13177828250\"}";
        String sign = MD5.create().digestHex(signBeforeStr, StandardCharsets.UTF_8);
        System.out.println(sign);

    }
}
