package com.example.demo;

import com.example.demo.export.LogResponse;
import com.example.demo.mapper.SourceMapper;
import com.example.demo.model.Source;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by dequan.yu on 2020/11/18.
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataExportTest {
    @Autowired
    private RestTemplate restTemplate;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    private final SourceMapper sourceMapper;

    @Test
    public void export() {
        String url = "https://log.xc-inc.cn/query?index=plume_log_run_20201117*&size=500&from=60";
        String requestData = "{\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"appName\":{\"query\":\"wms-api" +
                "-prod\"}}},{\"match_phrase\":{\"traceId\":{\"query\":\"83618643958697984\"}}}," +
                "{\"query_string\":{\"query\":\"\\\"当前库存量\\\"\",\"default_field\":\"content\"}}," +
                "{\"range\":{\"dtTime\":{\"gte\":1605542400000,\"lt\":1605628740000}}}]}}," +
                "\"highlight\":{\"fields\":{\"content\":{\"fragment_size\":2147483647}}}," +
                "\"sort\":[{\"dtTime\":\"desc\"}]}";
        LogResponse logResponse = this.restTemplate.postForObject(url, requestData, LogResponse.class);
        System.out.println(logResponse);
    }

    @Transactional
    @Test
    @Rollback(value = false)
    public void export2() throws IOException {
        String url = "https://log.xc-inc.cn/query?index=plume_log_run_20201114*&size=500&from=0";
        String requestData = "{\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"appName\":{\"query\":\"wms-api" +
                "-prod\"}}},{\"match_phrase\":{\"traceId\":{\"query\":\"82562140002717697\"}}}," +
                "{\"query_string\":{\"query\":\"\\\"当前库存量\\\"\",\"default_field\":\"content\"}}," +
                "{\"range\":{\"dtTime\":{\"gte\":1605283200000,\"lt\":1605369540000}}}]}}," +
                "\"highlight\":{\"fields\":{\"content\":{\"fragment_size\":2147483647}}}," +
                "\"sort\":[{\"dtTime\":\"desc\"}]}";
        final String tag = "20201114第二次";
        String json = this.post(url, requestData);
        System.out.println(json);
        Gson gson = new Gson();
        LogResponse logResponse = gson.fromJson(json, LogResponse.class);
//        System.out.println(logResponse);

        logResponse.getHits().getHits().forEach(h -> {
            LogResponse.HitsX.Hits.Source source = h.get_source();
            String content = source.getContent();
            int a = content.indexOf('[');
            int b = content.indexOf(']');
            String whSting = content.substring(a + 1, b);

            int c = content.indexOf('[', b + 1);
            int d = content.indexOf(']', b + 1);
            String goodsIdSting = content.substring(c + 1, d);

            int e = content.indexOf('[', d + 1);
            int f = content.indexOf(']', d + 1);
            String storeSting = content.substring(e + 1, f);

            System.out.println(String.format("仓库：%s，GoodId：%s，Store：%s", whSting, goodsIdSting, storeSting));

            Source dataSource = Source.builder().dateTime(source.getDateTime()).goodsId(Integer.valueOf(goodsIdSting))
                    .whId(Integer.valueOf(whSting)).storeCount(Integer.valueOf(storeSting)).tag(tag).logTraceId(source.getTraceId()).build();
            int result = this.sourceMapper.insert(dataSource);
            if (result != 1) {
                throw new RuntimeException("插入失败");
            } else {
                log.info("插入成功");
            }
        });
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}
