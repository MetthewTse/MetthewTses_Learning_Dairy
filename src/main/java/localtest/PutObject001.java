package localtest;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import okhttp3.*;

import java.util.HashMap;
import java.util.Map;

public class PutObject001 {
    public static void main(String[] args) {
        // 您可以通过环境变量获取访问密钥AK/SK，也可以使用其他外部引入方式传入。如果使用硬编码可能会存在泄露风险。
        // 您可以登录访问管理控制台获取访问密钥AK/SK
        String ak = "7LXK9OHWOXTKFIBATOSX";
        String sk = "DB4DClaoVwvfrzZ6SpNA8Opzb6MyA9FnXUN0QAU7";
        // 【可选】如果使用临时AK/SK和SecurityToken访问OBS，同样建议您尽量避免使用硬编码，以降低信息泄露风险。
        // 您可以通过环境变量获取访问密钥AK/SK/SecurityToken，也可以使用其他外部引入方式传入。
        // String securityToken = System.getenv("SECURITY_TOKEN");
        // endpoint填写桶所在的endpoint, 此处以华北-北京四为例，其他地区请按实际情况填写。
        String endPoint = "https://obs.cn-south-1.myhuaweicloud.com";
        // 您可以通过环境变量获取endPoint，也可以使用其他外部引入方式传入。
        //String endPoint = System.getenv("ENDPOINT");

        // 创建ObsClient实例
        // 使用永久AK/SK初始化客户端
        ObsClient obsClient = new ObsClient(ak, sk,endPoint);
        // 使用临时AK/SK和SecurityToken初始化客户端
        // ObsClient obsClient = new ObsClient(ak, sk, securityToken, endPoint);

        try {
            // URL有效期，3600秒
            long expireSeconds = 3600L;
            TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.PUT, expireSeconds);

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/octet-stream");
            request.setHeaders(headers);
            request.setBucketName("common-gz");
            request.setObjectKey("objectname");
            TemporarySignatureResponse response = obsClient.createTemporarySignature(request);
            System.out.println("Creating object using temporary signature url:");
            System.out.println("\t" + response.getSignedUrl());
            Request.Builder builder = new Request.Builder();
            for (Map.Entry<String, String> entry : response.getActualSignedRequestHeaders().entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
            // 使用PUT请求上传对象
            Request httpRequest =
                    builder.url(response.getSignedUrl())
                            .put(RequestBody.create(null, "Hello OBS".getBytes("UTF-8")))
                            .build();
            OkHttpClient httpClient =
                    new OkHttpClient.Builder()
                            .followRedirects(false)
                            .retryOnConnectionFailure(false)
                            .cache(null)
                            .build();
            Call c = httpClient.newCall(httpRequest);
            Response res = c.execute();
            System.out.println("Status:" + res.code());
            if (res.body() != null) {
                System.out.println("Content:" + res.body().string() + "\n");
            }
            res.close();
            System.out.println("PutObject successfully");
        } catch (ObsException e) {
            System.out.println("PutObject failed");
            // 请求失败,打印http状态码
            System.out.println("HTTP Code:" + e.getResponseCode());
            // 请求失败,打印服务端错误码
            System.out.println("Error Code:" + e.getErrorCode());
            // 请求失败,打印详细错误信息
            System.out.println("Error Message:" + e.getErrorMessage());
            // 请求失败,打印请求id
            System.out.println("Request ID:" + e.getErrorRequestId());
            System.out.println("Host ID:" + e.getErrorHostId());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("PutObject failed");
            // 其他异常信息打印
            e.printStackTrace();
        }
    }
}


