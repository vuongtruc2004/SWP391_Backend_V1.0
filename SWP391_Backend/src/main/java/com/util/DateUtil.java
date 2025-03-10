package com.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
public class DateUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Instant parseToInstant(String dateTimeString) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, FORMATTER);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Ho_Chi_Minh"));
        return zonedDateTime.toInstant();
    }

}
