//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date;


import com.base.date.model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DateHelper {
    public DateHelper() {
    }

    public static DateEnhance getDateEnhance() {
        return getDateEnhance(new Date(), (String)null);
    }

    public static DateEnhance getDateEnhance(Date date) {
        return getDateEnhance(date, (String)null);
    }

    public static DateEnhance getDateEnhance(String pattern) {
        return getDateEnhance(new Date(), pattern);
    }

    public static DateEnhance getDateEnhance(Date date, String pattern) {
        DateEnhance de = new DateEnhance();

        try {
            LocalDate localDate;
            if (date == null) {
                date = new Date();
                localDate = LocalDate.now();
            } else {
                localDate = DateUtil.dateToLocalDate(date);
            }

            if (pattern == null || "".equals(pattern)) {
                pattern = "yyyyMMdd";
            }

            DateFormat df = new SimpleDateFormat(pattern);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            de.setDate(date);
            Year year = buildYear(DateType.THIS_YEAR, df, localDate, pattern);
            de.setYear(year);
            Year lastYear = buildYear(DateType.LAST_YEAR, df, localDate, pattern);
            de.setLastYear(lastYear);
            Month month = buildMonth(DateType.THIS_MONTH, df, localDate, pattern, dateTimeFormatter);
            month.setCurrentDate(date);
            de.setMonth(month);
            Month lastMonth = buildMonth(DateType.LAST_MONTH, df, localDate, pattern, dateTimeFormatter);
            de.setLastMonth(lastMonth);
            Month last2Month = buildMonth(DateType.LAST_LAST_MONTH, df, localDate, pattern, dateTimeFormatter);
            de.setLast2Month(last2Month);
            Week week = buildWeek(DateType.THIS_WEEK, df, localDate, pattern, dateTimeFormatter);
            week.setCurrentDate(date);
            de.setWeek(week);
            Week lastWeek = buildWeek(DateType.LAST_WEEK, df, localDate, pattern, dateTimeFormatter);
            de.setLastWeek(lastWeek);
            Week last2Week = buildWeek(DateType.LAST_LAST_WEEK, df, localDate, pattern, dateTimeFormatter);
            de.setLast2Week(last2Week);
            Day day = buildDay(localDate, dateTimeFormatter, df);
            day.setCurrentDate(date);
            de.setDay(day);
            Quarter quarter = buildQuarter(localDate, de, pattern);
            de.setQuarter(quarter);
            return de;
        } catch (Exception var16) {
            var16.printStackTrace();
            throw new RuntimeException(var16);
        }
    }

    public static String getChineseDayOfWeek(int dayOfWeek) {
        switch(dayOfWeek) {
        case 1:
            return "周一";
        case 2:
            return "周二";
        case 3:
            return "周三";
        case 4:
            return "周四";
        case 5:
            return "周五";
        case 6:
            return "周六";
        case 7:
            return "周日";
        default:
            throw new RuntimeException("参数错误：dayOfWeek：" + dayOfWeek);
        }
    }

    private static Quarter buildQuarter(LocalDate localDate, DateEnhance de, String pattern) throws Exception {
        int monthValue = localDate.getMonth().getValue();
        Month month1;
        Month month2;
        Month month3;
        if (monthValue >= 1 && monthValue <= 3) {
            month1 = de.getMonth(1, pattern);
            month2 = de.getMonth(2, pattern);
            month3 = de.getMonth(3, pattern);
            return buildQuarter(month1, month2, month3);
        } else if (monthValue >= 4 && monthValue <= 6) {
            month1 = de.getMonth(4, pattern);
            month2 = de.getMonth(5, pattern);
            month3 = de.getMonth(6, pattern);
            return buildQuarter(month1, month2, month3);
        } else if (monthValue >= 7 && monthValue <= 9) {
            month1 = de.getMonth(7, pattern);
            month2 = de.getMonth(8, pattern);
            month3 = de.getMonth(9, pattern);
            return buildQuarter(month1, month2, month3);
        } else {
            month1 = de.getMonth(10, pattern);
            month2 = de.getMonth(11, pattern);
            month3 = de.getMonth(12, pattern);
            return buildQuarter(month1, month2, month3);
        }
    }

    private static Quarter buildQuarter(Month month1, Month month2, Month month3) {
        String startDay = null;
        Long startDay13Time = null;
        Long startDay10Time = null;
        String lastDay = null;
        Long lastDay13Time = null;
        Long lastDay10Time = null;
        List<String> days = new ArrayList();
        List<Long> days13Time = new ArrayList();
        List<Long> days10Time = new ArrayList();
        days.addAll(month1.getDays());
        days.addAll(month2.getDays());
        days.addAll(month3.getDays());
        days10Time.addAll(month1.getDays10Time());
        days10Time.addAll(month2.getDays10Time());
        days10Time.addAll(month3.getDays10Time());
        days13Time.addAll(month1.getDays13Time());
        days13Time.addAll(month2.getDays13Time());
        days13Time.addAll(month3.getDays13Time());
        startDay = month1.getStartDay();
        startDay10Time = month1.getStartDay10Time();
        startDay13Time = month1.getStartDay13Time();
        lastDay = month3.getLastDay();
        lastDay10Time = month3.getLastDay10Time();
        lastDay13Time = month3.getLastDay13Time();
        Quarter quarter = new Quarter();
        quarter.setDays(days);
        quarter.setDays10Time(days10Time);
        quarter.setDays13Time(days13Time);
        quarter.setStartDay(startDay);
        quarter.setStartDay10Time(startDay10Time);
        quarter.setStartDay13Time(startDay13Time);
        quarter.setLastDay(lastDay);
        quarter.setLastDay10Time(lastDay10Time);
        quarter.setLastDay13Time(lastDay13Time);
        return quarter;
    }

    public static Day buildDay(LocalDate localDate, DateTimeFormatter dateTimeFormatter, DateFormat df) throws Exception {
        Day day = new Day();
        String thisDay = localDate.format(dateTimeFormatter);
        long time = df.parse(thisDay).getTime();
        day.setDate(thisDay);
        day.setDate13Time(time);
        day.setDate10Time(time / 1000L);
        day.setDayOfWeek(localDate.getDayOfWeek().getValue());
        day.setDayOfMonth(localDate.getDayOfMonth());
        day.setDayOfYear(localDate.getDayOfYear());
        day.setLocalDate(localDate);
        return day;
    }

    public static Week buildWeek(DateType dateType, DateFormat df, LocalDate localDate, String pattern, DateTimeFormatter dateTimeFormatter) throws Exception {
        Week week = new Week();
        String startDay = DateUtil.getStartDay(dateType, localDate, pattern);
        long startTime = df.parse(startDay).getTime();
        week.setStartDay(startDay);
        week.setStartDay10Time(startTime / 1000L);
        week.setStartDay13Time(startTime);
        String endDay = DateUtil.getEndDay(dateType, localDate, pattern);
        long entTime = df.parse(endDay).getTime();
        week.setLastDay(endDay);
        week.setLastDay10Time(entTime / 1000L);
        week.setLastDay13Time(entTime);
        List<String> weekAllDay = DateService.weekAllDay(startDay, dateTimeFormatter);
        week.setDays(weekAllDay);
        Map<String, List<Long>> timeStamp = getTimeStamp(weekAllDay, df);
        week.setDays13Time((List)timeStamp.get("13"));
        week.setDays10Time((List)timeStamp.get("10"));
        week.setLocalDate(localDate);
        return week;
    }

    public static Month buildMonth(DateType dateType, DateFormat df, LocalDate localDate, String pattern, DateTimeFormatter dateTimeFormatter) throws Exception {
        Month month = new Month();
        String startDay = DateUtil.getStartDay(dateType, localDate, pattern);
        long startTime = df.parse(startDay).getTime();
        month.setStartDay(startDay);
        month.setStartDay10Time(startTime / 1000L);
        month.setStartDay13Time(startTime);
        String endDay = DateUtil.getEndDay(dateType, localDate, pattern);
        long entTime = df.parse(endDay).getTime();
        month.setLastDay(endDay);
        month.setLastDay10Time(entTime / 1000L);
        month.setLastDay13Time(entTime);
        if (dateType == DateType.LAST_MONTH) {
            localDate = localDate.minusMonths(1L);
        } else if (dateType == DateType.LAST_LAST_MONTH) {
            localDate = localDate.minusMonths(2L);
        }

        List<String> monthAllDay = DateService.monthAllDay(localDate, dateTimeFormatter);
        month.setDays(monthAllDay);
        Map<String, List<Long>> timeStamp = getTimeStamp(monthAllDay, df);
        month.setDays13Time((List)timeStamp.get("13"));
        month.setDays10Time((List)timeStamp.get("10"));
        month.setLocalDate(localDate);
        return month;
    }

    private static Year buildYear(DateType dateType, DateFormat df, LocalDate localDate, String pattern) throws Exception {
        Year year = new Year();
        String startDay = DateUtil.getStartDay(dateType, localDate, pattern);
        long startTime = df.parse(startDay).getTime();
        year.setStartDay(startDay);
        year.setStartDay10Time(startTime / 1000L);
        year.setStartDay13Time(startTime);
        String endDay = DateUtil.getEndDay(dateType, localDate, pattern);
        long entTime = df.parse(endDay).getTime();
        year.setLastDay(endDay);
        year.setLastDay10Time(entTime / 1000L);
        year.setLastDay13Time(entTime);
        if (dateType == DateType.LAST_YEAR) {
            localDate = localDate.minusYears(1L);
        }

        Map<String, List<String>> monthStartDayAndEndDay = buildMonthStartDayAndEndDay(localDate, pattern);
        List<String> monthStartDay = (List)monthStartDayAndEndDay.get("monthStartDay");
        year.setMonthStartDay(monthStartDay);
        Map<String, List<Long>> timeStamp = getTimeStamp(monthStartDay, df);
        year.setMonthStartDay13Time((List)timeStamp.get("13"));
        year.setMonthStartDay10Time((List)timeStamp.get("10"));
        List<String> monthEndDay = (List)monthStartDayAndEndDay.get("monthEndDay");
        year.setMonthEndDay(monthEndDay);
        timeStamp = getTimeStamp(monthEndDay, df);
        year.setMonthEndDay13Time((List)timeStamp.get("13"));
        year.setMonthEndDay10Time((List)timeStamp.get("10"));
        return year;
    }

    private static Map<String, List<String>> buildMonthStartDayAndEndDay(LocalDate localDate, String pattern) throws Exception {
        Map<String, List<String>> map = new HashMap();
        List<String> monthStartDay = new ArrayList();
        List<String> monthEndDay = new ArrayList();

        for(int i = 1; i <= 12; ++i) {
            Month month = getMonth(localDate, i, pattern);
            monthStartDay.add(month.getStartDay());
            monthEndDay.add(month.getLastDay());
        }

        map.put("monthStartDay", monthStartDay);
        map.put("monthEndDay", monthEndDay);
        return map;
    }

    private static Map<String, List<Long>> getTimeStamp(List<String> days, DateFormat df) throws ParseException {
        Map<String, List<Long>> map = new HashMap();
        List<Long> monthStartDay13Time = new ArrayList();
        List<Long> monthStartDay10Time = new ArrayList();
        Iterator var5 = days.iterator();

        while(var5.hasNext()) {
            String month = (String)var5.next();
            long time = df.parse(month).getTime();
            monthStartDay13Time.add(time);
            monthStartDay10Time.add(time / 1000L);
        }

        map.put("13", monthStartDay13Time);
        map.put("10", monthStartDay10Time);
        return map;
    }

    static Month getMonth(LocalDate localDate, int month, String pattern) throws Exception {
        LocalDate monthLocalDate = localDate.withMonth(month);
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Month m = buildMonth(DateType.THIS_MONTH, df, monthLocalDate, pattern, dateTimeFormatter);
        return m;
    }

    static Week getRelativelyWeek(int adjust, String pattern, Date date) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate;
        if (date == null) {
            localDate = LocalDate.now();
        } else {
            localDate = DateUtil.dateToLocalDate(date);
        }

        localDate = localDate.plusWeeks((long)adjust);
        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return buildWeek(DateType.CUSTOM_WEEK, df, localDate, pattern, dateTimeFormatter);
    }

    static Month getRelativelyMonth(int adjust, String pattern, Date date) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate;
        if (date == null) {
            localDate = LocalDate.now();
        } else {
            localDate = DateUtil.dateToLocalDate(date);
        }

        localDate = localDate.plusMonths((long)adjust);
        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return buildMonth(DateType.CUSTOM_MONTH, df, localDate, pattern, dateTimeFormatter);
    }

    static Year getRelativelyYear(int adjust, String pattern, Date date) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate;
        if (date == null) {
            localDate = LocalDate.now();
        } else {
            localDate = DateUtil.dateToLocalDate(date);
        }

        localDate = localDate.plusYears((long)adjust);
        DateFormat df = new SimpleDateFormat(pattern);
        return buildYear(DateType.CUSTOM_YEAR, df, localDate, pattern);
    }
}
