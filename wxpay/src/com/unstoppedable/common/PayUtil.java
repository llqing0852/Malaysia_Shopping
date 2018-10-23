package com.unstoppedable.common;

import com.squareup.okhttp.*;
import net.sf.json.JSONObject;

import java.io.IOException;

public class PayUtil {

    public static String getPayUrl(String cardName, String cardNo, String outTradeNo, double amount, int pid) {
        String requestJson = "{\n" +
                "  \"accessToken\": \"" + getAccessToken() + "\",\n" +
                "  \"param\": {\n" +
                "    \"body\": \"订单商品\",\n" +
                "    \"detail\": \"商品详情\",\n" +
                "    \"money\": " + amount * 100 + ",\n" +
                "    \"notifyUrl\": \"http://139.196.211.10/mall/NotifyUrl.aspx\",\n" +
                "    \"outTradeNo\": \"" + outTradeNo + "\",\n" +
                "\"cardName\": \"" + cardName + "\",\n" +
                "\"cardNo\": \"" + cardNo + "\",\n" +
                "\"productId\": \"" + pid + "\",\n" +
                "    \"type\": \"T1\"\n" +
                "  }\n" +
                "}";

        String responseJson = execute("http://api.huhupay.cn/pay/v1/gateway/merchant/noCardQuickPay", requestJson);
        JSONObject jsonObject = JSONObject.fromObject(responseJson);
        String payUrl = jsonObject.getString("value");

        return payUrl;
    }

    private static String getAccessToken() {
        String requestJson = "{\n" +
                "  \"no\": \"M000000000640170\",\n" +
                "  \"nonce\": \"1234567\",\n" +
                "  \"sign\": \"553D7B51B3EE5B37584222C4CCC835EF\",\n" +
                "  \"timestamp\": 1234\n" +
                "}";
        String tokenResponseJson = execute("http://api.huhupay.cn/pay/v1/gateway/merchant/main/getAccessToken", requestJson);

        JSONObject jsonObject = JSONObject.fromObject(tokenResponseJson);

        String accessToken = JSONObject.fromObject(jsonObject.getString("value")).getString("accessToken");

        return accessToken;
    }

    private static String execute(String url, String jsonBody) {
        OkHttpClient client = new OkHttpClient();
        String responseString = "";

        try {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

}




