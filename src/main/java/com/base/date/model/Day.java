//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date.model;

import com.base.date.DateHelper;
import com.base.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Day {
    private Date currentDate;
    private LocalDate localDate;
    private String date;
    private Long date10Time;
    private Long date13Time;
    private Integer dayOfWeek;
    private Integer dayOfMonth;
    private Integer dayOfYear;

    public Day() {
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Day getNextDay() throws Exception {
        return this.getNextDay((String)null);
    }

    public Day getNextDay(String pattern) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate = DateUtil.dateToLocalDate(this.currentDate);
        LocalDate nextlocalDate = localDate.plusDays(1L);
        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Day day = DateHelper.buildDay(nextlocalDate, dateTimeFormatter, df);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = nextlocalDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        day.setCurrentDate(date);
        return day;
    }

    public Day getPreviousDay(String pattern) throws Exception {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyyMMdd";
        }

        LocalDate localDate = DateUtil.dateToLocalDate(this.currentDate);
        LocalDate previouslocalDate = localDate.minusDays(1L);
        DateFormat df = new SimpleDateFormat(pattern);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Day day = DateHelper.buildDay(previouslocalDate, dateTimeFormatter, df);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = previouslocalDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        day.setCurrentDate(date);
        return day;
    }

    public Day getPreviousDay() throws Exception {
        return this.getPreviousDay((String)null);
    }

    public Date getCurrentDate() {
        return this.currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getDayOfYear() {
        return this.dayOfYear;
    }

    public void setDayOfYear(Integer dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDate10Time() {
        return this.date10Time;
    }

    public void setDate10Time(Long date10Time) {
        this.date10Time = date10Time;
    }

    public Long getDate13Time() {
        return this.date13Time;
    }

    public void setDate13Time(Long date13Time) {
        this.date13Time = date13Time;
    }

    public String toString() {
        return "Day{date='" + this.date + '\'' + ", date10Time=" + this.date10Time + ", date13Time=" + this.date13Time + '}';
    }
}
