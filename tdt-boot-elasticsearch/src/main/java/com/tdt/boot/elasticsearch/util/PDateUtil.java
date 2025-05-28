
package com.tdt.boot.elasticsearch.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import org.springframework.lang.Nullable;

public class PDateUtil {
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER;
    public static final DateTimeFormatter DATE_FORMATTER;
    public static final DateTimeFormatter TIME_FORMATTER;
    private static final TemporalQuery<Instant> INSTANT_QUERY;
    public static final LocalDate EPOCH;

    public PDateUtil() {
    }

    public static Date now() {
        return new Date();
    }

    public static String today() {
        return format(new Date(), "yyyyMMdd");
    }

    public static Integer hour() {
        return Integer.valueOf(format(new Date(), "HH"));
    }

    public static Date plusYears(Date date, int yearsToAdd) {
        return plusAtUtc(date, Period.ofYears(yearsToAdd));
    }

    public static Date plusMonths(Date date, int monthsToAdd) {
        return plusAtUtc(date, Period.ofMonths(monthsToAdd));
    }

    public static Date plusWeeks(Date date, int weeksToAdd) {
        return plus(date, Period.ofWeeks(weeksToAdd));
    }

    public static Date plusDays(Date date, long daysToAdd) {
        return plus(date, Duration.ofDays(daysToAdd));
    }

    public static Date plusHours(Date date, long hoursToAdd) {
        return plus(date, Duration.ofHours(hoursToAdd));
    }

    public static Date plusMinutes(Date date, long minutesToAdd) {
        return plus(date, Duration.ofMinutes(minutesToAdd));
    }

    public static Date plusSeconds(Date date, long secondsToAdd) {
        return plus(date, Duration.ofSeconds(secondsToAdd));
    }

    public static Date plusMillis(Date date, long millisToAdd) {
        return plus(date, Duration.ofMillis(millisToAdd));
    }

    public static Date plusNanos(Date date, long nanosToAdd) {
        return plus(date, Duration.ofNanos(nanosToAdd));
    }

    public static Date plusAtUtc(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant().atZone(ZoneOffset.UTC).plus(amount).toInstant();
        return Date.from(instant);
    }

    public static Date plus(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant().plus(amount);
        return Date.from(instant);
    }

    public static Date minusYears(Date date, int years) {
        return minusAtUtc(date, Period.ofYears(years));
    }

    public static Date minusMonths(Date date, int months) {
        return minusAtUtc(date, Period.ofMonths(months));
    }

    public static Date minusWeeks(Date date, int weeks) {
        return minus(date, Period.ofWeeks(weeks));
    }

    public static Date minusDays(Date date, long days) {
        return minus(date, Duration.ofDays(days));
    }

    public static Date minusHours(Date date, long hours) {
        return minus(date, Duration.ofHours(hours));
    }

    public static Date minusMinutes(Date date, long minutes) {
        return minus(date, Duration.ofMinutes(minutes));
    }

    public static Date minusSeconds(Date date, long seconds) {
        return minus(date, Duration.ofSeconds(seconds));
    }

    public static Date minusMillis(Date date, long millis) {
        return minus(date, Duration.ofMillis(millis));
    }

    public static Date minusNanos(Date date, long nanos) {
        return minus(date, Duration.ofNanos(nanos));
    }

    public static Date minusAtUtc(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant().atZone(ZoneOffset.UTC).minus(amount).toInstant();
        return Date.from(instant);
    }

    public static Date minus(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant().minus(amount);
        return Date.from(instant);
    }

    @Nullable
    public static String formatDateTime(@Nullable Date date) {
        return format(date, DATETIME_FORMATTER);
    }

    @Nullable
    public static String formatDate(@Nullable Date date) {
        return format(date, DATE_FORMATTER);
    }

    @Nullable
    public static String formatTime(@Nullable Date date) {
        return format(date, TIME_FORMATTER);
    }

