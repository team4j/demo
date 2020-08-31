package com.example.demo;

import okhttp3.*;

import java.io.IOException;

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
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        for (int i = 0; i < 1000; i++) {
            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override public void onResponse(Call call, Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                        Headers responseHeaders = response.headers();
                        for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }

                        System.out.println(responseBody.string());
                    }
                }
            });
        }
    }
}
