//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date.model;


import com.base.date.DateHelper;
import com.base.date.DateType;
import com.base.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Month {
    private Date currentDate;
    private LocalDate localDate;
    private String startDay;
    private Long startDay13Time;
    private Long startDay10Time;
    private String lastDay;
    private Long lastDay13Time;
    private Long lastDay10Time;
    private List<String> days;
    private List<Long> days13Time;
    private List<Long> days10Time;

    public Month() {
    }

    public Month getPreviousMonth() throws Exception {
        return this.getPreviousMonth((String)null);
    }

    public Month getPreviousMonth(String pattern) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate = DateUtil.dateToLocalDate(this.currentDate);
        LocalDate nextlocalDate = localDate.minusMonths(1L);
        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Month nextMonth = DateHelper.buildMonth(DateType.THIS_MONTH, df, nextlocalDate, pattern, dateTimeFormatter);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = nextlocalDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        nextMonth.setCurrentDate(date);
        return nextMonth;
    }

    public Month getNextMonth() throws Exception {
        return this.getNextMonth((String)null);
    }

    public Month getNextMonth(String pattern) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate = DateUtil.dateToLocalDate(this.currentDate);
        LocalDate nextlocalDate = localDate.plusMonths(1L);
        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Month nextMonth = DateHelper.buildMonth(DateType.THIS_MONTH, df, nextlocalDate, pattern, dateTimeFormatter);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = nextlocalDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        nextMonth.setCurrentDate(date);
        return nextMonth;
    }

    public Date getCurrentDate() {
        return this.currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getStartDay() {
        return this.startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public Long getStartDay13Time() {
        return this.startDay13Time;
    }

    public void setStartDay13Time(Long startDay13Time) {
        this.startDay13Time = startDay13Time;
    }

    public Long getStartDay10Time() {
        return this.startDay10Time;
    }

    public void setStartDay10Time(Long startDay10Time) {
        this.startDay10Time = startDay10Time;
    }

    public String getLastDay() {
        return this.lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public Long getLastDay13Time() {
        return this.lastDay13Time;
    }

    public void setLastDay13Time(Long lastDay13Time) {
        this.lastDay13Time = lastDay13Time;
    }

    public Long getLastDay10Time() {
        return this.lastDay10Time;
    }

    public void setLastDay10Time(Long lastDay10Time) {
        this.lastDay10Time = lastDay10Time;
    }

    public List<String> getDays() {
        return this.days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<Long> getDays13Time() {
        return this.days13Time;
    }

    public void setDays13Time(List<Long> days13Time) {
        this.days13Time = days13Time;
    }

    public List<Long> getDays10Time() {
        return this.days10Time;
    }

    public void setDays10Time(List<Long> days10Time) {
        this.days10Time = days10Time;
    }

    public Long getLimitLastDay10Time() {
        return this.lastDay10Time + 86399L;
    }

    public Long getLimitLastDay13Time() {
        return this.lastDay13Time + 86399999L;
    }

    public String toString() {
        return "Month{startDay='" + this.startDay + '\'' + ", startDay13Time=" + this.startDay13Time + ", startDay10Time=" + this.startDay10Time + ", lastDay='" + this.lastDay + '\'' + ", lastDay13Time=" + this.lastDay13Time + ", lastDay10Time=" + this.lastDay10Time + ", days=" + this.days + ", days13Time=" + this.days13Time + ", days10Time=" + this.days10Time + '}';
    }
}