    @Nullable
    public static String format(@Nullable Date date, String pattern) {
        return format(date, DateTimeFormatter.ofPattern(pattern));
    }

    @Nullable
    public static String format(@Nullable Date date, DateTimeFormatter formatter) {
        return date == null ? null : format(date.toInstant(), formatter);
    }

    @Nullable
    public static String format(Instant instant, DateTimeFormatter formatter) {
        ZoneId zone = formatter.getZone();
        return zone == null ? formatter.withZone(ZoneId.systemDefault()).format(instant) : formatter.format(instant);
    }

    public static String formatDateTime(TemporalAccessor temporal) {
        return DATETIME_FORMATTER.format(temporal);
    }

    public static String formatDate(TemporalAccessor temporal) {
        return DATE_FORMATTER.format(temporal);
    }

    public static String formatTime(TemporalAccessor temporal) {
        return TIME_FORMATTER.format(temporal);
    }

    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(temporal);
    }

    public static Date parse(String dateStr, String pattern) {
        return parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date parse(String dateStr, DateTimeFormatter format) {
        if (format.getZone() == null) {
            format = format.withZone(ZoneId.systemDefault());
        }

        Instant instant = (Instant)format.parse(dateStr, instantQuery());
        return Date.from(instant);
    }

    public static <T> T parse(String dateStr, String pattern, TemporalQuery<T> query) {
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).parse(dateStr, query);
    }

    public static Instant toInstant(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static LocalDateTime toDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime toStartOfDay(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static LocalDateTime toEndOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    public static LocalDateTime toDateTime(Date date) {
        return toDateTime(date.toInstant());
    }

    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(toInstant(dateTime));
    }

    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Calendar toCalendar(final LocalDateTime localDateTime) {
        return GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
    }

    public static long toMilliseconds(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long toMilliseconds(LocalDate localDate) {
        return toMilliseconds(localDate.atStartOfDay());
    }

    public static LocalDateTime fromCalendar(final Calendar calendar) {
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zid);
    }

    public static LocalDateTime fromInstant(final Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime fromDate(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime fromMilliseconds(final long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    public static boolean isAfter(ChronoLocalDateTime<?> self, ChronoLocalDateTime<?> other) {
        return self.isAfter(other);
    }

    public static boolean isBefore(ChronoLocalDateTime<?> self, ChronoLocalDateTime<?> other) {
        return self.isBefore(other);
    }

    public static boolean isEqual(ChronoLocalDateTime<?> self, ChronoLocalDateTime<?> other) {
        return self.isEqual(other);
    }

    public static Duration between(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive);
    }

    public static Period between(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    public static Duration between(Date startDate, Date endDate) {
        return Duration.between(startDate.toInstant(), endDate.toInstant());
    }

    public static String secondToTime(Long second) {
        if (second != null && second != 0L) {
            long days = second / 86400L;
            second = second % 86400L;
            long hours = second / 3600L;
            second = second % 3600L;
            long minutes = second / 60L;
            second = second % 60L;
            return days > 0L ? String.format("{}天{}小时{}分{}秒", new Object[]{days, hours, minutes, second}) : String.format("{}小时{}分{}秒", new Object[]{hours, minutes, second});
        } else {
            return "";
        }
    }

    public static LocalDateTime parseDateTime(CharSequence dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parseDateTime(dateStr, formatter);
    }

    public static LocalDateTime parseDateTime(CharSequence dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static LocalDateTime parseDateTime(CharSequence dateStr) {
        return parseDateTime(dateStr, DATETIME_FORMATTER);
    }

    public static LocalDateTime parseDateTime(@Nullable CharSequence text, @Nullable String[] parsePatterns) {
        return parseDateTime(text, (Locale)null, parsePatterns);
    }

    public static LocalDateTime parseDateTime(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns) {
        return (LocalDateTime)parse(text, locale, parsePatterns, LocalDateTime::from);
    }

    public static LocalDate parseDate(CharSequence dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parseDate(dateStr, formatter);
    }

    public static LocalDate parseDate(CharSequence dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static LocalDate parseDate(CharSequence dateStr) {
        return parseDate(dateStr, DATE_FORMATTER);
    }

    public static LocalDate parseDate(@Nullable CharSequence text, @Nullable String[] parsePatterns) {
        return parseDate(text, (Locale)null, parsePatterns);
    }

    public static LocalDate parseDate(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns) {
        return (LocalDate)parse(text, locale, parsePatterns, LocalDate::from);
    }

    public static LocalTime parseTime(CharSequence dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parseTime(dateStr, formatter);
    }

    public static LocalTime parseTime(CharSequence dateStr, DateTimeFormatter formatter) {
        return LocalTime.parse(dateStr, formatter);
    }

    public static LocalTime parseTime(CharSequence dateStr) {
        return parseTime(dateStr, TIME_FORMATTER);
    }

    public static LocalTime parseTime(@Nullable CharSequence text, @Nullable String[] parsePatterns) {
        return parseTime(text, (Locale)null, parsePatterns);
    }

    public static LocalTime parseTime(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns) {
        return (LocalTime)parse(text, locale, parsePatterns, LocalTime::from);
    }

    public static <T> T parse(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns, TemporalQuery<T> query) {
        if (text != null && parsePatterns != null) {
            Locale lcl = locale == null ? Locale.getDefault() : locale;
            ZoneId systemZone = ZoneId.systemDefault();
            DateTimeFormatter formatter = null;
            String[] var7 = parsePatterns;
            int var8 = parsePatterns.length;
            int var9 = 0;

            while(var9 < var8) {
                String parsePattern = var7[var9];
                formatter = DateTimeFormatter.ofPattern(parsePattern, lcl).withZone(systemZone);

                try {
                    return formatter.parse(text, query);
                } catch (DateTimeParseException var12) {
                    ++var9;
                }
            }

            throw new DateTimeParseException("Unable to parse the date: " + text, text, -1);
        } else {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
    }

    public static TemporalQuery<Instant> instantQuery() {
        return INSTANT_QUERY;
    }

    static {
        DATETIME_FORMATTER = DatePattern.NORM_DATETIME_FORMAT;
        DATE_FORMATTER = DatePattern.NORM_DATE_FORMAT;
        TIME_FORMATTER = DatePattern.NORM_TIME_FORMAT;
        INSTANT_QUERY = new TemporalQuery<Instant>() {
            @Override
            @Nullable
            public Instant queryFrom(TemporalAccessor temporal) {
                if (temporal.isSupported(ChronoField.INSTANT_SECONDS) && temporal.isSupported(ChronoField.NANO_OF_SECOND)) {
                    long instantSecs = temporal.getLong(ChronoField.INSTANT_SECONDS);
                    int nanoOfSecond = temporal.get(ChronoField.NANO_OF_SECOND);
                    return Instant.ofEpochSecond(instantSecs, (long)nanoOfSecond);
                } else {
                    ZoneId zoneId = (ZoneId)temporal.query(TemporalQueries.zoneId());
                    Objects.requireNonNull(zoneId, "Unable to obtain Instant from TemporalAccessor: zoneId is null.");
                    if (temporal.isSupported(ChronoField.NANO_OF_DAY)) {
                        return LocalTime.ofNanoOfDay(temporal.getLong(ChronoField.NANO_OF_DAY)).atDate(PDateUtil.EPOCH).atZone(zoneId).toInstant();
                    } else {
                        return temporal.isSupported(ChronoField.EPOCH_DAY) ? LocalDate.ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY)).atStartOfDay().atZone(zoneId).toInstant() : null;
                    }
                }
            }

            public String toString() {
                return "Instant";
            }
        };
        EPOCH = LocalDate.of(1970, 1, 1);
    }
}
