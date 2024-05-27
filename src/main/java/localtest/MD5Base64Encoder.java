package localtest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Base64Encoder {

    /**
     * 计算字符串的MD5摘要并进行Base64编码。
     *
     * @param input 要计算MD5摘要的字符串
     * @return MD5摘要的Base64编码字符串
     */
    public static String encodeToBase64MD5(String input) {
        try {
            // 创建MessageDigest实例，指定算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换成字节数组
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

            // 计算MD5摘要
            byte[] digestBytes = md.digest(inputBytes);

            // 对MD5摘要进行Base64编码
            String encodedDigest = Base64.getEncoder().encodeToString(digestBytes);

            return encodedDigest;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public static void main(String[] args) {
        String messageBody = "2dc353ebe5f297adb9c2567029e369ac";
        String encodedMD5 = encodeToBase64MD5(messageBody);
        System.out.println("The Base64 encoded MD5 digest of the message body is: " + encodedMD5);
    }
}