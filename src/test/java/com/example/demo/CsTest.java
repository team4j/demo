package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * Created by dequan.yu on 2022/5/19.
 */
public class CsTest {
    @Test
    public void validate() {
        try {
            Validate.isTrue(1 == 2, "明显错了");
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void random() {
        while (true) {
            String random = RandomStringUtils.randomNumeric(6);
            if (StringUtils.startsWith(random, "00")) {
                System.out.println(random);
                return;
            }
        }
    }

    @Test
    public void regex() {
        System.out.println(Pattern.matches("^E011_\\d{2}_30$", "E011_01_30"));

        System.out.println(Pattern.matches("^\\d{6}$", "a90090"));

        System.out.println(Pattern.matches("[a-z]*aa[a-z]*|[a-z]*bb[a-z]*", "ccbbcc"));
    }
}
