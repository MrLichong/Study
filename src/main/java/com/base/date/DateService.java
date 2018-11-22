//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateService {
    public DateService() {
    }

    static String getEndDayOfYear(LocalDate now, String pattern) {
        return now.with(TemporalAdjusters.lastDayOfYear()).format(DateTimeFormatter.ofPattern(pattern));
    }

    static String getEndDayOfMonth(LocalDate now, String pattern) {
        return now.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(pattern));
    }

    static String getStartDayOfYear(LocalDate now, String pattern) {
        return now.with(TemporalAdjusters.firstDayOfYear()).format(DateTimeFormatter.ofPattern(pattern));
    }

    static String getStartDayOfMonth(LocalDate now, String pattern) {
        return now.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(pattern));
    }

    static String getStartDayOfWeek(LocalDate localDate, String pattern, int adjust) {
        int value = localDate.getDayOfWeek().getValue();
        if (value == 7) {
            localDate = localDate.minusWeeks(1L);
        }

        return localDate.with(WeekFields.of(Locale.CHINA).dayOfWeek(), (long)adjust).atStartOfDay(ZoneId.systemDefault()).plusDays(1L).format(DateTimeFormatter.ofPattern(pattern));
    }

    static List<String> getMonthStartDay(LocalDate localDate, String pattern) {
        int year = localDate.getYear();
        localDate = LocalDate.of(year, 1, 1);
        List<String> returnList = new ArrayList();

        for(int i = 1; i <= 12; ++i) {
            returnList.add(localDate.format(DateTimeFormatter.ofPattern(pattern)));
            localDate = localDate.plusMonths(1L);
        }

        return returnList;
    }

    static List<String> monthAllDay(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
        boolean leapYear = localDate.isLeapYear();
        int monthLength = localDate.getMonth().length(leapYear);
        int month = localDate.getMonth().getValue();
        int year = localDate.getYear();
        localDate = LocalDate.of(year, month, 1);
        List<String> returnList = new ArrayList();

        for(int i = 1; i <= monthLength; ++i) {
            returnList.add(localDate.format(dateTimeFormatter));
            localDate = localDate.plusDays(1L);
        }

        return returnList;
    }

    static List<String> weekAllDay(String startDay, DateTimeFormatter dateTimeFormatter) {
        LocalDate localStartDay = LocalDate.parse(startDay, dateTimeFormatter);
        List<String> returnList = new ArrayList();

        for(int i = 1; i <= 7; ++i) {
            returnList.add(localStartDay.format(dateTimeFormatter));
            localStartDay = localStartDay.plusDays(1L);
        }

        return returnList;
    }
}
