package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dequan.yu on 2021/6/11.
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class RestTemplateTest {
    private final RestTemplate restTemplate;

    @Test
    public void accessObject() {
        String url = "https://wmsapi.youxianbianli.com/wms/sorting-task/cancel";
        RestRequest request = RestRequest.builder()
                .id(1145333)
                .reason("仓库人员要求批量取消打包任务")
                .reasonType(5).build();

        RestResponse response = restTemplate.postForObject(url, request, RestResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("ok", response.getResult());
        System.out.println(response);
    }

    @Test
    public void accessEntity() {
        String url = "https://wmsapi.youxianbianli.com/wms/sorting-task/cancel";
        String token = "058aAi7HSA4mNphXufRfHa0E3zRp+WhRCPAGWADK44uBhEL0seEkM5sbihvXuYQb/Cvv7MJMklhr+WAYfDDgXXfP/4HRHgYvuU5L2PPAQAdLnE1qMgNJgiAQbInwoo+nsTJWa5RpGwdB82K9RcFK+GvrbA/Bp3kMM7V58TPq4o4MhkJJHmUWi6V83xC9veKfjpTnaT7eUjrq3ktOGDXTMIh1fdN7NAZ7PoMl0tkrw2uoTS7KDK+CtORMg5nSz3Nqgc4+Az/rYW1sWGWXed1AWfKkZ9eu3a2yEeZLYGQBafyvUOokSfPTKCfOnYxExVM72Z7aRXWv1nJQPnzkGq+9l5lVGSSs2B0OFstJR5MeK5DSUTItIsrfJOQaTgiobyU0vaaueC5S+ChU0qC4yGkvnVSRgVI5wO/rZkShY9vRL5KE7tj/MQ29JhrCdpxXsRDOIBMxRvU3ykBMCYAt5NkZ8VLuaeJlcowe+yevsDxxrFrxnmLelyFx59fv7L9N5tEksSzgYtphcNpAnLPhzdYxfU65SeUJ5cVmKfs8ESpOOIhxVMLRDdDYpq4xxEt5uiSf8UE7/2fkjUJR0TStoHRJOMaeUbtoI1qfG6TOxKIebS5WZXVUTnTr0mg+nt3nAhcqdSkBGsLF4DAtPmuDaSWnrBwOHS/HYreT4GC/AfpHvD6134dR4TpGnUYc6s1Bp+t6FBd23w9qh83X2xWZJSp80OoDYdz8JgElYFWogkUBVn8oQoEXJtnX39lzsguYO5cmDlLyiv7jkqOzo1Y1Z5JSINQNZ2ddHDw";

        List<Integer> ids = new ArrayList<>(Arrays.asList(1145022, 990518));

        RestRequest request = RestRequest.builder()
                .reason("仓库人员要求批量取消打包任务")
                .reasonType(5).build();

        ids.forEach(id -> {
            request.setId(1145333);

            HttpHeaders headers = new HttpHeaders();
            headers.add("tctoken", token);
            HttpEntity<RestRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<RestResponse> response = restTemplate.postForEntity(url, entity, RestResponse.class);
            Assertions.assertNotNull(response.getBody());
            System.out.println(response.getBody());
            Assertions.assertEquals("ok", response.getBody().getResult());
        });
    }

    @Test
    public void async() {
        String url = "https://wmsapi.youxianbianli.com/wms/sorting-task/cancel";
        String token = "31895F8r3vGGXafPYjcUwixWO07UIQCqjB91AJoE8Cjeex/G55pYZGAKchiQTFQdHGVp/wOD8TnNiBqqGIzV3efyxM2Y1OYjF+Icm1GsLqvENRiYFOpZW8WvLU1yGxw98wdWSRP9F7mtCumhKZxRi4SIW3oSNN3wvlW4DKh206KyDURwKYf36opBN77AQjkY8/QtjoiMS43SqvSl0STDvp0c4cLTO6z0ViUOHTmNMvcproyw22XfKPM/FaZUrnZoRyDqwcZZmkgZRCXC5/gbCBxXL1wURRp6Dav6GwsnUG+3L+vAxPu07/ZiSioI7VznAopEOKHrNVGrV/2Pu6R/A8unN6wwGFF4dO0Ln22g5INvWQInOjUOa+BGwYj7eSGhZ3J0pjKNmKm7wm9EnwAvUfK4YOVO1D/yIlGbi3bAbDAPs1WsNQ8mvDaWY3Gh95htdu+oC3U0NGetxhSnzfqspi01avezJIoEAoKB2L1HQYpmdI9nzyVQYK/IKEJiyQgbKrwjcY474mxL99OaFudEMCmv1D6B4nW6ABJkzheNqKtwVI5yJ5mWt3abjba43Ol49h7fVkp+RqHZg4BReDs8GFcF5nSIkuIh9QyWgjFBFuRHHHpfzJTmjCSUl418cKeeM/o75sLitcN8Q17d1G4Fwb9QACbTi0JBAY0a8HBxKa/crCuTVOh1vTJNuLTFB6geu0RoZdizadg+5uSAGAx/Cr4pAWn/0LMxgtaq79nr65iH99OHyPCyKZsBvMMeNfpWK5fHmYB3JE9pvZR0Wyy+sQj77HJVuuNEWw";

        RestRequest request = RestRequest.builder()
                .id(1144960)
                .reason("仓库人员要求批量取消打包任务")
                .reasonType(5).build();

        Mono<RestResponse> mono =  WebClient.create().post()
                .uri(url)
                .header("tctoken", token)
                .body(Mono.just(request), RestRequest.class)
                .retrieve()
                .bodyToMono(RestResponse.class);
        System.out.println(mono.block());
    }
}
