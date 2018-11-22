//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date;


import com.base.date.model.*;

import java.time.LocalDate;
import java.util.Date;

public class DateEnhance {
    private Date date;
    private Year year;
    private Year lastYear;
    private Month month;
    private Month lastMonth;
    private Month last2Month;
    private Week week;
    private Week lastWeek;
    private Week last2Week;
    private Day day;
    private Quarter quarter;

    public DateEnhance() {
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Quarter getQuarter() {
        return this.quarter;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    public Year getLastYear() {
        return this.lastYear;
    }

    void setLastYear(Year lastYear) {
        this.lastYear = lastYear;
    }

    public Month getLastMonth() {
        return this.lastMonth;
    }

    void setLastMonth(Month lastMonth) {
        this.lastMonth = lastMonth;
    }

    public Month getLast2Month() {
        return this.last2Month;
    }

    void setLast2Month(Month last2Month) {
        this.last2Month = last2Month;
    }

    public Week getLastWeek() {
        return this.lastWeek;
    }

    void setLastWeek(Week lastWeek) {
        this.lastWeek = lastWeek;
    }

    public Week getLast2Week() {
        return this.last2Week;
    }

    void setLast2Week(Week last2Week) {
        this.last2Week = last2Week;
    }

    public Year getYear() {
        return this.year;
    }

    void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return this.month;
    }

    void setMonth(Month month) {
        this.month = month;
    }

    public Week getWeek() {
        return this.week;
    }

    void setWeek(Week week) {
        this.week = week;
    }

    public Day getDay() {
        return this.day;
    }

    void setDay(Day day) {
        this.day = day;
    }

    public String toString() {
        return "DateEnhance{year=" + this.year + ", lastYear=" + this.lastYear + ", month=" + this.month + ", lastMonth=" + this.lastMonth + ", last2Month=" + this.last2Month + ", week=" + this.week + ", lastWeek=" + this.lastWeek + ", last2Week=" + this.last2Week + ", day=" + this.day + '}';
    }

    public Month getMonth(int month) throws Exception {
        return this.getMonth(month, (String)null);
    }

    public Month getMonth(int month, String pattern) throws Exception {
        if (month >= 0 && month <= 12) {
            return DateHelper.getMonth(LocalDate.now(), month, pattern);
        } else {
            throw new IllegalArgumentException("月份只能是1-12之间的数字");
        }
    }

    public Week getRelativelyWeek(int adjust) throws Exception {
        return this.getRelativelyWeek(adjust, (String)null, (Date)null);
    }

    public Week getRelativelyWeek(int adjust, String pattern) throws Exception {
        return this.getRelativelyWeek(adjust, pattern, (Date)null);
    }

    public Week getRelativelyWeek(int adjust, String pattern, Date date) throws Exception {
        return DateHelper.getRelativelyWeek(adjust, pattern, date);
    }

    public Month getRelativelyMonth(int adjust) throws Exception {
        return this.getRelativelyMonth(adjust, (String)null, (Date)null);
    }

    public Month getRelativelyMonth(int adjust, String pattern) throws Exception {
        return this.getRelativelyMonth(adjust, pattern, (Date)null);
    }

    public Month getRelativelyMonth(int adjust, String pattern, Date date) throws Exception {
        return DateHelper.getRelativelyMonth(adjust, pattern, date);
    }

    public Year getRelativelyYear(int adjust) throws Exception {
        return this.getRelativelyYear(adjust, (String)null, (Date)null);
    }

    public Year getRelativelyYear(int adjust, String pattern) throws Exception {
        return this.getRelativelyYear(adjust, pattern, (Date)null);
    }

    public Year getRelativelyYear(int adjust, String pattern, Date date) throws Exception {
        return DateHelper.getRelativelyYear(adjust, pattern, date);
    }
}
