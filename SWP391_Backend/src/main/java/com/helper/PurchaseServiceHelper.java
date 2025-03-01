package com.helper;

import com.dto.request.PurchaseRequest;
import com.exception.custom.InvalidRequestInput;
import com.util.VnPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PurchaseServiceHelper {

    private final String FORMAT_PATTERN = "yyyyMMddHHmmss";
    private final SimpleDateFormat VN_PAY_DATE_FORMAT = new SimpleDateFormat(FORMAT_PATTERN);

    @Value("${vnpay.hash.secret}")
    private String secretKey;

    @Value("${vnpay.url}")
    private String initPaymentPrefixUrl;

    public String formatVnTime(Calendar calendar) {
        return VN_PAY_DATE_FORMAT.format(calendar.getTime());
    }

    public Instant parseVnTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                .withZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        return ZonedDateTime.parse(timeString, formatter).toInstant();
    }

    public String getOrderInfo(PurchaseRequest purchaseRequest) {
        return "Thanh toan don hang " + purchaseRequest.getCourseIds().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public String getTxnRef(PurchaseRequest purchaseRequest, Long userId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT_PATTERN));
        return timestamp + userId + purchaseRequest.getCourseIds().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    public String encodeWithHmacSHA512(String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hashBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return toHexString(hashBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new InvalidRequestInput("Error while encrypting data with HMAC-SHA512: " + e.getMessage());
        }
    }

    public String buildPaymentUrl(Map<String, String> params) {
        StringBuilder hashPayload = new StringBuilder();
        StringBuilder query = new StringBuilder();
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        Iterator<String> iterator = fieldNames.iterator();
        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            String fieldValue = params.get(fieldName);

            if (fieldValue != null && !fieldValue.isBlank()) {
                hashPayload
                        .append(fieldName)
                        .append("=")
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                query
                        .append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII))
                        .append("=")
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                if (iterator.hasNext()) {
                    hashPayload.append("&");
                    query.append("&");
                }
            }
        }
        String secureHash = encodeWithHmacSHA512(hashPayload.toString());
        query.append("&vnp_SecureHash=").append(secureHash);
        return initPaymentPrefixUrl + "?" + query;
    }

    public boolean verifyIpn(Map<String, String> params) {
        String requestSecureHash = params.get(VnPayUtil.VNP_SECURE_HASH);
        params.remove(VnPayUtil.VNP_SECURE_HASH);
        params.remove(VnPayUtil.VNP_SECURE_HASH_TYPE);

        StringBuilder hashPayload = new StringBuilder();
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);
        Iterator<String> iterator = fieldNames.iterator();

        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            String fieldValue = params.get(fieldName);
            if (fieldValue != null && !fieldValue.isBlank()) {
                hashPayload
                        .append(fieldName)
                        .append("=")
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                if (iterator.hasNext()) {
                    hashPayload.append("&");
                }
            }
        }
        String secureHash = encodeWithHmacSHA512(hashPayload.toString());
        return secureHash.equals(requestSecureHash);
    }

    public String getIpAddress(HttpServletRequest httpServletRequest) {
        String xForwardedFor = httpServletRequest.getHeader("X-Forwarded-For");
        if (xForwardedFor == null) {
            xForwardedFor = httpServletRequest.getRemoteAddr();
            if (xForwardedFor == null) {
                xForwardedFor = "127.0.0.1";
            }
            return xForwardedFor;
        }
        return xForwardedFor.split(",")[0].trim();
    }

    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
