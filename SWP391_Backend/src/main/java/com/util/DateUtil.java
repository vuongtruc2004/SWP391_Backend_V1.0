package com.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static boolean isIso8601(String dateTimeString) {
        try {
            ZonedDateTime.parse(dateTimeString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static Instant parseToInstant(String dateTimeString) {
        if (isIso8601(dateTimeString)) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeString).withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
            return zonedDateTime.toInstant();
        } else {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, FORMATTER);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Ho_Chi_Minh"));
            return zonedDateTime.toInstant();
        }
    }

}