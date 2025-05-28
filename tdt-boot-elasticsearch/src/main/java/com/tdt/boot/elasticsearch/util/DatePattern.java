package com.tdt.boot.elasticsearch.util;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface DatePattern {
    String NORM_DATE_PATTERN = "yyyy-MM-dd";
    DateTimeFormatter NORM_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
    String NORM_TIME_PATTERN = "HH:mm:ss";
    DateTimeFormatter NORM_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    DateTimeFormatter NORM_DATETIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());
    String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter NORM_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
    String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    DateTimeFormatter NORM_DATETIME_MS_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.systemDefault());
    String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";
    DateTimeFormatter CHINESE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy年MM月dd日").withZone(ZoneId.systemDefault());
    String PURE_DATE_PATTERN = "yyyyMMdd";
    DateTimeFormatter PURE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.systemDefault());
    String PURE_TIME_PATTERN = "HHmmss";
    DateTimeFormatter PURE_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");
    String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";
    DateTimeFormatter PURE_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";
    DateTimeFormatter PURE_DATETIME_MS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
    DateTimeFormatter HTTP_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).withZone(ZoneId.of("GMT"));
    String JDK_DATETIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";
    DateTimeFormatter JDK_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    DateTimeFormatter UTC_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneOffset.UTC);
    String UTC_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    DateTimeFormatter UTC_WITH_ZONE_OFFSET_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").withZone(ZoneOffset.UTC);
    String UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    DateTimeFormatter UTC_MS_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);
    String UTC_MS_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    DateTimeFormatter UTC_MS_WITH_ZONE_OFFSET_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").withZone(ZoneOffset.UTC);
}
