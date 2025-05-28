package com.tdt.boot.elasticsearch.util;


import com.tdt.boot.elasticsearch.constant.StatisticsOrderConst;
import com.tdt.boot.elasticsearch.domain.CountSearchEntity;
import com.tdt.boot.elasticsearch.domain.StatisticsOrderSearchEntity;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * @description：时间通用类
 */
public class DateUtil extends PDateUtil {

    public static final String YTD_DATETIME = "yyyyMMdd";
    public static final String YEARS_DATETIME = "yyyyMM";
    public static final String YEARS_DATETIME_STR = "yyyy-MM";
    public static final Integer YEARS_DATETIME_LONG = 10;




    /**
     * LocalDateTime转毫秒时间戳
     *
     * @param localDateTime LocalDateTime
     * @return 时间戳
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zoneId).toInstant();
            return instant.toEpochMilli();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp 时间戳
     * @return LocalDateTime
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date转LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date转LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDate dateToLocalDate(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * localDate转String
     *
     * @param localDate
     * @return
     */
    public static String localDateFormat(LocalDate localDate, String format) {
        try {
            return localDate.format(DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断传入日期是否是月最后一天
     *
     * @param date 日期
     * @return 返回是否是月最后一天
     */
    public static Boolean monthEndDate(String date) {
        LocalDate today = strToLocalDate(date);
        if (today == null) {
            return false;
        }
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        return Period.between(today, lastDayOfMonth).getDays() == 0;
    }


    /**
     * /**
     * 获取根据日期获取月最后一天
     *
     * @param date 日期
     * @return int类型的月最后一天
     */
    public static int getMonthEndData(Date date) {
        if (date == null) {
            return 0;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return Objects.requireNonNull(dateToLocalDateTime(dates)).getDayOfMonth();
    }

    /**
     * 获取月初\月尾
     *
     * @param date 入参
     * @return StartAndEndDateDTO 封装类型的月初、月尾时间戳
     */
    public static CountSearchEntity monthStartAndDate(String date) {
        LocalDate dateTime = strToLocalDate(date);
        //获取月初
        LocalDate monthOfFirstDate = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        //获取月末
        LocalDate monthOfLastDate = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        if (monthOfFirstDate == null || monthOfLastDate == null) {
            return null;
        }
        String todayStart = monthOfFirstDate.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        String todayEnd = monthOfLastDate.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        return CountSearchEntity.builder()
                .startDate(todayStart)
                .endDate(todayEnd)
                .build();
    }

    /**
     * 获取入参日期天的开始时间、月初开始时间
     *
     * @param date 日期
     * @return StartAndEndDateDTO对象的当天的开始、结束时间时间戳
     */
    public static CountSearchEntity searchStartAndDate(String date) {
        LocalDate dateTime = strToLocalDate(date);
        LocalDate monthOfFirstDate = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        String todayStart = monthOfFirstDate.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        String endDateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        return CountSearchEntity.builder()
                .startDate(todayStart)
                .endDate(endDateTime)
                .build();
    }

    /**
     * long 类型转Date
     *
     * @param date 时间戳
     * @return Date
     */
    public static String longToDate(Long date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(PATTERN_DATETIME).format(date);
    }

    /**
     * String 转Date
     *
     * @param strDate String类型的日期
     * @return Date类型日期 如果只有年月默认返回：月首日；例如入参：2021-06；返回：2021-06-01
     */
    public static Date strToDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        int num = 7;
        if (strDate.length() == num) {
            strDate = strDate.concat("-01");

        }
        return DateUtil.parse(strDate, PATTERN_DATE);
    }

    /**
     * 传入的String日期是否当月
     *
     * @param date 日期字符串
     * @return 是否当月
     */
    public static Boolean isCurrentMonth(String date) {
        LocalDate formatDate = LocalDate.now();
        String localDate = formatDate.format(DateTimeFormatter.ofPattern(YEARS_DATETIME_STR));
        return localDate.equals(date.length() == 7 ? date : date.substring(0, 7));
    }

    /**
     * str转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate strToLocalDate(String date) {
        if (!date.contains(StatisticsOrderConst.SMALL_ACROSS)) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(YTD_DATETIME));
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_DATE));
    }


    /**
     * long 转LocalDate
     *
     * @param timestamp 时间戳
     * @return LocalDate
     */
    public static LocalDate longToLocalDate(Long timestamp) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取年初、当前时间的时间戳
     *
     * @return
     */
    public static CountSearchEntity yearStartAndDate() {
        //当前时间
        LocalDate yearEndDay = LocalDate.now();
        //获取年初
        LocalDate yearStarDay = yearEndDay.with(TemporalAdjusters.firstDayOfYear());
        return CountSearchEntity.builder()
                .startDate(yearStarDay.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME)))
                .endDate(yearEndDay.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME)))
                .build();
    }

    /**
     * 获取年初、入参月尾
     *
     * @return
     */
    public static CountSearchEntity yearStartAndNowDate(String searchTime) {
        LocalDate localDateTime = strToLocalDate(searchTime);
        LocalDateTime startTime = localDateTime.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay();
        LocalDateTime monthEnd = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay();
        String todayStart = startTime.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        String endDateTime = monthEnd.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        return CountSearchEntity.builder()
                .startDate(todayStart)
                .endDate(endDateTime)
                .build();
    }

    public static CountSearchEntity beforeYearStartAndDate(String beforeDate) {
        LocalDate localDateTime = strToLocalDate(beforeDate);
        LocalDateTime startTime = localDateTime.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay();
        LocalDateTime endTime = LocalDateTime.of(localDateTime.with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
        String todayStart = startTime.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        String endDateTime = endTime.format(DateTimeFormatter.ofPattern(DateUtil.YTD_DATETIME));
        return CountSearchEntity.builder()
                .startDate(todayStart)
                .endDate(endDateTime)
                .build();
    }

    /**
     * @Description 获取导出Excel文件名日期前缀
     * @param dto
     * @return java.lang.String
     * @author LZW
     * @date 2021/12/16 15:38
     */
    public static String getFileNameDate(StatisticsOrderSearchEntity dto){
        String month = "month";
        String date = dto.getSearchTime();
        if (month.equals(dto.getSearchTimeType())){
            date = dto.getSearchTime().substring(0,7);
        }else if (dto.getStartDate() != null && dto.getEndDate() != null){
            date = dto.getStartDate()+"~"+dto.getEndDate();
        }
        return date;
    }
}
