package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.channels.FileChannel.MapMode.READ_ONLY;
import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import static java.nio.file.StandardOpenOption.*;

@Slf4j
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
        Map<Boolean, List<Person>> map =
                personList.stream().collect(Collectors.groupingBy(person -> person.getId() == 1));
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
        Date todayTime = DateUtils.setMinutes(DateUtils.setHours(new Date(), Integer.parseInt(time[0])),
                Integer.parseInt(time[1]));

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
    public void date2() {
//        Date date = DateUtils.parseDate("2019-10-28 20:20:00", "yyyy-MM-dd HH:mm:ss");

        String businessHours = "07:00:00-18:00:00";
        String[] businessHoursInterval = businessHours.split("-");
        String currentDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String startTimeString = currentDate + " " + businessHoursInterval[0];
        String endTimeString = currentDate + " " + businessHoursInterval[1];

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
        String date = DateFormatUtils.format(new Date(),
                DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern());
        System.out.println(date);
    }

    @Test
    public void gson() {
        Person person = Person.builder().id(1).name("admin").password("admin").build();
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println(json);

        Person de = gson.fromJson(json, Person.class);
        System.out.println(de);
    }

    @Test
    public void date5() throws ParseException {
        Date date = DateUtils.parseDate("2019-11-27T00:00:00".replace("T", " "), "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
    }

    @Test
    public void date6() throws ParseException {
//        Date date = DateUtils.parseDate("2019-11-27T12:50:00", DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.);
        Date date = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.parse("2019-11-27T12:50:00");
        System.out.println(date);
    }

//    @Test
//    public void  builder() {
//        Cat cat = Cat.builder().name("tom").build();
//        System.out.println(cat);
//    }

    @Test
    public void bigDecimal2() {
        BigDecimal zero = BigDecimal.ZERO;
        System.out.println(zero);
    }

    @Test
    public void log() {
        log.debug("Hi, {}", "阿黄");
    }

    @Test
    public void date7() throws ParseException {
        Date useTime = DateUtils.parseDate("2019-11-26 12:20:00", "yyyy-MM-dd HH:mm:ss");
        Date planArrTime = DateUtils.parseDate("2019-11-26 12:50:00", "yyyy-MM-dd HH:mm:ss");
        Date planDepTime = DateUtils.parseDate("2019-11-26 15:00:00", "yyyy-MM-dd HH:mm:ss");

        long between = planDepTime.getTime() - useTime.getTime();
        long result = between / (60 * 1000);
        System.out.println(result);
        // 用车时间到飞机起飞时间小于30分钟
        if (result <= 30) {
            System.out.println("起飞");
        }

        between = useTime.getTime() - planArrTime.getTime();
        result = between / (60 * 1000);
        System.out.println(result);
        // 用车时间到飞机降落时间小于30分钟
        if (between / (60 * 1000) <= 30) {
            System.out.println(between);
            System.out.println("降落");
        }
    }

    @Test
    public void duration() throws ParseException {
        Date useTime = DateUtils.parseDate("2019-11-26 12:20", "yyyy-MM-dd HH:mm");
        String useTimeIso = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(useTime);
        System.out.println(useTimeIso);
        LocalDateTime startT = LocalDateTime.parse(useTimeIso);
        LocalDateTime endT = LocalDateTime.parse("2019-11-26T12:50:00");
        Duration duration = Duration.between(startT, endT);
        System.out.println(duration.getSeconds() / 60);
    }

    @Test
    public void duration2() throws ParseException {
        // 接机时间
        Date pickupUseTime = null;
        // 送机时间
        Date dropOffUseTime = null;

        String planDeDateTime = "2019-11-14T12:00:00";
        String planArrDateTime = "2019-11-14T14:00:00";
        Date planDepTime = DateFormatUtils.ISO_DATETIME_FORMAT.parse(planDeDateTime);
        Date planArrTime = DateFormatUtils.ISO_DATETIME_FORMAT.parse(planArrDateTime);
        LocalDateTime planDepTimeLocal = LocalDateTime.parse(planDeDateTime);
        LocalDateTime planArrTimeLocal = LocalDateTime.parse(planArrDateTime);
        LocalDateTime now = LocalDateTime.now();

        // 现在距离飞机计划起飞的时间
        Duration durationDepTime = Duration.between(now, planDepTimeLocal);
        // 现在距离飞机计划降落的时间
        Duration durationArrTime = Duration.between(now, planArrTimeLocal);
        System.out.println(durationDepTime.getSeconds());
        System.out.println(durationArrTime.getSeconds());

        // 距离起飞时间>4h
        if (durationDepTime.getSeconds() > 4 * 60 * 60) {
            pickupUseTime = DateUtils.addHours(planDepTime, -4);
            dropOffUseTime = DateUtils.addMinutes(planArrTime, 30);
            System.out.println("距离起飞时间>4h");
        }

        // 距离起飞时间0-4h
        if (durationDepTime.getSeconds() >= 0 && durationDepTime.getSeconds() <= 4 * 60 * 60) {
            pickupUseTime = new Date();
            dropOffUseTime = DateUtils.addMinutes(planArrTime, 30);
            System.out.println("距离起飞时间0-4h");
        }

        // 起飞时间与实际降落时间之间
        if (durationDepTime.getSeconds() < 0 && durationArrTime.getSeconds() >= 0) {
            pickupUseTime = new Date();
            dropOffUseTime = DateUtils.addMinutes(planArrTime, 30);
            System.out.println("起飞时间与实际降落时间之间");
        }

        // 实际降落时间与实际降落时间+30min之间
        if (durationArrTime.getSeconds() < 0 && durationArrTime.getSeconds() >= -30 * 60) {
            pickupUseTime = new Date();
            dropOffUseTime = DateUtils.addMinutes(planArrTime, 35);
            System.out.println("实际降落时间与实际降落时间+30min之间");
        }

        // >实际降落时间+30min
        if (durationArrTime.getSeconds() < -30 * 60) {
            pickupUseTime = new Date();
            dropOffUseTime = new Date();
            System.out.println(">实际降落时间+30min");
        }

        System.out.println(pickupUseTime);
        System.out.println(dropOffUseTime);
    }

    @Test
    public void stringFormat() {
        String message = "%s";
        System.out.println(String.format(message, 1));
    }

    @Test
    public void localDateTime() {
        System.out.println(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(DateTimeFormatter.ISO_DATE);
        System.out.println(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String time = "2019-11-14 14:00";
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void integer() {
        Integer i = null;

        if (i > 0) {
            System.out.println();
        }
    }

    @Test
    public void duration3() {
        LocalDateTime useTimeLocal = LocalDateTime.parse("2019-11-17T21:45:00");
        LocalDateTime planArrTime = LocalDateTime.parse("2019-11-17T21:15:00");

        Duration duration = Duration.between(planArrTime, useTimeLocal);

        // 用车时间到飞机降落时间小于30分钟
        if (duration.getSeconds() < 30 * 60) {
            System.out.println("ssfdsfds");
        }
    }

    @Test
    public void date2LocalDate() {
//        System.out.println(LocalDate.now().isBefore(LocalDate.parse("2019-11-20")));
//        System.out.println(LocalDate.now().compareTo(LocalDate.parse("2019-11-18")) <= 0);
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));


        if (LocalDate.now().compareTo(LocalDate.parse("2019-11-20")) > 0) {
            System.out.println("sdfsdf");
        }
    }

    @Test
    public void coup() {
        String json = "{\"id\":1342,\"couponName\":\"70元接送机券\",\"giftCard\":\"pOCyauBLUSlj01mncM1bDzxSKOm8\"," +
                "\"batchNumber\":\"pOCyauBLUSlj01mncM1bDzxSKOm8\",\"couponHolder\":0,\"amount\":70," +
                "\"cityId\":\"0000\",\"cityName\":\"全国\",\"productId\":\"12,13\",\"carTypeId\":\"1,2,3,4\"," +
                "\"describe\":\"单笔接送机订单满100元可用\",\"status\":1,\"operator\":\"宋响1201423\",\"createTime\":\"2019-09-17 " +
                "18:25:00\",\"carRate\":100,\"otherRate\":0,\"supplierRate\":0,\"userRange\":0,\"couponResource\":2," +
                "\"supplierCode\":\"0000\"}";

        Coup coupon = new Gson().fromJson(json, Coup.class);
        String carTypeSign = "";
        String productIdSign = "19";
        String cityIdSign = "170";
        System.out.println((!"0000".equals(coupon.getCarTypeId()) && !(coupon.getCarTypeId() + ",").contains(carTypeSign)));
        System.out.println((!"0000".equals(coupon.getProductId()) && !(coupon.getProductId() + ",").contains(productIdSign)));
        System.out.println((!"0000".equals(coupon.getCityId()) && !(coupon.getCityId() + ",").contains(cityIdSign)));
    }

    @Test
    public void base64() {
    }

    @Test
    public void stream() {
        List<Integer> integerList = Stream.of(1, 2).collect(Collectors.toList());
        integerList.forEach(System.out::println);
    }

    @Test
    public void optional1() {
        Optional<Integer> integer = Stream.of(1, 2).filter(i -> i == 1).findFirst();
        System.out.println(integer.isPresent());
    }

    @Test
    public void exception() {
        try {
            int s = 1 / 0;
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }

    @Test
    public void groupBy() {
        OrderPassenger passenger1 = OrderPassenger.builder().orderId(1).name("jack").build();
        OrderPassenger passenger2 = OrderPassenger.builder().orderId(1).name("rose").build();
        OrderPassenger passenger3 = OrderPassenger.builder().orderId(2).name("catalina").build();
        OrderPassenger passenger4 = OrderPassenger.builder().orderId(3).name("charles").build();
        List<OrderPassenger> passengerList = Arrays.asList(passenger1, passenger2, passenger3, passenger4);
        Map<Integer, String> groupName = passengerList.stream()
                .collect(Collectors.groupingBy(OrderPassenger::getOrderId, Collectors.mapping(OrderPassenger::getName
                        , Collectors.joining(","))));
        Map<Integer, List<OrderPassenger>> map =
                passengerList.stream().collect(Collectors.groupingBy(OrderPassenger::getOrderId));

//        List<OrderPassenger> finalPassengerList = new ArrayList<>();
//        group.forEach((integer, orderPassengers) -> {
//            StringBuilder names = new StringBuilder();
//            orderPassengers.forEach(p -> names.append(p.getName()).append(','));
//            names.deleteCharAt(names.length() - 1);
//            OrderPassenger passenger = orderPassengers.get(0);
//            passenger.setName(names.toString());
//            finalPassengerList.add(passenger);
//        });
//
//        System.out.println(finalPassengerList);

        List<OrderPassenger> finalPassengerList = new ArrayList<>();
        groupName.forEach((id, names) -> {
            OrderPassenger passenger = map.get(id).get(0);
            passenger.setName(names);
            finalPassengerList.add(passenger);
        });

        System.out.println(finalPassengerList);
    }

    @Test
    public void date8() {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_MONTH, 1);
        String aoOrderToDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -30);
        String aoOrderFromDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        System.out.println(aoOrderFromDate);
        System.out.println(aoOrderToDate);
    }

    @Test
    public void date9() throws ParseException {
        Date date1 = DateUtils.parseDate("2020-01-07 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtils.parseDate("2020-01-07 00:00:10", "yyyy-MM-dd HH:mm:ss");

        long overTime = (date2.getTime() - date1.getTime()) / 1000;
        System.out.println(overTime);
    }

    @Test
    public void date10() {
        long overTime = Duration.between(LocalDateTime.parse("2020-01-01T00:00:00", DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-01-01T01:01:01", DateTimeFormatter.ISO_DATE_TIME)).toMillis() / 1000;
        // 小于1分钟
        if (overTime < 60) {
            System.out.println(overTime + "秒");
        }
        // 小于1小时
        else if (overTime < 60 * 60) {
            long min = overTime / 60;
            long sec = overTime % 60;
            System.out.println(min + "分" + sec + "秒");
        }
        // 小于1天
        else if (overTime < 24 * 60 * 60) {
            long hour = overTime / (60 * 60);
            long residue = overTime % (60 * 60);
            // 余数小于1分钟
            if (residue < 60) {
                System.out.println(hour + "时0分" + residue + "秒");
            }
            // 余数大于1分钟
            else {
                long min = residue / 60;
                long sec = residue % 60;
                System.out.println(hour + "时" + min + "分" + sec + "秒");
            }
        }
        // 大于1天
        else {
            long day = overTime / (24 * 60 * 60);
            long residue = overTime % (24 * 60 * 60);
            // 余数小于1分钟
            if (residue < 60) {
                System.out.println(day + "天0时0分" + residue + "秒");
            }
            // 余数小于1小时
            else if (residue < 60 * 60) {
                long min = residue / 60;
                residue = residue % 60;
                System.out.println(day + "天0时" + min + "分" + residue + "秒");
            }
            // 余数大于1小时
            else {
                long hour = residue / (60 * 60);
                residue = residue % (60 * 60);
                // 小于1分
                if (residue < 60) {
                    System.out.println(day + "天" + hour + "时0分" + residue + "秒");
                }
                // 大于1分
                else {
                    long min = residue / 60;
                    residue = residue % 60;
                    System.out.println(day + "天" + hour + "时" + min + "分" + residue + "秒");
                }
            }
        }
    }

    @Test
    public void date11() {
        long overTime = Duration.between(LocalDateTime.parse("2020-01-01T00:00:00", DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-01-01T00:00:01", DateTimeFormatter.ISO_DATE_TIME)).toMillis() / 1000;
        System.out.println(this.secondToTime(overTime));

        System.out.println(this.ms2dhms(overTime));
    }

    @Test
    public void date12() {
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond());
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());

    }

    private String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days) {
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        } else {
            return hours + "小时" + minutes + "分" + second + "秒";
        }
    }

    private String ms2dhms(long secondCount) {
        String retval;

        long days = secondCount / (60 * 60 * 24);
        long hours = (secondCount % (60 * 60 * 24)) / (60 * 60);
        long minutes = (secondCount % (60 * 60)) / 60;
        long seconds = secondCount % 60;

        if (days > 0) {
            retval = days + "d" + hours + "h" + minutes + "m" + seconds + "s";
        } else if (hours > 0) {
            retval = hours + "h" + minutes + "m" + seconds + "s";
        } else if (minutes > 0) {
            retval = minutes + "m" + seconds + "s";
        } else {
            retval = seconds + "s";
        }

        return retval;
    }

    @Test
    public void json() throws InvocationTargetException, IllegalAccessException {
        Notify notify = new Notify();
        NotifyDetail detail = new NotifyDetail();
        detail.setMessage("哈哈哈哈");
        notify.setDetail(detail);

        Gson gson = new Gson();
        String json = gson.toJson(notify);
        System.out.println(json);

        Notify jsonObj = gson.fromJson(json, Notify.class);
//        NotifyDetail notifyDetail = (NotifyDetail) jsonObj.getDetail();
        NotifyDetail notifyDetail = gson.fromJson(jsonObj.getDetail().toString(), NotifyDetail.class);
        System.out.println(notifyDetail.getMessage());

        NotifyDetail notifyDetail1 = new NotifyDetail();
        BeanUtils.populate(notifyDetail1, (Map)jsonObj.getDetail());
        System.out.println(notifyDetail1);
    }

    @Test
    public void bigDecimal3() {
        BigDecimal bigDecimal = new BigDecimal("3.14");
        String string = bigDecimal.toString();
        System.out.println(string);
    }

    @Test
    public void hasMap() {
        Map<String, String> map = new HashMap<>();
        System.out.println(1 << 4);
    }

    @Test
    public void cas() {
        AtomicInteger integer = new AtomicInteger(1);
        int value = integer.get();
        System.out.println(value);
        System.out.println(integer.compareAndSet(1, 2));
    }

    @Test
    public void nioBuffer() {
        // 获取缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 写入缓冲区
        String content = "abcdefghijklmnopqrstuvwxyz";
        buffer.put(content.getBytes());
        byte[] dst = new byte[buffer.position()];
        // 切换读模式
        buffer.flip();
        // 从缓冲区读取数据
        buffer.get(dst, 0, dst.length);
        System.out.println(new String(dst));
        // 清空缓冲区(数据并未被清空)
        buffer.clear();
    }

    @Test
    public void nioChannel() throws IOException, InterruptedException {
        long start = System.currentTimeMillis();

        String intPath = "/Users/yudequan/Downloads/哈哈/1993蜜桃成熟时HD1080P粤语中字无删减高清修复版.mp4/1993蜜桃成熟时HD1080P粤语中字无删减高清修复版.mp4";
        String outPath = "/Users/yudequan/Downloads/哈哈/1993蜜桃成熟时HD1080P粤语中字无删减高清修复版.mp4/1.mp4";

        FileChannel inChannel = FileChannel.open(Paths.get(intPath), READ);
        FileChannel outChannel = FileChannel.open(Paths.get(outPath), READ, WRITE, CREATE);

        MappedByteBuffer inMapBuf = inChannel.map(READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuf = outChannel.map(READ_WRITE, 0, inChannel.size());

        byte[] dst = new byte[inMapBuf.limit()];
        inMapBuf.get(dst);
        outMapBuf.put(dst);

        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void forLoop() {
        long start = System.currentTimeMillis();
        List<Order> orderList = new ArrayList<>();
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            orderList.add(new Order(i, "APP"));
            passengerList.add(new Passenger(i, i, "张三" + i));
        }

        List<OrderPassenger2> orderPassengerList = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            for (Order order : orderList) {
                if (passenger.getOrderId().equals(order.getId())) {
                    orderPassengerList.add(new OrderPassenger2(order.getId(), order.getSource(), passenger.getName()));
                }
            }
        }
        System.out.println(orderPassengerList.size());

        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }

    @Test
    public void optimizeForLoopJava8() {
        long start = System.currentTimeMillis();
        List<Order> orderList = new ArrayList<>();
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            orderList.add(new Order(i, "APP"));
            passengerList.add(new Passenger(i, i, "张三" + i));
        }

        Map<Integer, Passenger> passengerMap = passengerList.stream().collect(Collectors.toMap(Passenger::getOrderId, p -> p));

        List<OrderPassenger2> orderPassengerList = new ArrayList<>();
        orderList.forEach(o ->
            orderPassengerList.add(new OrderPassenger2(o.getId(), o.getSource(), passengerMap.get(o.getId()).getName())));
        System.out.println(orderPassengerList.size());

        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }

    @Test
    public void optimizeForLoopMap() {
        long start = System.currentTimeMillis();
        List<Order> orderList = new ArrayList<>();
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            orderList.add(new Order(i, "APP"));
            passengerList.add(new Passenger(i, i, "张三" + i));
        }

//        Map<Integer, Passenger> passengerMap = passengerList.stream().collect(Collectors.toMap(Passenger::getOrderId, p -> p));
        Map<Integer, Passenger> passengerMap = new HashMap<>();
        for (Passenger passenger : passengerList) {
            passengerMap.put(passenger.getOrderId(), passenger);
        }

        List<OrderPassenger2> orderPassengerList = new ArrayList<>();
//        orderList.forEach(o ->
//                orderPassengerList.add(new OrderPassenger2(o.getId(), o.getSource(), passengerMap.get(o.getId()).getName())));
        for (Order order : orderList) {
            orderPassengerList.add(new OrderPassenger2(order.getId(), order.getSource(), passengerMap.get(order.getId()).getName()));
        }

        System.out.println(orderPassengerList.size());

        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
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
    private transient String password;
//    @Expose(serialize = false, deserialize = false)
//    private String password;
}


//@Builder
//@Data
//class Animal {
//    private int id;
//}
//
//@EqualsAndHashCode(callSuper = true)
//@Data
//@Builder
//class Cat extends Animal {
//    private String name;
//}

@Builder
@Data
class OrderPassenger {
    private Integer orderId;
    private String name;
}

@Data
class Notify {
    private Object detail;
}

@Data
class NotifyDetail {
    private String message;
}