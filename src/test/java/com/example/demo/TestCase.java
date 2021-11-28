package com.example.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by dequan.yu on 2021/11/24.
 */
@Slf4j
public class TestCase {
    @Test
    public void sumBigDecimalList() {
        List<BigDecimal> list = Arrays.asList(new BigDecimal(31), new BigDecimal(40), new BigDecimal(30));
        BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info(sum.toString());
    }

    @Test
    public void regex() {
        String content = "-5.011111111%";
        String pattern = "\\b(?<!\\.)(?!0+(?:\\.0+)?%)(?:\\d|[1-9]\\d|100)(?:(?<!100)\\.\\d+)?%";

        boolean isMatch = Pattern.matches(pattern, content);

        System.out.println("字符串中是否包含了字符串? " + isMatch);
    }

    @Test
    public void sort() {
        List<String> list = Arrays.asList("5.1-6.4%", "5.1%", "3.1%", "12.8%", "14.5%");
        list.sort(Comparator.reverseOrder());
        list.forEach(System.out::println);


        List<Movie> movies = Arrays.asList(
                new Movie("Lord of the rings", 8.8),
                new Movie("Back to the future", 8.5),
                new Movie("Carlito's way", 7.9),
                new Movie("Pulp fiction", 8.9));
        movies.sort(Comparator.comparingDouble(Movie::getRating)
                .reversed());
        movies.forEach(System.out::println);
    }

    @Data
    private static class Movie {
        private String name;
        private double rating;

        public Movie(String name, double rating) {
            this.name = name;
            this.rating = rating;
        }
    }
}

