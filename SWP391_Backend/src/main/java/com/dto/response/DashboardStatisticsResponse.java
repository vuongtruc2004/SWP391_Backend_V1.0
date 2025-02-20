package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardStatisticsResponse {
    Long totalRevenue;
    Long todayRevenue;
    Long yesterdayRevenue;
    Long totalStudents;
    Long todayStudents;
    Long yesterdayStudents;
    Long totalOrders;
    Long todayOrders;
    Long yesterdayOrders;
}
