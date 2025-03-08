package com.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Instant parseToInstant(String dateTimeString) {
        try {
            if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
                throw new IllegalArgumentException("Chuỗi ngày giờ không hợp lệ.");
            }
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, FORMATTER);
            return localDateTime.toInstant(ZoneOffset.UTC);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Lỗi định dạng ngày giờ: " + dateTimeString, e);
        }
    }
}
