package localtest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchVersion {

    public static void main(String[] args) {
        String apkName="public/apk/version/cloudphone-preview_wechatmp01-v2.0.0-20240401_1654-debug.apk";
        //根据当前版本号，获取v开头的版本号
        String versionPattern = "(\\d+\\.\\d+\\.\\d+)"; // 匹配形如 vX.Y.Z 的版本号

        Pattern pattern = Pattern.compile(versionPattern);
        Matcher matcher = pattern.matcher(apkName);
        if (matcher.find()) {
            System.out.println("提取到的版本号是: " + matcher.group(0));
        } else {
            System.out.println("警告：未能从文件名中正确提取版本号，请检查文件命名格式是否符合预期。");
        }
    }
}
