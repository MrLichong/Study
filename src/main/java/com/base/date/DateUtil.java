//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static final String DEFAULT_PATTERN = "yyyyMMdd";

    private DateUtil() {
    }

    static String getStartDay(DateType dateType, LocalDate localDate, String pattern) {
        return getDateStartDay(dateType, localDate, pattern);
    }

    static String getStartDay(DateType dateType, Date date) {
        return getDateStartDay(dateType, dateToLocalDate(date), "yyyyMMdd");
    }

    static String getStartDay(DateType dateType, Date date, String pattern) {
        return getDateStartDay(dateType, dateToLocalDate(date), pattern);
    }

    static String getEndDay(DateType dateType, LocalDate localDate, String pattern) {
        return getDateEndDay(dateType, localDate, pattern);
    }

    static String getEndDay(DateType dateType, Date date) {
        return getDateEndDay(dateType, dateToLocalDate(date), "yyyyMMdd");
    }

    static String getEndDay(DateType dateType, Date date, String pattern) {
        return getDateEndDay(dateType, dateToLocalDate(date), pattern);
    }

    private static String getDateEndDay(DateType dateType, LocalDate localDate, String pattern) {
        String s = "";
        switch(dateType) {
        case THIS_WEEK:
            s = DateService.getStartDayOfWeek(localDate, pattern, 7);
            break;
        case LAST_WEEK:
            localDate = localDate.minusWeeks(1L);
            s = DateService.getStartDayOfWeek(localDate, pattern, 7);
            break;
        case LAST_LAST_WEEK:
            localDate = localDate.minusWeeks(2L);
            s = DateService.getStartDayOfWeek(localDate, pattern, 7);
            break;
        case THIS_MONTH:
            s = DateService.getEndDayOfMonth(localDate, pattern);
            break;
        case LAST_MONTH:
            localDate = localDate.minusMonths(1L);
            s = DateService.getEndDayOfMonth(localDate, pattern);
            break;
        case LAST_LAST_MONTH:
            localDate = localDate.minusMonths(2L);
            s = DateService.getEndDayOfMonth(localDate, pattern);
            break;
        case THIS_YEAR:
            s = DateService.getEndDayOfYear(localDate, pattern);
            break;
        case LAST_YEAR:
            localDate = localDate.minusYears(1L);
            s = DateService.getEndDayOfYear(localDate, pattern);
            break;
        case CUSTOM_WEEK:
            s = DateService.getStartDayOfWeek(localDate, pattern, 7);
            break;
        case CUSTOM_MONTH:
            s = DateService.getEndDayOfMonth(localDate, pattern);
            break;
        case CUSTOM_YEAR:
            s = DateService.getEndDayOfYear(localDate, pattern);
        }

        return s;
    }

    private static String getDateStartDay(DateType dateType, LocalDate localDate, String pattern) {
        String s = "";
        switch(dateType) {
        case THIS_WEEK:
            s = DateService.getStartDayOfWeek(localDate, pattern, 1);
            break;
        case LAST_WEEK:
            localDate = localDate.minusWeeks(1L);
            s = DateService.getStartDayOfWeek(localDate, pattern, 1);
            break;
        case LAST_LAST_WEEK:
            localDate = localDate.minusWeeks(2L);
            s = DateService.getStartDayOfWeek(localDate, pattern, 1);
            break;
        case THIS_MONTH:
            s = DateService.getStartDayOfMonth(localDate, pattern);
            break;
        case LAST_MONTH:
            localDate = localDate.minusMonths(1L);
            s = DateService.getStartDayOfMonth(localDate, pattern);
            break;
        case LAST_LAST_MONTH:
            localDate = localDate.minusMonths(2L);
            s = DateService.getStartDayOfMonth(localDate, pattern);
            break;
        case THIS_YEAR:
            s = DateService.getStartDayOfYear(localDate, pattern);
            break;
        case LAST_YEAR:
            localDate = localDate.minusYears(1L);
            s = DateService.getStartDayOfYear(localDate, pattern);
            break;
        case CUSTOM_WEEK:
            s = DateService.getStartDayOfWeek(localDate, pattern, 1);
            break;
        case CUSTOM_MONTH:
            s = DateService.getStartDayOfMonth(localDate, pattern);
            break;
        case CUSTOM_YEAR:
            s = DateService.getStartDayOfYear(localDate, pattern);
        }

        return s;
    }

    public static LocalDate dateToLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
}
