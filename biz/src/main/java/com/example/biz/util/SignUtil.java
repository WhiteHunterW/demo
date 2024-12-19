package com.example.biz.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wenzeng
 * @date 2024/11/19
 */
public class SignUtil {

    public static String getAccessToken() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("appKey", "e252d7acfd814bc0a74cadfc91ee6b3e");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String signature = createHmacSha256Sign(params, "72c295648ef9dcfb2d1a82a6c2f842690e20b08b");
        params.put("signature", signature);
        String result = HttpUtil.get("https://bipcw.xinchao.com/iuap-api-auth/open-auth/selfAppAuth/getAccessToken", new HashMap<>(params));
        JSONObject response = JSON.parseObject(result);
        String code = response.getString("code");
        if (!"00000".equals(code)) {
            throw new RuntimeException("access_token get failed:" + result);
        }
        JSONObject data = JSON.parseObject(response.getJSONObject("data").toString());
        String accessToken = data.getString("access_token");
        String encodeAccessToken = URLEncoder.encode(accessToken);
        return encodeAccessToken;
    }



    /**
     * 按参数名排序后依次拼接参数名称与数值，之后对该字符串使用 HmacSHA256 加签，加签结果进行 base 64 返回
     * @param params 请求参数 map
     * @param suiteSecret 套件密钥，用作 mac key
     * @return Exception
     */
    public static String createHmacSha256Sign(Map<String, String> params, String suiteSecret) throws Exception {
        Map<String, String> treeMap;
        if (params instanceof TreeMap) {
            treeMap = params;
        } else {
            treeMap = new TreeMap<>(params);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(suiteSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        String base64String = Base64.getEncoder().encodeToString(signData);
        return URLEncoder.encode(base64String, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        /*Map<String, String> params = new HashMap<>();
        params.put("appKey", "e252d7acfd814bc0a74cadfc91ee6b3e");
        params.put("timestamp", "1731996930565");
        String signature = createHmacSha256Sign(params, "72c295648ef9dcfb2d1a82a6c2f842690e20b08b");
        System.out.println(signature);*/
        /*String a = "frewr";
        String b = "asdfsdfasdfewrewr";
        System.out.println(a.compareTo(b));
        System.out.println('f'-'a');*/
        System.out.println(getAccessToken());
    }
}
