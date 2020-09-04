package com.example.demo;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dequan.yu on 2020/8/31.
 */
public class OkHttpTest {
    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        try {
            new OkHttpTest().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("supplierCode", "GYS2020082843715447");
        requestData.put("configId", 1);
        Request request = new Request.Builder()
                .url("http://10.101.164.67:8183/api/merchant/credit/wmsAutomaticInput")
                .post(RequestBody.create(new Gson().toJson(requestData), MediaType.parse("application/json; charset=utf-8")))
                .build();
        for (int i = 0; i < 500; i++) {
            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override public void onResponse(Call call, Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                        System.out.println(responseBody.string());
                    }
                }
            });
        }
    }
}
