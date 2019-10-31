package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class LyTest {
    @Test
    public void bigDecimal() {
        BigDecimal b = new BigDecimal("123.456").setScale(0, BigDecimal.ROUND_DOWN);
//        b.setScale(1, BigDecimal.ROUND_DOWN);
        System.out.println(b);

    }

    @Test
    public void foreach() {
        Price price = Price.builder().a(10).b(2).build();
        Price price1 = Price.builder().a(12).b(2).build();
        Price price2 = Price.builder().a(8).b(2).build();
        List<Price> priceList = Arrays.asList(price, price1, price2);

        List<Price> newPriceList = priceList.stream()
                .peek(p -> p.setC(p.getA() - p.getB()))
                .sorted(Comparator.comparing(Price::getC))
                .filter(price3 -> price3.getC() >= 8)
                .collect(Collectors.toList());

        newPriceList.forEach(System.out::println);
        newPriceList.forEach(p -> p.setC(0));
        newPriceList.forEach(System.out::println);
    }

    @Test
    public void limit() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integerList2 = integerList.stream().limit(3).collect(Collectors.toList());
        integerList2.forEach(System.out::print);
    }

    @Test
    public void map() {
        Price price = Price.builder().a(10).b(2).build();
        Price price1 = Price.builder().a(12).b(2).build();
        Price price2 = Price.builder().a(8).b(2).build();
        List<Price> priceList = Arrays.asList(price, price1, price2);

//        List<Integer> integerList = priceList.stream().map(p -> p.getA()).collect(Collectors
//        .toList());
        List<Integer> integerList =
                priceList.stream().map(Price::getA).collect(Collectors.toList());
        integerList.forEach(System.out::println);
    }

    @Test
    public void collectToMap() {
        List<Person> personList = Arrays.asList(Person.builder().id(1).name("Charles").build(),
                Person.builder().id(2).name("Catalina").build());
        Map<Integer, Person> map = personList.stream().collect(Collectors.toMap(Person::getId, person -> person));
        System.out.println(map);
    }

    @Test
    public void group() {
        List<Person> personList = Arrays.asList(Person.builder().id(1).name("Charles").build(),
                Person.builder().id(2).name("Catalina").build());
        Map<Boolean, List<Person>> map = personList.stream().collect(Collectors.groupingBy(person -> person.getId() == 1));
        System.out.println(map);
    }

    @Test
    public void max() {
        List<Person> personList = Arrays.asList(Person.builder().id(1).name("Charles").build(),
                Person.builder().id(2).name("Catalina").build());

        Person person = personList.stream().max(Comparator.comparing(Person::getId)).orElse(null);
        System.out.println(Optional.ofNullable(person).isPresent() ? person.getId() : -1);
    }

    @Test
    public void optional() {
        System.out.println(Optional.of(null).isPresent());
        System.out.println(Optional.of("").isPresent());
        System.out.println(Optional.ofNullable("").isPresent());
    }

    @Test
    public void date() throws ParseException {

        Date userTime = DateUtils.parseDate("2019-10-29 12:00:00", "yyyy-MM-dd HH:mm:ss");
        String userTimeString = DateFormatUtils.format(userTime, "yyyy-MM-dd HH:mm:ss");
        String[] time = userTimeString.split(" ");
        Date todayTime = DateUtils.setMinutes(DateUtils.setHours(new Date(), Integer.parseInt(time[0])), Integer.parseInt(time[1]));

        String businessHours = "07:00:00-18:00:00";
        String[] businessHoursInterval = businessHours.split("-");
        String[] startHour = businessHoursInterval[0].split(":");
        Date startTime = DateUtils.setMinutes(DateUtils.setHours(new Date(),
                Integer.parseInt(startHour[0])),
                Integer.parseInt(startHour[1]));
        String[] endHour = businessHoursInterval[1].split(":");
        Date endTime = DateUtils.setMinutes(DateUtils.setHours(new Date(), Integer.parseInt(endHour[0])),
                Integer.parseInt(endHour[1]));

        System.out.println(startTime);
        System.out.println(endTime);

        if (todayTime.getTime() >= startTime.getTime()
                && todayTime.getTime() <= endTime.getTime()) {
            System.out.println("支持服务");
        }
    }

    @Test
    public void date2(){
//        Date date = DateUtils.parseDate("2019-10-28 20:20:00", "yyyy-MM-dd HH:mm:ss");

        String businessHours = "07:00:00-18:00:00";
        String[] businessHoursInterval = businessHours.split("-");
        String currentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startTimeString = currentDate + " " +  businessHoursInterval[0];
        String endTimeString = currentDate + " " +  businessHoursInterval[1];

        String useTimeString = "2019-10-30 7:30".split(StringUtils.SPACE)[1];
        String todayUseTimeString = currentDate + " " + useTimeString;

        System.out.println(startTimeString);
        System.out.println(endTimeString);
        System.out.println(todayUseTimeString);


        Date startTime = null;
        Date endTime = null;
        Date todayUseTime = null;
        try {
            startTime = DateUtils.parseDate(startTimeString, "yyyy-MM-dd HH:mm:ss");
            endTime = DateUtils.parseDate(endTimeString, "yyyy-MM-dd HH:mm:ss");
            todayUseTime = DateUtils.parseDate(todayUseTimeString, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            try {
                todayUseTime = DateUtils.parseDate(todayUseTimeString, "yyyy-MM-dd HH:mm");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        if (todayUseTime.getTime() >= startTime.getTime() && todayUseTime.getTime() <= endTime.getTime()) {
            System.out.println("提供服务");
        } else {
            System.out.println("不提供服务");

        }
    }

    @Test
    public void string() {
        String s = "汉口火车站";
        s = s.replace("火车站", "站");
        System.out.println(s);
    }

    @Test
    public void date3() {
        try {
            Date date = DateUtils.parseDate("2019-10-30 7:30:00", "yyyy-MM-dd HH:mm:ss");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void date4() {
        String date = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern());
        System.out.println(date);
    }

    @Test
    public void gson() {
        Person person = Person.builder().id(1).name("admin").password("admin").build();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(person);
        System.out.println(json);
    }
}


@Builder
@Data
class Price {
    private Integer a;
    private Integer b;
    private Integer c;
}

@Builder
@Data
class Info {
    private List<Price> priceList;
}

@Builder
@Data
class Person {
    @Expose
    private Integer id;
    @Expose
    private String name;
//    private transient String password;
    @Expose(serialize = false, deserialize = false)
    private String password;
}