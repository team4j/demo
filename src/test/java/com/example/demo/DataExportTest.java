package com.example.demo;

import com.example.demo.export.LogResponse;
import com.example.demo.mapper.SalesDataMapper;
import com.example.demo.mapper.SourceMapper;
import com.example.demo.model.SalesDataDO;
import com.example.demo.model.Source;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private final SalesDataMapper salesDataMapper;

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
    @Rollback(value = false)
    @Test
    public void export3() throws IOException {
        String url = "https://log.xc-inc.cn/query?index=plume_log_run_20210129*&size=5000&from=0";
        String requestData = "{\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"appName\":{\"query\":\"wms-api" +
                "-prod\"}}},{\"match_phrase\":{\"LOGGER_SUBCATEGORY\":{\"query\":\"createSortingTask\"}}}," +
                "{\"query_string\":{\"query\":\"\\\"通过预测模型接口查询到每日预估销量\\\"\",\"default_field\":\"content\"}}," +
                "{\"range\":{\"dtTime\":{\"gte\":1611849600000,\"lt\":1611927970000}}}]}}," +
                "\"highlight\":{\"fields\":{\"content\":{\"fragment_size\":2147483647}}}," +
                "\"sort\":[{\"dtTime\":\"desc\"}]}";
        String json = this.post(url, requestData);
        System.out.println(json);
        Gson gson = new Gson();
        LogResponse logResponse = gson.fromJson(json, LogResponse.class);
        logResponse.getHits().getHits().forEach(hits -> {
            LogResponse.HitsX.Hits.Source source = hits.get_source();
            if (source.getTraceId().equals("110085883335675904") || source.getTraceId().equals("110124722473275392")
                    || source.getTraceId().equals("110154934980317184") || source.getTraceId().equals("110185098141372416")) {
                String tag = "";
                switch (source.getTraceId()) {
                    case "110085883335675904": {
                        tag = "第一次";
                        break;
                    }
                    case "110124722473275392": {
                        tag = "第二次";
                        break;
                    }
                    case "110154934980317184": {
                        tag = "第三次";
                        break;
                    }
                    case "110185098141372416": {
                        tag = "第四次";
                        break;
                    }
                }

                // [仓库：1，目标商品：113653]，仓库：[1]，通过预测模型接口查询到每日预估销量：[285]
                String content = source.getContent();
                int whIdStartIndex = content.indexOf("仓库：") + "仓库：".length();
                int whIdEndIndex = content.indexOf("，");
                int goodsIdStartIndex = content.indexOf("目标商品：") + "目标商品：".length();
                int goodsIdEndIndex = content.indexOf("]");
                int salesStartIndex = content.indexOf("预估销量：[") + "预估销量：[".length();
                int salesEndIndex = content.lastIndexOf("]");
                SalesDataDO salesDataDO = SalesDataDO.builder()
                        .whId(Integer.valueOf(content.substring(whIdStartIndex, whIdEndIndex)))
                        .goodsId(Integer.valueOf(content.substring(goodsIdStartIndex, goodsIdEndIndex)))
                        .sales(Integer.valueOf(content.substring(salesStartIndex, salesEndIndex)))
                        .salesTime(LocalDateTime.parse(source.getDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                        .createTime(LocalDateTime.now())
                        .tag(tag)
                        .build();
                int result = this.salesDataMapper.insert(salesDataDO);
                if (result != 1) {
                    throw new RuntimeException("插入失败");
                } else {
                    log.info("插入成功");
                }
            }
        });
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


    @Test
    public void select() {
        List<SalesDataDO> salesDataDOList = this.salesDataMapper.getAllByCreateTime(LocalDateTime.now());
        Assertions.assertTrue(salesDataDOList.size() > 0);
        System.out.println(salesDataDOList);
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
