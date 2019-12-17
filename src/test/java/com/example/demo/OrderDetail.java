package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dequan.yu on 2019/12/13.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {
    private String orderNo;
    // 订单原始价格，展示在航司页面上的价格，所有乘客之和
    private BigDecimal orderAmount;
    private String orderStatus;
    private String depCode;
    private String arrCode;
    private String airlineCode;
    private String flightNo;
    private String cabinCode;
    private String flightDate;
    private String takeOffTime;
    private String arrivalTime;
    private String issueTime;
    private List<OrderPassenger> orderPassengerList;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class OrderPassenger {
        private String passengerId;
        private String passengerName;
        private String passengerType;
        private String birthDate;
        private String certNo;
        private String certType;
        private String pnr;
        private String ticketNo;
        private BigDecimal ticketFare;
        private BigDecimal airportTax;
        private BigDecimal fuelTax;
        private BigDecimal otherTax;
    }
}
