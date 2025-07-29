package com.example.dncinema.dto;

public interface StatisticsMemberDTO {
    Integer getMemberId();
    String getMemberName();
    Integer getTotalTickets();
    Double getTotalRevenue();
    Double getLoyaltyPoints();
}
