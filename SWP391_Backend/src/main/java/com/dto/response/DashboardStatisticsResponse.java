package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardStatisticsResponse {
    Long todayRevenue;
    Long yesterdayRevenue;
    Long todayStudents;
    Long yesterdayStudents;
    Long todayOrders;
    Long yesterdayOrders;
    Long revenue;
    Long orders;
    Long students;
}
